package cz.wake.manager.commads;

import cz.craftmania.craftcore.spigot.messages.BossBar;
import cz.wake.manager.Main;
import cz.wake.manager.utils.tasks.RestartTask;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class RestartManager_command implements CommandExecutor {

    public static BossBar bb = new BossBar("msg", "GREEN", "SEGMENTED_20", 1.0);
    public static int min;
    String combinedArgs;

  @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return true;
        Player p = (Player) commandSender;
        if (p.hasPermission("craftmanager.restartmanager")) {
            if (strings.length == 0) {
                p.sendMessage("§ePouziti: §f/rm start <minuty> <duvod>");
                return true;
            }
            if (strings.length >= 3) {
                if (strings[0].equalsIgnoreCase("start")) {
                    //todo
                    try {
                        min = Integer.valueOf(strings[1]);

                        if (strings.length > 3) {
                          StringBuilder sb = new StringBuilder();
                          for (int i = 2; i < strings.length; i++) {
                            sb.append(strings[i]);
                            sb.append(" ");
                          }
                          combinedArgs = sb.toString();
                          Main.restartReason = combinedArgs;
                        } else {
                          Main.restartReason = strings[2];
                        }



                        Main.restartTime = System.currentTimeMillis() + (min * 1000 * 60);
                        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(Main.getInstance(), new RestartTask(), 0, 20); //every 1s
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                          bb.addPlayer(pl);
                        }

                        Bukkit.getServer().broadcastMessage("§f");
                        Bukkit.getServer().broadcastMessage("§fByl naplanovan §crestart serveru§f.");
                        Bukkit.getServer().broadcastMessage("§fRestart za §a" + min + " minut§f!");
                        Bukkit.getServer().broadcastMessage("§fDuvod: §7" + Main.restartReason);
                        Bukkit.getServer().broadcastMessage("§f");
                        return true;
                    } catch (NumberFormatException e) {
                        p.sendMessage("§cSpatny format casu :herold:");
                        return true;
                    }
                } else {
                    //todo
                }
            } if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("stop")) {
                    //todo
                } else {
                    //todo
                }
            }
            else {
                p.sendMessage("§ePouziti: §f/rm start <minuty> <duvod>");
                return true;
            }
            //todo
        } else {
            p.sendMessage("§cNedostatecna opraveni na pouziti tohoto prikazu!");
            return true;
        }
        return true;
    }
}
