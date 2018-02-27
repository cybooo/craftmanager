package cz.wake.manager.perks.general;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkullCommand implements CommandExecutor {

    //TODO: DOdelat...

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] array) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if (Command.getName().equalsIgnoreCase("skull")) {
                if (player.hasPermission("craftmanager.vip.skull")) {

                    giveHead(player, "dd");
                }
                return true;
            }
        }
        return false;
    }

    private void giveHead(Player p, String name) {
        try {


        } catch (Exception e) {
            p.sendMessage("§cChyba v API Mojangu! Zkus to znova zachvilku! :)");
            //e.printStackTrace();
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
