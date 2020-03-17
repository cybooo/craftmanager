package cz.wake.manager.commads.staff;

import cz.wake.manager.Main;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.CommandPermission;
import io.github.jorelali.commandapi.api.arguments.Argument;
import io.github.jorelali.commandapi.api.arguments.IntegerArgument;
import io.github.jorelali.commandapi.api.arguments.LiteralArgument;
import io.github.jorelali.commandapi.api.arguments.StringArgument;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.LinkedHashMap;

public class ServerSlots_command {

    public static void registerCommand() {

        //Default: /server slots -> Vypíše statistiky o slotech
        LinkedHashMap<String, Argument> serverArgs = new LinkedHashMap<>();
        serverArgs.put("slots", new LiteralArgument("slots").withPermission(CommandPermission.fromString("craftmanager.slots.edit")));
        CommandAPI.getInstance().register("server", serverArgs, (sender, args) -> {
            int maxSlots = Bukkit.getMaxPlayers();
            int totalSlots = Main.getInstance().getConfig().getInt("totalSlots");
            int reservedSlots = Main.getInstance().getConfig().getInt("reservedSlots");
            int playerCount = Bukkit.getOnlinePlayers().size();

            sender.sendMessage(ChatColor.AQUA + "Maximální kapacita serveru: " + ChatColor.GRAY + maxSlots + ChatColor.DARK_GRAY + " Slotů");
            sender.sendMessage(ChatColor.AQUA + "Počet slotů pro hráče: " + ChatColor.GRAY + totalSlots + ChatColor.DARK_GRAY + " Slotů");
            sender.sendMessage(ChatColor.AQUA + "Počet slotů pro VIP a AT: " + ChatColor.GRAY + reservedSlots + ChatColor.DARK_GRAY + " Slotů");
            sender.sendMessage(ChatColor.AQUA + "Momentální počet hráčů: " + ChatColor.GRAY + playerCount + ChatColor.DARK_GRAY + " hráč/i/ů");
        });

        //Edit slots: /server slots total|reserved [value]
        serverArgs.put("type", new StringArgument().overrideSuggestions("total", "reserved"));
        serverArgs.put("slotsNumber", new IntegerArgument());
        CommandAPI.getInstance().register("server", serverArgs, (sender, args) -> { //Mění počet slotů
            int maxSlots = Bukkit.getMaxPlayers();
            String changeType = (String) args[0];
            int slotsNew = (int) args[1];

            switch (changeType) {
                case "total": { //Změní počet slotů pro HRÁČE
                    int reservedSlots = Main.getInstance().getConfig().getInt("reservedSlots");
                    if (slotsNew <= 0) { //chyba
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[!] " + ChatColor.RED  + "Počet slotů nesmí být menší než 0!");
                    } else if ((slotsNew + reservedSlots) <= maxSlots) { //správně
                        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + ">> " + ChatColor.GREEN + "Počet slotů pro hráče úspěšně změněn na " + slotsNew + ".");
                        Main.getInstance().getConfig().set("totalSlots", slotsNew);
                    } else { //chyba
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[!] " + ChatColor.RED  + "Počet slotů musí být menší než " + (maxSlots - reservedSlots) + "!");
                    }
                    break;
                }
                case "reserved": { //Změní počet slotů pro VIP a AT
                    int totalSlots = Main.getInstance().getConfig().getInt("totalSlots");
                    if (slotsNew < 0) { //chyba
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[!] " + ChatColor.RED  + "Počet slotů nesmí být menší než 0!");
                    } else if ((slotsNew + totalSlots) <= maxSlots) { //správně
                        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + ">> " + ChatColor.GREEN + "Počet slotů pro VIP a AT úspěšně změněn na " + slotsNew + ".");
                        Main.getInstance().getConfig().set("reservedSlots", slotsNew);
                    } else { //chyba
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[!] " + ChatColor.RED  + "Počet slotů musí být menší než " + (maxSlots - totalSlots) + "!");
                    }
                    break;
                }
                default:
                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[!] " + ChatColor.RED + "Špatný syntax příkazu! Správný syntax: /server slots [total|reserved] [nový počet slotů]");
            }
        });
    }
}
