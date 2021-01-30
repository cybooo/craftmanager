package cz.wake.manager.utils.tasks;

import cz.wake.manager.Main;
import cz.wake.manager.perks.coloranvil.ColorHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class AnvilTask extends BukkitRunnable {

    private static HashMap<AnvilInventory, AnvilTask> anvilTasks;
    private AnvilInventory inv;
    private Player player;

    static {
        AnvilTask.anvilTasks = new HashMap<>();
    }

    public AnvilTask(final AnvilInventory inv, final Player player) {
        this.inv = inv;
        this.player = player;
        AnvilTask.anvilTasks.put(inv, this);
        this.runTaskTimer(Main.getInstance(), 1L, 3L);
    }

    public void run() {
        if (this.inv.getViewers().size() == 0) {
            this.cancel();
        }
        ColorHandler.getTranslatedItem(this.player, this.inv, this);
    }

    public static AnvilTask getTask(final AnvilInventory inv) {
        return AnvilTask.anvilTasks.get(inv);
    }
}
