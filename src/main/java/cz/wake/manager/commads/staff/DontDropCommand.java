package cz.wake.manager.commads.staff;

import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class DontDropCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player)sender;
            if (args.length < 1) {
                player.sendMessage("§eCo takkle pouzit treba /dontdrop [block/unblock/info]");
                return true;
            }
            switch (args[0]) {
                case "block":
                    if (player.isOp() || player.hasPermission("craftmanager.vip.dontdrop.admin")) {
                        final List<String> configList = (List<String>)Main.getInstance().getConfig().getList("dontdrop.worlds");
                        configList.add(player.getWorld().getName());
                        Main.getInstance().getConfig().set("dontdrop.worlds", configList);
                        Main.getInstance().saveConfig();
                        player.sendMessage("§aSvet byl pridan na seznam blokovanych.");
                    }
                    break;
                case "unblock":
                    if (player.isOp() || player.hasPermission("craftmanager.vip.dontdrop.admin")) {
                        Main.getInstance().getConfig().getList("dontdrop.worlds").remove(player.getWorld().getName());
                        player.sendMessage("§cSvet byl odebran z blokovanych.");
                    }
                    break;
                case "info":
                    if (Main.getInstance().getConfig().getList("dontdrop.worlds").contains(player.getWorld().getName())) {
                        player.sendMessage("§eV tomto svete §anemohou §eVIP dropovat itemy!");
                    }
                    else {
                        player.sendMessage("§eV tomto svete §cmohou §eVIP dropovat itemy!");
                    }
                    break;
                default:
                    player.sendMessage("§eCo takkle pouzit treba /dontdrop [block/unblock/info]");
                    break;
            }
        }
        return true;
    }
}
