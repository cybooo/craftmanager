package cz.wake.manager.commads.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

@CommandAlias("fakevote")
@Description("Otevře ti tvůj profil")
public class Fakevote_command extends BaseCommand {

    private static final String FAKE_HOST_NAME_FOR_VOTE = UUID.randomUUID().toString();

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lFakevote commands:");
        help.showHelp();
    }

    @Default
    @CommandCompletion("@players [test]")
    @Syntax("[nick] [test]")
    public void makeFakeVote(CommandSender Sender, String playerName, String test) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if (player.hasPermission("craftmanager.admin")) {
                Player voteForPlayer = Bukkit.getPlayer(playerName);

                com.vexsoftware.votifier.model.Vote vote = new com.vexsoftware.votifier.model.Vote();
                vote.setUsername(playerName);
                vote.setTimeStamp(new Date().toString());
                vote.setAddress(FAKE_HOST_NAME_FOR_VOTE);
                vote.setServiceName(test);
                Bukkit.getPluginManager().callEvent(new VotifierEvent(vote));
                player.sendMessage("§dTest vote pro: §f" + voteForPlayer.getName());
            } else {
                player.sendMessage("§c§l[!] §cNedostatecna opraveni na pouziti tohoto prikazu!");
            }
        }
    }
}
