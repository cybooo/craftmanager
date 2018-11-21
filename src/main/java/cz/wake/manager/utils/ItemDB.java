package cz.wake.manager.utils;

import cz.wake.manager.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemDB {

    private final transient Main ess;
    private final transient Map<String, Integer> items = new HashMap<>();
    private final transient Map<ItemData, List<String>> names = new HashMap<>();
    private final transient Map<ItemData, String> primaryName = new HashMap<>();
    private final transient Map<String, Short> durabilities = new HashMap<>();
    private final transient Map<String, String> nbtData = new HashMap<>();
    private final transient ManagedFile file;
    private final transient Pattern splitPattern = Pattern.compile("((.*)[:+',;.](\\d+))");
    private final transient Pattern csvSplitPattern = Pattern.compile("(\"([^\"]*)\"|[^,]*)(,|$)");

    public ItemDB(final Main ess) {
        this.ess = ess;
        file = new ManagedFile("items.csv", ess);
    }

    public void reloadConfig() {
        final List<String> lines = file.getLines();

        if (lines.isEmpty()) {
            return;
        }

        durabilities.clear();
        items.clear();
        names.clear();
        primaryName.clear();

        for (String line : lines) {
            if (line.length() > 0 && line.charAt(0) == '#') {
                continue;
            }

            String itemName = null;
            int numeric = -1;
            short data = 0;
            String nbt = null;

            int col = 0;
            Matcher matcher = csvSplitPattern.matcher(line);
            while (matcher.find()) {
                String match = matcher.group(1);
                if (StringUtils.stripToNull(match) == null) {
                    continue;
                }
                match = StringUtils.strip(match.trim(), "\"");
                switch (col) {
                    case 0:
                        itemName = match.toLowerCase(Locale.ENGLISH);
                        break;
                    case 1:
                        numeric = Integer.parseInt(match);
                        break;
                    case 2:
                        data = Short.parseShort(match);
                        break;
                    case 3:
                        nbt = StringUtils.stripToNull(match);
                        break;
                    default:
                        continue;
                }
                col++;
            }
            // Invalid row
            if (itemName == null || numeric < 0) {
                continue;
            }
            durabilities.put(itemName, data);
            items.put(itemName, numeric);
            if (nbt != null) {
                nbtData.put(itemName, nbt);
            }

            ItemData itemData = new ItemData(numeric, data);
            if (names.containsKey(itemData)) {
                List<String> nameList = names.get(itemData);
                nameList.add(itemName);
            } else {
                List<String> nameList = new ArrayList<>();
                nameList.add(itemName);
                names.put(itemData, nameList);
                primaryName.put(itemData, itemName);
            }
        }

        for (List<String> nameList : names.values()) {
            Collections.sort(nameList, LengthCompare.INSTANCE);
        }
    }

    public ItemStack get(final String id, final int quantity) throws Exception {
        final ItemStack retval = get(id.toLowerCase(Locale.ENGLISH));
        retval.setAmount(quantity);
        return retval;
    }

    public ItemStack get(final String id) throws Exception {
        int itemid = 0;
        String itemname;
        short metaData = 0;
        Matcher parts = splitPattern.matcher(id);
        if (parts.matches()) {
            itemname = parts.group(2);
            metaData = Short.parseShort(parts.group(3));
        } else {
            itemname = id;
        }

        if (IntegerUtil.isInt(itemname)) {
            itemid = Integer.parseInt(itemname);
        } else if (IntegerUtil.isInt(id)) {
            itemid = Integer.parseInt(id);
        } else {
            itemname = itemname.toLowerCase(Locale.ENGLISH);
        }

        if (itemid < 1) {
            if (items.containsKey(itemname)) {
                itemid = items.get(itemname);
                if (durabilities.containsKey(itemname) && metaData == 0) {
                    metaData = durabilities.get(itemname);
                }
            } else if (Material.getMaterial(itemname.toUpperCase(Locale.ENGLISH)) != null) {
                Material bMaterial = Material.getMaterial(itemname.toUpperCase(Locale.ENGLISH));
                itemid = bMaterial.getId();
            } else {
                try {
                    //Material bMaterial = Bukkit.getUnsafe().getMaterialFromInternalName(itemname.toLowerCase(Locale.ENGLISH));
                    //itemid = bMaterial.getId();
                    itemid = 1; //TODO: Fix portu z 1.12
                } catch (Throwable throwable) {
                }
            }
        }

        if (itemid < 1) {
            //throw new Exception(tl("unknownItemName", itemname));
        }

        final Material mat = Material.getMaterial(String.valueOf(itemid)); //TODO: Fix je to spatne! xD
        if (mat == null) {
            //throw new Exception(tl("unknownItemId", itemid));
        }
        ItemStack retval = new ItemStack(mat);
        if (nbtData.containsKey(itemname)) {
            String nbt = nbtData.get(itemname);
            if (nbt.startsWith("*")) {
                nbt = nbtData.get(nbt.substring(1));
            }
            retval = ess.getServer().getUnsafe().modifyItemStack(retval, nbt);
        }
        retval.setDurability(metaData);

        retval.setAmount(mat.getMaxStackSize());
        return retval;
    }

    public Collection<String> listNames() {
        return primaryName.values();
    }

    static class ItemData {
        final private int itemNo;
        final private short itemData;

        ItemData(final int itemNo, final short itemData) {
            this.itemNo = itemNo;
            this.itemData = itemData;
        }

        public int getItemNo() {
            return itemNo;
        }

        public short getItemData() {
            return itemData;
        }

        @Override
        public int hashCode() {
            return (31 * itemNo) ^ itemData;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof ItemData)) {
                return false;
            }
            ItemData pairo = (ItemData) o;
            return this.itemNo == pairo.getItemNo() && this.itemData == pairo.getItemData();
        }
    }


    static class LengthCompare implements java.util.Comparator<String> {

        private static final LengthCompare INSTANCE = new LengthCompare();

        public LengthCompare() {
            super();
        }

        @Override
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }
}