package cz.wake.manager.utils;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

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
        return create(material, data, displayName, null);
    }

    public static org.bukkit.inventory.ItemStack createHead(String name, String uuid, String textureData) {
        if(Bukkit.getVersion().contains("v1_11_R1")){
            net.minecraft.server.v1_11_R1.ItemStack sHead = org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.SKULL_ITEM, 1, (short) 3));

            net.minecraft.server.v1_11_R1.NBTTagCompound tag = new net.minecraft.server.v1_11_R1.NBTTagCompound();
            net.minecraft.server.v1_11_R1.NBTTagCompound skullOwnerTag = new net.minecraft.server.v1_11_R1.NBTTagCompound();
            net.minecraft.server.v1_11_R1.NBTTagCompound displayTag = new net.minecraft.server.v1_11_R1.NBTTagCompound();
            net.minecraft.server.v1_11_R1.NBTTagCompound propertiesTag = new net.minecraft.server.v1_11_R1.NBTTagCompound();

            net.minecraft.server.v1_11_R1.NBTTagList tagList = new net.minecraft.server.v1_11_R1.NBTTagList();

            net.minecraft.server.v1_11_R1.NBTTagCompound valueTag = new net.minecraft.server.v1_11_R1.NBTTagCompound();
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
            return org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack.asBukkitCopy(sHead);
        } else if (Bukkit.getVersion().contains("v1_12_R1")) {
            net.minecraft.server.v1_12_R1.ItemStack sHead = org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.SKULL_ITEM, 1, (short) 3));

            net.minecraft.server.v1_12_R1.NBTTagCompound tag = new net.minecraft.server.v1_12_R1.NBTTagCompound();
            net.minecraft.server.v1_12_R1.NBTTagCompound skullOwnerTag = new net.minecraft.server.v1_12_R1.NBTTagCompound();
            net.minecraft.server.v1_12_R1.NBTTagCompound displayTag = new net.minecraft.server.v1_12_R1.NBTTagCompound();
            net.minecraft.server.v1_12_R1.NBTTagCompound propertiesTag = new net.minecraft.server.v1_12_R1.NBTTagCompound();

            net.minecraft.server.v1_12_R1.NBTTagList tagList = new net.minecraft.server.v1_12_R1.NBTTagList();

            net.minecraft.server.v1_12_R1.NBTTagCompound valueTag = new net.minecraft.server.v1_12_R1.NBTTagCompound();
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
            return org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack.asBukkitCopy(sHead);
        }
        return null;
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
            if(Bukkit.getVersion().contains("v1_11_R1")){
                net.minecraft.server.v1_11_R1.ItemStack nmsStack = org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack.asNMSCopy(item);
                net.minecraft.server.v1_11_R1.NBTTagCompound tag = null;
                if (!nmsStack.hasTag()) {
                    tag = new net.minecraft.server.v1_11_R1.NBTTagCompound();
                    nmsStack.setTag(tag);
                }
                if (tag == null) tag = nmsStack.getTag();
                net.minecraft.server.v1_11_R1.NBTTagList ench = new net.minecraft.server.v1_11_R1.NBTTagList();
                tag.set("ench", ench);
                nmsStack.setTag(tag);
                return org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack.asCraftMirror(nmsStack);
            } else if (Bukkit.getVersion().contains("v1_12_R1")) {
                net.minecraft.server.v1_12_R1.ItemStack nmsStack = org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack.asNMSCopy(item);
                net.minecraft.server.v1_12_R1.NBTTagCompound tag = null;
                if (!nmsStack.hasTag()) {
                    tag = new net.minecraft.server.v1_12_R1.NBTTagCompound();
                    nmsStack.setTag(tag);
                }
                if (tag == null) tag = nmsStack.getTag();
                net.minecraft.server.v1_12_R1.NBTTagList ench = new net.minecraft.server.v1_12_R1.NBTTagList();
                tag.set("ench", ench);
                nmsStack.setTag(tag);
                return org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack.asCraftMirror(nmsStack);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}