package cz.wake.manager.listener;

import cz.wake.manager.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.List;

public class LoginListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onLogin(PlayerLoginEvent e) {

        String ip = e.getAddress().getHostAddress();
        final Player p = e.getPlayer();

        if ((!isProxyIP(ip)) && (!isDisabledIP(ip))) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§ePro pripojeni na server pouzij IP: §cplay.craftmania.cz");
        }

        /*
        if(!Main.getInstance().getFetchData().hasDataChecker(p)){ // mrwakecz = mrwakecz
            Main.getInstance().getSetData().createPlayerCheck(p);

        } else {
            if(p.getName().equals(Main.getInstance().getFetchData().getNormalNameChecked(p))){ // MrWakeCZ == MrWakeCZ
                System.out.println("[CraftManager] Hrac " + p.getName() + " prosel uspesne kontrolou nicku.");
            } else {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cPodobny nick jiz hraje na serveru, nelze se pripojit.");
            }
        } */

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
