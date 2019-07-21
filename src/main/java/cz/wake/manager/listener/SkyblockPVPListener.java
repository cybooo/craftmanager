package cz.wake.manager.listener;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

public class SkyblockPVPListener implements Listener {

    public static ArrayList<Player> queue = new ArrayList<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (!p.getWorld().getName().equalsIgnoreCase("pvp")) {
            return;
        }

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();
            if (e.getClickedBlock().getType() == Material.OAK_SIGN
                    || e.getClickedBlock().getType() == Material.OAK_SIGN
                    || e.getClickedBlock().getType() == Material.OAK_WALL_SIGN) {
                if (b.getLocation().equals(new Location(Bukkit.getWorld("pvp"), 452.0, 33.0, -700.0))) { //Teleport na spawn
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());
                }
                if (b.getLocation().equals(new Location(Bukkit.getWorld("pvp"), 448.0, 33.0, -703.0))) { //Teleport do areny
                    if (p.getInventory().contains(Material.ELYTRA) || (p.getInventory().getItemInOffHand().getType().equals(Material.ELYTRA))) {
                        p.sendMessage("§c§l(!) §cNelze vstoupit do PvP s Elytrou!");
                        return;
                    } else if ((p.getInventory().contains(Material.GOLDEN_APPLE)) || (p.getInventory().getItemInOffHand().getType().equals(Material.GOLDEN_APPLE))) {
                        p.sendMessage("§c§l(!) §cNelze vstoupit do PvP s Golden Apple!");
                        return;
                    } else if ((p.getInventory().contains(Material.TOTEM_OF_UNDYING)) || (p.getInventory().getItemInOffHand().getType().equals(Material.TOTEM_OF_UNDYING))) {
                        p.sendMessage("§c§l(!) §cNelze vstoupit do PvP s Totemem!");
                        return;
                    }
                    teleportToArena(p);
                    sendMessage(p);
                    p.setAllowFlight(false);
                    p.setFlying(false);
                }
                if (b.getLocation().equals(new Location(Bukkit.getWorld("pvp"), 450.0, 14.0, -700.0)) && !queue.equals(p)) { //Teleport na spawn
                    p.sendMessage("§e§l(*) §eZa 4 vteriny budes teleportovan na spawn!");
                    queue.add(p);
                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), new BukkitRunnable() {
                        @Override
                        public void run() {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());
                            queue.remove(p);
                        }
                    }, 80L);

                }
            }
        }
    }

    private void teleportToArena(Player p) {
        int random = randRange(1, 3);
        switch (random) {
            case 1:
                p.teleport(new Location(Bukkit.getWorld("pvp"), 455, 13, -692, 90, 0));
                break;
            case 2:
                p.teleport(new Location(Bukkit.getWorld("pvp"), 476, 21, -679, 120, 0));
                break;
            case 3:
                p.teleport(new Location(Bukkit.getWorld("pvp"), 440, 13, -705, -12, 0));
                break;
            default:
                p.teleport(new Location(Bukkit.getWorld("pvp"), 440, 13, -705, -12, 0));
                break;
        }
    }

    private void sendMessage(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1f, 1f);
        p.sendMessage("   ");
        p.sendMessage("§c§lUpozorneni pro PvP:");
        p.sendMessage("§eTeamy v PVP jsou zakazany, poruseni se tresta banem!");
        p.sendMessage("   ");
    }

    private static int randRange(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max - min + 1) + min;
        return randomNum;
    }
}
