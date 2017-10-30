package cz.wake.manager.utils;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AnvilContainer extends ContainerAnvil {

    public AnvilContainer(EntityHuman paramEntityHuman) {
        super(paramEntityHuman.inventory, paramEntityHuman.world, new BlockPosition(0, 0, 0), paramEntityHuman);
    }

    public boolean a(EntityHuman paramEntityHuman) {
        return true;
    }

    public static void openAnvil(Player paramPlayer) {

        EntityPlayer localEntityPlayer = ((CraftPlayer) paramPlayer).getHandle();
        AnvilContainer localAnvilContainer = new AnvilContainer(localEntityPlayer);
        int i = localEntityPlayer.nextContainerCounter();

        ((CraftPlayer) paramPlayer).getHandle().playerConnection.sendPacket(new PacketPlayOutOpenWindow(i, "minecraft:anvil", new ChatMessage("Repairing", new Object[0]), 0));

        localEntityPlayer.activeContainer = localAnvilContainer;
        localEntityPlayer.activeContainer.windowId = i;
        localEntityPlayer.activeContainer.addSlotListener(localEntityPlayer);
        localEntityPlayer.activeContainer = localAnvilContainer;
        localEntityPlayer.activeContainer.windowId = i;
        localEntityPlayer.activeContainer.checkReachable = false;

        Inventory localInventory = localAnvilContainer.getBukkitView().getTopInventory();
        localInventory.setMaxStackSize(99);
        localInventory.setItem(0, getItem(Material.PAPER, "Prejmenuj me", 0));
    }

    public static ItemStack getItem(Material material, String name, int data, String... paramVarArgs) {
        ArrayList localArrayList = new ArrayList();
        String[] arrayOfString;
        int j = (arrayOfString = paramVarArgs).length;
        for (int i = 0; i < j; i++) {
            Object localObject = arrayOfString[i];
            localArrayList.add(localObject);
        }
        Object localObject = new ItemStack(material, 1, (short) data);
        ItemMeta localItemMeta = ((ItemStack) localObject).getItemMeta();
        localItemMeta.setDisplayName(name);
        localItemMeta.setLore(localArrayList);
        ((ItemStack) localObject).setItemMeta(localItemMeta);
        return (ItemStack) localObject;
    }
}
