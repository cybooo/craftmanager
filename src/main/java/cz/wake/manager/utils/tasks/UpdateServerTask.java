package cz.wake.manager.utils.tasks;

import cz.wake.manager.Main;

public class UpdateServerTask implements Runnable {

    @Override
    public void run() {
        Main.getInstance().getMySQL().updateServerTask();
    }
}
