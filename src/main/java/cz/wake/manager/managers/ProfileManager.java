package cz.wake.manager.managers;

import org.bukkit.entity.Player;

import java.util.HashSet;

public class ProfileManager {

    public static HashSet<PlayerProfile> profiles = new HashSet<>();

    public static void registerProfile(PlayerProfile profile){
        profiles.add(profile);
    }

    public static void unregisterProfile(PlayerProfile profile){
        profiles.remove(profile);
    }

    public static PlayerProfile getProfile(Player p){
        for (PlayerProfile profile : profiles){
            if(profile.getPlayer() == p){
                return profile;
            }
        }
        return null;
    }
}
