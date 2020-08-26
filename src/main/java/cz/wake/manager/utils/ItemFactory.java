package cz.wake.manager.utils;

import net.minecraft.server.v1_16_R2.NBTTagCompound;
import net.minecraft.server.v1_16_R2.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ItemFactory {

    public static ItemStack create(Material material, byte data, String displayName, String... lore) {
        try {
            ItemStack itemStack = new MaterialData(material, data).toItemStack(1);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(displayName);
            if (lore != null) {
                List<String> finalLore = new ArrayList<>();
                for (String s : lore)
                    finalLore.add(s);
                itemMeta.setLore(finalLore);
            }
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ItemStack create(Material material, byte data, String displayName) {
        return create(material, data, displayName, (String[]) null);
    }

    public static org.bukkit.inventory.ItemStack createHead(String name, String uuid, String textureData) {
        net.minecraft.server.v1_16_R2.ItemStack sHead = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3));

        NBTTagCompound tag = new NBTTagCompound();
        NBTTagCompound skullOwnerTag = new NBTTagCompound();
        NBTTagCompound displayTag = new NBTTagCompound();
        NBTTagCompound propertiesTag = new NBTTagCompound();

        NBTTagList tagList = new NBTTagList();

        NBTTagCompound valueTag = new NBTTagCompound();
        valueTag.setString("Value", textureData);

        tagList.add(valueTag);

        propertiesTag.set("textures", tagList);

        skullOwnerTag.setString("Id", uuid);
        skullOwnerTag.setString("Name", name);

        skullOwnerTag.set("Properties", propertiesTag);

        displayTag.setString("Name", name);

        tag.set("SkullOwner", skullOwnerTag);

        tag.set("display", displayTag);

        sHead.setTag(tag);
        return CraftItemStack.asBukkitCopy(sHead);
    }

    public static ItemStack createColouredLeather(Material armourPart, int red, int green, int blue) {
        ItemStack itemStack = new ItemStack(armourPart);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        leatherArmorMeta.setColor(Color.fromRGB(red, green, blue));
        itemStack.setItemMeta(leatherArmorMeta);
        return itemStack;
    }

    public static ItemStack addGlow(ItemStack item) {
        try {
            net.minecraft.server.v1_16_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag = null;
            if (!nmsStack.hasTag()) {
                tag = new NBTTagCompound();
                nmsStack.setTag(tag);
            }
            if (tag == null) tag = nmsStack.getTag();
            NBTTagList ench = new NBTTagList();
            tag.set("ench", ench);
            nmsStack.setTag(tag);
            return CraftItemStack.asCraftMirror(nmsStack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public static String convertItemStackToJsonRegular(ItemStack itemStack) {
        // First we convert the item stack into an NMS itemstack
        net.minecraft.server.v1_16_R2.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = new NBTTagCompound();
        compound = nmsItemStack.save(compound);

        return compound.toString();
    }

    public static String convertItemStackToJson(ItemStack itemStack) {
        // ItemStack methods to get a net.minecraft.server.ItemStack object for serialization
        Class<?> craftItemStackClazz = TPReflectionUtil.getOBCClass("inventory.CraftItemStack");
        Method asNMSCopyMethod = TPReflectionUtil.getMethod(craftItemStackClazz, "asNMSCopy", ItemStack.class);

        // NMS Method to serialize a net.minecraft.server.ItemStack to a valid Json string
        Class<?> nmsItemStackClazz = TPReflectionUtil.getNMSClass("ItemStack");
        Class<?> nbtTagCompoundClazz = TPReflectionUtil.getNMSClass("NBTTagCompound");
        Method saveNmsItemStackMethod = TPReflectionUtil.getMethod(nmsItemStackClazz, "save", nbtTagCompoundClazz);

        Object nmsNbtTagCompoundObj; // This will just be an empty NBTTagCompound instance to invoke the saveNms method
        Object nmsItemStackObj; // This is the net.minecraft.server.ItemStack object received from the asNMSCopy method
        Object itemAsJsonObject; // This is the net.minecraft.server.ItemStack after being put through saveNmsItem method

        try {
            nmsNbtTagCompoundObj = nbtTagCompoundClazz.newInstance();
            nmsItemStackObj = asNMSCopyMethod.invoke(null, itemStack);
            itemAsJsonObject = saveNmsItemStackMethod.invoke(nmsItemStackObj, nmsNbtTagCompoundObj);
        } catch (Throwable t) {
            Bukkit.getLogger().log(Level.SEVERE, "failed to serialize itemstack to nms item", t);
            return null;
        }

        // Return a string representation of the serialized object
        return itemAsJsonObject.toString();
    }
}