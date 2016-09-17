package cz.wake.manager.commads;

import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

public class Fakevote_command implements CommandExecutor{

    public static final String FAKE_HOST_NAME_FOR_VOTE = UUID.randomUUID().toString();

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString){
        if(Sender instanceof Player){
            Player player = (Player)Sender;
            if((Command.getName().equalsIgnoreCase("fakevote"))){
                if(player.hasPermission("craftmanager.admin")){
                    if (ArrayOfString.length == 0) {
                        player.sendMessage("§ePouziti: §f/fakevote nick test");
                        return true;
                    }
                    switch (ArrayOfString[0]) {
                        case "test":
                            if (ArrayOfString.length != 3) {
                                player.sendMessage(ChatColor.RED + "Musis specifikovat argumenty.");
                                player.sendMessage(ChatColor.RED + "/fakevote test <player> <service>");
                                return true;
                            }
                            Player voteForPlayer = Bukkit.getPlayer(ArrayOfString[1]);

                            com.vexsoftware.votifier.model.Vote vote = new com.vexsoftware.votifier.model.Vote();
                            vote.setUsername(ArrayOfString[1]);
                            vote.setTimeStamp(new Date().toString());
                            vote.setAddress(FAKE_HOST_NAME_FOR_VOTE);
                            vote.setServiceName(ArrayOfString[2]);
                            Bukkit.getPluginManager().callEvent(new VotifierEvent(vote));

                            player.sendMessage("§dTest vote pro: §f" + voteForPlayer.getName());
                    }
                    return true;
                } else {
                    player.sendMessage("§cNedostatecna opraveni na pouziti tohoto prikazu!");
                    return false;
                }
            }
        }
        return false;
    }
}
