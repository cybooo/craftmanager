package cz.wake.manager.perks.twerking;

import cz.wake.manager.Main;
import cz.wake.manager.utils.ExpUtil;
import cz.wake.manager.utils.ServerType;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.Optional;
import java.util.Random;

public class TwerkEvent implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onToggleSneak(PlayerToggleSneakEvent e) {
        if (e.isCancelled() || !e.isSneaking()) {
            return;
        }
        final Player player = e.getPlayer();
        if (!player.hasPermission("craftmanager.vip.twerking")
                || Main.getServerType() == ServerType.VANILLA
                || Main.getServerType() == ServerType.SKYCLOUD) {
            return;
        }
        final World world = player.getWorld();
        final Location location = player.getLocation();
        final int blockX = location.getBlockX();
        final int blockY = location.getBlockY();
        final int blockZ = location.getBlockZ();
        Object empty = Optional.empty();

        int radiusX = 1;
        int radiusY = 0;
        int radiusZ = 1;

        for (int n = -radiusX; n <= radiusX && !((Optional)empty).isPresent(); ++n) {
            for (int n2 = -radiusY; n2 <= radiusY && !((Optional)empty).isPresent(); ++n2) {
                for (int i = -radiusZ; i <= radiusZ; ++i) {
                    final Optional<Block> ofNullable = Optional.ofNullable(world.getBlockAt(blockX + n, blockY + n2, blockZ + i));
                    if (ofNullable.isPresent()) {
                        if (ofNullable.get().getType() == Material.OAK_SAPLING) { //TODO: All types Material
                            empty = ofNullable;
                            break;
                        }
                    }
                }
            }
        }
        if (!((Optional)empty).isPresent()) {
            return;
        }
        final Location location2 = ((Optional<Block>)empty).get().getLocation();
        //TODO: Effects
        //world.playEffect(location2.add(0.5, 0.5, 0.5), Effect.VILLAGER_PLANT_GROW, 0, 0, 0.25f, 0.25f, 0.25f, 1.0f, 16, 16);

        if (ExpUtil.getTotalExperience(player) - 15 < 0) {
            player.sendMessage("§c§l[!] §cNemas dostatek expu!");
            return;
        }

        int level = ExpUtil.getTotalExperience(player);
        ExpUtil.setTotalExperience(player, level - 15);

        int sance = randRange(1,8);
        if (sance != 1) {
            return;
        }

        final byte data = ((Optional<Block>)empty).get().getData();
        final Material type = ((Optional<Block>)empty).get().getType();
        TreeType treeType = null;
        switch (data) {
            case 0: {
                treeType = TreeType.TREE;
                break;
            }
            case 1: {
                treeType = TreeType.REDWOOD;
                break;
            }
            case 2: {
                treeType = TreeType.BIRCH;
                break;
            }
            case 3: {
                treeType = TreeType.JUNGLE;
                break;
            }
            case 4: {
                treeType = TreeType.ACACIA;
                break;
            }
            case 5: {
                treeType = TreeType.DARK_OAK;
                break;
            }
            case 8: {
                treeType = TreeType.TREE;
                break;
            }
            default: {
                //Chyba
                return;
            }
        }
        ((Optional<Block>)empty).get().setType(Material.AIR);
        if (!world.generateTree(location2, treeType)) {
            //TODO: world.getBlockAt(location2).setTypeIdAndData(type.getId(), data, false);
            ExpUtil.setTotalExperience(player, level);
        }
    }

    private static int randRange(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max - min + 1) + min;
        return randomNum;
    }
}
