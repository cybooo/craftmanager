package cz.wake.manager.utils;

import cz.wake.manager.Main;

public class UpdateTaskServer implements Runnable {

    @Override
    public void run() {
        Main.getInstance().getSetData().updateServerTask();
    }
}
