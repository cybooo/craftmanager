package cz.wake.manager.perks.general;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

@CommandAlias("skull|hlava")
@Description("Dá ti tvojí hlavu")
public class SkullCommand extends BaseCommand {

    private HashMap<Player, Double> _time = new HashMap<>();
    private HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<>();

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lSkull commands:");
        help.showHelp();
    }

    @Default
    public void giveSkull(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
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
                    player.sendMessage("§c§l[!] §cTento prikaz muzes provadet pouze kazdych 10 minut!");
                }
            }
        }
    }

    private void giveHead(Player p) {
        try {
            //String command = "minecraft:give %creator% skull 1 3 {SkullOwner:\"%name%\",display:{Name:\"§b§l%name%\",Lore:[\"§7Vygenerovano pomoci §e/skull\",\"§8Vytvoril: %creator%\"]}}"
            //        .replaceAll("%creator%", p.getName()).replaceAll("%name%", p.getName());
            // 1.14+
            //String command = "give %creator% minecraft:player_head{SkullOwner:\"%creator%\",display:{Lore:[\"{\"text\":\"Vygenerováno pomocí \",\"color\":\"blue\",\"extra\":[{\"text\":\"/skull\",\"color\":\"green\"}]}\"]}}"
            //        .replaceAll("%creator%", p.getName()).replaceAll("%name%", p.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give %creator% minecraft:player_head{\"SkullOwner\":\"%creator%\"}".replaceAll("%creator%", p.getName()));
        } catch (Exception e) {
            p.sendMessage("§c§l[!] §cChyba v API Mojangu! Zkus to znova zachvilku! :)");
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
