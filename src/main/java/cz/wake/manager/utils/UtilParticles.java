package cz.wake.manager.utils;

import org.bukkit.Location;

public class UtilParticles {

    private final static int DEF_RADIUS = 128;

    public static void display(Particles effect, Location location, int amount, float speed) {
        effect.display(0, 0, 0, speed, amount, location, 128);
    }

    public static void display(Particles effect, Location location, int amount) {
        effect.display(0, 0, 0, 0, amount, location, 128);
    }

    public static void display(Particles effect, Location location) {
        display(effect, location, 1);
    }

    public static void display(Particles effect, double x, double y, double z, Location location, int amount) {
        effect.display((float) x, (float) y, (float) z, 0f, amount, location, 128);
    }

    public static void display(Particles effect, int red, int green, int blue, Location location, int amount) {
        for (int i = 0; i < amount; i++)
            effect.display(new Particles.OrdinaryColor(red, green, blue), location, DEF_RADIUS);
    }

    public static void display(int red, int green, int blue, Location location) {
        display(Particles.REDSTONE, red, green, blue, location, 1);
    }

    public static void display(Particles effect, int red, int green, int blue, Location location) {
        display(effect, red, green, blue, location, 1);
    }


}
