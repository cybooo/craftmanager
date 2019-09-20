package cz.wake.manager.commads.staff;

import cz.craftmania.craftcore.spigot.messages.BossBar;
import cz.wake.manager.Main;
import cz.wake.manager.utils.tasks.RestartTask;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class RestartManager_command implements CommandExecutor {

    public static BossBar bb = new  BossBar("msg", "GREEN", "SEGMENTED_20", 1.0);
    public static List<BukkitTask> runnables = new ArrayList<>();
    public static int min;
    String combinedArgs;

  @Override
    public boolean onCommand(CommandSender p, Command command, String s, String[] strings) {
        if (p.hasPermission("craftmanager.restartmanager")) {
            if (strings.length == 0) {
                if (Main.restartTime != null) {
                  Long remaining = Main.restartTime - System.currentTimeMillis();
                  p.sendMessage("§e§l[*] §eAktualne je naplanovany §c§lRESTART §e(za " + TimeUnit.MILLISECONDS.toMinutes(remaining) + "m " + TimeUnit.MILLISECONDS.toSeconds(remaining) % 60 % 60 + "s)");
                }
                sendUsage(p);
                return true;
            }
            if (strings.length >= 3) {
                if (strings[0].equalsIgnoreCase("start")) {
                    if (Main.restartTime != null) {
                      p.sendMessage("§c§l[!] §cJiz probiha nejaky restart, musis ho nejdrive zrusit!");
                      return true;
                    }
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
                        runnables.add(Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(Main.getInstance(), new RestartTask(), 0, 20));

                        for (Player pl : Bukkit.getOnlinePlayers()) {
                          bb.addPlayer(pl);
                        }

                        bb.setVisible(true);

                        if (min > 60) {
                            Bukkit.getServer().broadcastMessage("§7§m---------§7[§c§l Restart serveru §7]§m---------\n");
                            Bukkit.getServer().broadcastMessage("§f");
                            Bukkit.getServer().broadcastMessage("   §7Server bude restartovan za §f" + TimeUnit.MINUTES.toHours(min) + "h " + TimeUnit.MINUTES.toMinutes(min) % 60 + "m§7.");
                            Bukkit.getServer().broadcastMessage("   §7Duvod restartu: §f" + Main.restartReason);
                            Bukkit.getServer().broadcastMessage("§f");
                            Bukkit.getServer().broadcastMessage("§7§m-------------------------------------");
                        return true;
                        } else {
                            Bukkit.getServer().broadcastMessage("§7§m---------§7[§c§l Restart serveru §7]§m---------\n");
                            Bukkit.getServer().broadcastMessage("§f");
                            Bukkit.getServer().broadcastMessage("   §7Server bude restartovan za §f" + TimeUnit.MINUTES.toMinutes(min) + "m§7.");
                            Bukkit.getServer().broadcastMessage("   §7Duvod restartu: §f" + Main.restartReason);
                            Bukkit.getServer().broadcastMessage("§f");
                            Bukkit.getServer().broadcastMessage("§7§m-------------------------------------");
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        p.sendMessage("§c§l[!] §cNespravny format cisel!");
                        return true;
                    }
                } else {
                    sendUsage(p);
              }
            } if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("stop")) {
                  if (Main.restartTime == null) {
                      p.sendMessage("§c§l[!] §cMomentalne neni naplanovan zadny restart.");
                      return true;
                  }
                  Main.restartTime = null;
                  Main.restartReason = null;
                  p.sendMessage("§e§l[*] §cNaplanovany restart byl uspesne zrusen.");
                  for (BukkitTask task : runnables) {
                      task.cancel();
                  }
                  bb.hide();
                  return true;
                } else {
                  sendUsage(p);
                }
            }
            else {
                sendUsage(p);
                return true;
            }
        } else {
          p.sendMessage("§c§l[!] §cNemas dostatecna prava!");
          return true;
        }
        return true;
    }

    private void sendUsage(CommandSender p) {
        p.sendMessage("§e§l[*] §ePouziti: §f/rm start/stop <minuty> <duvod>");
    }
}
