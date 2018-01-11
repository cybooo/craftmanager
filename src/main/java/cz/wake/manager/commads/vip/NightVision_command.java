package cz.wake.manager.commads.vip;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVision_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("nv"))) {
                if (player.hasPermission("craftmanager.nightvision")) {
                    if(!player.hasPotionEffect(PotionEffectType.NIGHT_VISION)){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 12000000, 5));
                        player.sendMessage("§eAktivoval jsi permanentni §bNightVision!");
                    } else {
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        player.sendMessage("§eDeaktivoval jsi efekt §bNightVision");
                    }
                } else {
                    player.sendMessage("§cK pouziti teto schopnosti musis vlastnit VIP!");
                    return true;
                }

            }

        }
        return false;
    }
}
