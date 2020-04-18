package cz.wake.manager.listener;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {

    @EventHandler
    public void dynamicSlotsLoginCheck(PlayerLoginEvent e) {
        int totalSlots = Main.getInstance().getConfig().getInt("totalSlots");
        int reservedSlots = Main.getInstance().getConfig().getInt("reservedSlots");
        int playerCount = Bukkit.getOnlinePlayers().size();

        if (playerCount < totalSlots || e.getPlayer().getName().equals("MrWakeCZ")) { //Jestli momentálních hráčů je méně než slotů povolených, hráče to připojí
            return;
        } else {
            if (e.getPlayer().hasPermission("craftmanager.vip.login-bypass")) {
                if (playerCount < (totalSlots + reservedSlots)) { //Hráč má práva na login-bypass a server ještě není úplně plný
                    return;
                } else { //Hráč sice má práva na login-bypass, ale server presáhl svou maximální kapacitu
                    e.disallow(PlayerLoginEvent.Result.KICK_FULL, "Server je plný!\nServer dosáhl své maximální kapacity pro hráče, VIP i AT! Budeš si muset počkat, než se uvolní místo pro tebe.");
                }
            } else { //Hráč nemá práva na login-bypass a server je plný -> Kick message pro hráče
                e.disallow(PlayerLoginEvent.Result.KICK_FULL, "Server je plný!\nPokud se chceš připojit na plný server, musíš si zakoupit VIP!");
            }
        }
    }
}
