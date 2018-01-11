package cz.wake.manager.perks.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Replacements implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onHeroChat(AsyncPlayerChatEvent e){
        final Player p = e.getPlayer();
        String msg = e.getMessage();
        if(p.hasPermission("craftmanager.vip.replacements")){
            if(msg.contains("o/")){
                msg = msg.replace("o/", "( ﾟ◡ﾟ)/");
            }
            if(msg.contains("<3")){
                msg = msg.replace("<3", "§c❤");
            }
            if(msg.contains(":star:")){
                msg = msg.replace(":star:", "§6✮");
            }
            if(msg.contains(":shrug:")){
                msg = msg.replace(":shrug:", "¯\\_(ツ)_/¯");
            }
            if(msg.contains(":tableflip:")){
                msg = msg.replace(":tableflip:", "(╯°□°）╯︵ ┻━┻");
            }
            if(msg.contains(":unflip:")){
                msg = msg.replace(":unflip:", "┬─┬ ノ( ゜-゜ノ)");
            }
            if(msg.contains(":fight:")){
                msg = msg.replace(":fight:", "(ง'̀-'́)ง");
            }
            if(msg.contains(":lenny:")){
                msg = msg.replace(":lenny:", "( ͡° ͜ʖ ͡°)");
            }
            if(msg.contains(":moneypls:")){
                msg = msg.replace(":moneypls:", "(づ｡◕‿‿◕｡)づ");
            }
            if(msg.contains(":*")){
                msg = msg.replace(":*", "(づ￣ ³￣)づ");
            }
            if(msg.contains(":hype:")){
                msg = msg.replace(":hype:", "ヾ(⌐■_■)ノ♪");
            }
            if(msg.contains(":cry:")){
                msg = msg.replace(":cry:", "(ಥ﹏ಥ)");
            }
            e.setMessage(msg);
        }
    }
}
