package cz.wake.manager.listener;

import cz.wake.manager.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.List;

public class LoginListener implements Listener{

    @EventHandler(ignoreCancelled = true)
    public void onLogin(PlayerLoginEvent e){
        String ip = e.getAddress().getHostAddress();
        if ((!isProxyIP(ip)) && (!isDisabledIP(ip))){
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§ePro pripojeni na server pouzij IP: §cplay.craftmania.cz");
        }
    }

    private List<String> getProxyIPs() {
        return Main.getInstance().getConfig().getStringList("proxy-ips");
    }

    private List<String> getDisabledIPs() {
        return Main.getInstance().getConfig().getStringList("disable-ips");
    }

    private boolean isProxyIP(String ip) {
        return getProxyIPs().contains(ip);
    }

    private boolean isDisabledIP(String ip) {
        return getDisabledIPs().contains(ip);
    }
}
