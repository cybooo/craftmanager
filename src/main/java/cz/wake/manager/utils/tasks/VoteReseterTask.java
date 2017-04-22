package cz.wake.manager.utils.tasks;

import cz.wake.manager.Main;
import cz.wake.manager.utils.Log;
import org.bukkit.ChatColor;

public class VoteReseterTask implements Runnable {

    @Override
    public void run() {
        Log.withPrefix("Kontrola resetu hlasu.");
        if (Main.getInstance().getMySQL().getResetTimeWeek() < System.currentTimeMillis()) {
            Log.withPrefix("Byl detekovan reset Week hlasu.");
            Log.withPrefix("Probehne reset...");
            Main.getInstance().getMySQL().resetWeekVotes();
            Log.withPrefix("Hlasy za 7 dni vyresetovany. Probehne vypocet dalsiho restartu!");
            long nextReset = (System.currentTimeMillis() + 604800000); // 7 dni
            Main.getInstance().getMySQL().resetTimeVoteWeek(nextReset);
            Log.withPrefix(ChatColor.GREEN + "Restart hlasu probehl uspesne!");
        } else {
            Log.withPrefix("Restart Week hlasu byl zrusen, nenastal cas.");
        }

        if (Main.getInstance().getMySQL().getResetTimeMonth() < System.currentTimeMillis()) {
            Log.withPrefix("Byl detekovan reset Month hlasu.");
            Log.withPrefix("Probehne reset...");
            Main.getInstance().getMySQL().resetMonthVotes();
            Log.withPrefix(ChatColor.GREEN + "Hlasy za 30 dni vyresetovany.");
        } else {
            Log.withPrefix("Restart Month hlasu byl zrusen, nenastal cas.");
        }
    }
}
