package cz.wake.manager.commads.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("slots")
@Description("Umožní ti upravovat přístupné sloty na serveru")
public class ServerSlots_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lServerSlots commands:");
        help.showHelp();
    }

    @Default
    public void showSlotsSettings(CommandSender sender) {
        if (sender instanceof Player) {
            if (sender.hasPermission("craftmanager.slots.edit")) {
                int maxSlots = Bukkit.getMaxPlayers();
                int totalSlots = Main.getInstance().getConfig().getInt("totalSlots");
                int reservedSlots = Main.getInstance().getConfig().getInt("reservedSlots");
                int playerCount = Bukkit.getOnlinePlayers().size();

                sender.sendMessage(ChatColor.AQUA + "Maximální kapacita serveru: " + ChatColor.GRAY + maxSlots + ChatColor.DARK_GRAY + " Slotů");
                sender.sendMessage(ChatColor.AQUA + "Počet slotů pro hráče: " + ChatColor.GRAY + totalSlots + ChatColor.DARK_GRAY + " Slotů");
                sender.sendMessage(ChatColor.AQUA + "Počet slotů pro VIP a AT: " + ChatColor.GRAY + reservedSlots + ChatColor.DARK_GRAY + " Slotů");
                sender.sendMessage(ChatColor.AQUA + "Momentální počet hráčů: " + ChatColor.GRAY + playerCount + ChatColor.DARK_GRAY + " hráč/i/ů");
            }
        }
    }

    @Default
    @CommandCompletion("[total|reserved] [cislo]")
    @Syntax("[total|reserved] [cislo]")
    public void changeSlotsSettings(CommandSender sender, String changeType, int slotsNew) {
        int maxSlots = Bukkit.getMaxPlayers();
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
                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[!] " + ChatColor.RED + "Špatný syntax příkazu! Správný syntax: /slots [total|reserved] [nový počet slotů]");
        }
    }
}
