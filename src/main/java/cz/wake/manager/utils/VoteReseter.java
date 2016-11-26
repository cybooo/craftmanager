package cz.wake.manager.utils;

import cz.wake.manager.Main;

public class VoteReseter implements Runnable {

    @Override
    public void run() {
        System.out.println("[CraftManager] Kontrola resetu hlasu.");
        if(Main.getInstance().getFetchData().getResetTimeWeek() < System.currentTimeMillis()){
            System.out.println("[CraftManager] Byl detekovan reset Week hlasu.");
            System.out.println("[CraftManager] Probehne reset...");
            Main.getInstance().getSetData().resetWeekVotes();
            System.out.println("[CraftManager] Hlasy za 7 dni vyresetovany. Probehne vypocet dalsiho restartu!");
            long nextReset = (System.currentTimeMillis() + 604800000); // 7 dni
            Main.getInstance().getSetData().resetTimeVoteWeek(nextReset);
            System.out.println("[CraftManager] Restart hlasu probehl uspesne!");
        } else {
            System.out.println("[CraftManager] Restart Week hlasu byl zrusen, nenastal cas.");
        }

        if(Main.getInstance().getFetchData().getResetTimeMonth() < System.currentTimeMillis()){
            System.out.println("[CraftManager] Byl detekovan reset Month hlasu.");
            System.out.println("[CraftManager] Probehne reset...");
            Main.getInstance().getSetData().resetMonthVotes();
            System.out.println("[CraftManager] Hlasy za 30 dni vyresetovany.");
        } else {
            System.out.println("[CraftManager] Restart Month hlasu byl zrusen, nenastal cas.");
        }
    }
}