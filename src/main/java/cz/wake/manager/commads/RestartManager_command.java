package cz.wake.manager.commads;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RestartManager_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return true;
        Player p = (Player) commandSender;
        if (p.hasPermission("craftmanager.restartmanager")) {
            if (strings.length == 0) {
                p.sendMessage("§ePouziti: §f/rm start <cas> <duvod>");
                return true;
            }
            if (strings.length >= 3) {
                if (strings[0].equalsIgnoreCase("start")) {
                    //todo
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                        String dateInString = strings[1];
                        Date date = sdf.parse(dateInString);
                        p.sendMessage(String.valueOf(date.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
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
                p.sendMessage("§ePouziti: §f/rm start <cas> <duvod>");
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
