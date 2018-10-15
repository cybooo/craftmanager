package cz.wake.manager.perks.general;

import cz.wake.manager.Main;
import cz.wake.manager.listener.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class SkullCommand implements CommandExecutor {

    private HashMap<Player, Double> _time = new HashMap<>();
    private HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] array) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if (Command.getName().equalsIgnoreCase("skull")) {
                if (player.hasPermission("craftmanager.vip.skull")) {
                    if (!this._time.containsKey(player)) {
                        this._time.put(player, 600D + 0.1D);
                        giveHead(player);
                        this._cdRunnable.put(player, new BukkitRunnable() {
                            @Override
                            public void run() {
                                _time.put(player, (_time.get(player)) - 0.1D);
                                if ((_time.get(player)) < 0.01D) {
                                    _time.remove(player);
                                    _cdRunnable.remove(player);
                                    cancel();
                                }
                            }
                        });
                        (this._cdRunnable.get(player)).runTaskTimer(Main.getInstance(), 2L, 2L);
                    } else {
                        player.sendMessage("§cTento prikaz muzes provadet pouze kazdych 10 minut!");
                    }
                }
                return true;
            }
        }
        return true;
    }

    private void giveHead(Player p) {
        try {
            String command = "minecraft:give %creator% skull 1 3 {SkullOwner:\"%name%\",display:{Name:\"§b§l%name%\",Lore:[\"§7Vygenerovano pomoci §e/skull\",\"§8Vytvoril: %creator%\"]}}"
                    .replaceAll("%creator%", p.getName()).replaceAll("%name%", p.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        } catch (Exception e) {
            p.sendMessage("§cChyba v API Mojangu! Zkus to znova zachvilku! :)");
        }
    }

    //TODO: Pridat do CraftCore
    public static String addUUIDDashes(String idNoDashes) {
        StringBuffer idBuff = new StringBuffer(idNoDashes);
        idBuff.insert(20, '-');
        idBuff.insert(16, '-');
        idBuff.insert(12, '-');
        idBuff.insert(8, '-');
        return idBuff.toString();
    }
}
