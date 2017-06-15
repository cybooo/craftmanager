package cz.wake.manager.listener;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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

    private ArrayList<Player> queue = new ArrayList<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(!p.getWorld().getName().equalsIgnoreCase("pvp")){
            return;
        }

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block b = e.getClickedBlock();
            if (e.getClickedBlock().getType() == Material.SIGN
                    || e.getClickedBlock().getType() == Material.SIGN_POST
                    || e.getClickedBlock().getType() == Material.WALL_SIGN) {
                if(b.getLocation().equals(new Location(Bukkit.getWorld("pvp"), 452.0, 33.0, -700.0))){ //Teleport na spawn
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());
                }
                if(b.getLocation().equals(new Location(Bukkit.getWorld("pvp"), 448.0, 33.0, -703.0))) { //Teleport do areny
                    teleportToArena(p);
                }
                if(b.getLocation().equals(new Location(Bukkit.getWorld("pvp"), 450.0, 14.0, -700.0))) { //Teleport na spawn
                    if(!queue.equals(p)){
                        p.sendMessage("§eZa 4 vteriny budes teleportovan na spawn!");
                        queue.add(p);
                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new BukkitRunnable() {
                            @Override
                            public void run() {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());
                                queue.remove(p);
                            }
                        },80L);
                    }

                }
            }
        }
    }

    private void teleportToArena(Player p){
        int random = randRange(1,3);
        switch (random){
            case 1:
                p.teleport(new Location(Bukkit.getWorld("pvp"),455, 13, -692, 90, 0));
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

    private static int randRange(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max - min + 1) + min;
        return randomNum;
    }
}