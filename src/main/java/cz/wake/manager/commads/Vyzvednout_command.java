package cz.wake.manager.commads;

import cz.wake.craftcore.messages.Advancement;
import cz.wake.craftcore.messages.chat.CenteredMessage;
import cz.wake.craftcore.messages.handler.AdvancementManager;
import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Vyzvednout_command implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("vyzvednout")) {

                if(Main.getInstance().getMySQL().getRewardState(player) == 1){
                    player.sendMessage("§c§l(!) §cOdmenu jsi si jiz vybral/a!");
                    return false;
                }

                if(!(Main.getInstance().getIdServer().equalsIgnoreCase("survival") || Main.getInstance().getIdServer().equalsIgnoreCase("skyblock"))){
                    player.sendMessage("§c§l(!) §cTento prikaz lze pouzit pouze na Survivalu nebo Skyblocku.");
                    return false;
                } else {
                    //Kdyz je v SQL
                    if(Main.getInstance().getMySQL().hasActiveReward(player.getName())){
                        if(isInventoryFull(player)){
                            player.sendMessage("§c§l(!) §cMas plny inventar, uvolni si jedno misto pro Epic Crate!");
                            return false;
                        } else {
                            CenteredMessage.sendMessage(player,"","§b§lDekujeme","§7Ziskal/a jsi: 400CC + 3x EpicCrate","");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "packages give " + player.getName() + " epiccrate 3");
                            Main.getInstance().getMySQL().addCoins(player, 400);
                            Advancement.builder(new NamespacedKey(Main.getInstance(), "craftmanager/vyzvednuto"))
                                    .title("Odmena vyzvednuta!").description("_").icon("minecraft:diamond")
                                    .announce(false).hidden(false).toast(true).frame(AdvancementManager.FrameType.TASK).build()
                                    .show(Main.getInstance(), player);
                            Main.getInstance().getMySQL().updateReward(player);
                        }
                    } else {
                        player.sendMessage("§6§l(?) Vypada to, ze jsi nevyplnil dotaznik a tak nejsi mezi hraci, co maji dostat odmenu. Bohuzel...");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public boolean isInventoryFull(Player p) {
        return p.getInventory().firstEmpty() == -1;
    }

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(!(Main.getInstance().getIdServer().equalsIgnoreCase("survival") || Main.getInstance().getIdServer().equalsIgnoreCase("skyblock"))){
            return;
        }

        if(Main.getInstance().getMySQL().getRewardState(p) == 0){
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), new BukkitRunnable() {
                @Override
                public void run() {
                    Advancement.builder(new NamespacedKey(Main.getInstance(), "craftmanager/nevyzvednuto"))
                            .title("Mas nevyzvednutou odmenu!").description("_").icon("minecraft:paper")
                            .announce(false).hidden(false).toast(true).frame(AdvancementManager.FrameType.TASK).build()
                            .show(Main.getInstance(), p);
                    Advancement.builder(new NamespacedKey(Main.getInstance(), "craftmanager/nevyzvednuto2"))
                            .title("Pouzij: /vyzvednout").description("_").icon("minecraft:paper")
                            .announce(false).hidden(false).toast(true).frame(AdvancementManager.FrameType.TASK).build()
                            .show(Main.getInstance(), p);
                }
            }, 40L);
        }
    }
}
