package cz.wake.manager.listener;

import cz.wake.manager.Main;
import cz.wake.manager.commads.staff.RestartManager_command;
import cz.wake.manager.perks.general.Chatcolor_command;
import cz.wake.manager.managers.RecipeManager;
import cz.wake.manager.managers.RecipePlayer;
import cz.wake.manager.perks.particles.ParticlesAPI;
import net.horkanos.craftchat.CraftChat;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    private ParticlesAPI partAPI = new ParticlesAPI();
    private Chatcolor_command chc = new Chatcolor_command();

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();

        e.setJoinMessage(null);

        //Oprava pro skoncene VIP hrace s Glowing
        p.setGlowing(false);

        //RestartManager
        if (Main.restartTime != null) {
            RestartManager_command.bb.addPlayer(p);
        }

        //Efekty
        if (!Main.getInstance().isVisibleForPlayer(p)) {
            Main.getInstance().addPlayer(p);
        }

        //Oprava pro skonceni fly
        if (!p.hasPermission("essentials.fly")) {
            if ((!p.hasPermission("askyblock.islandfly")) && p.getAllowFlight()) {
                p.setAllowFlight(false);
                p.setFlying(false);
            }
        }

        //AT
        if (Main.getInstance().getMySQL().isAT(p)) {
            Main.getInstance().at_list.add(p);
            Main.getInstance().at_afk.put(p, 0);
        }

        //Death messages
        if (Main.getInstance().getMySQL().getSettings(p, "death_messages") == 1) {
            Main.getInstance().death_messages.add(p);
        }

        //ChatColor
        if (p.hasPermission("craftmanager.chatcolor") && !p.hasPermission("craftmanager.chatcolor.at")) {
            setupChatColor(p);
        }

        //Mentions
        if (Main.getInstance().getMySQL().getSettingsString(p, "mention_sound") == null && Main.getInstance().getMySQL().getSettingsString(p, "mention_sound").equals("")) {
            Main.getInstance().getMySQL().updateSettings(p, "mention_sound", "ENTITY_EXPERIENCE_ORB_PICKUP");
        }

        //Disable chat
        if(Main.getInstance().getMySQL().getSettings(p, "disabled_chat") == 1){
            CraftChat.disableChat(p, true);
        }

        //Register Recipe Player
        RecipeManager.registerRecipePlayer(new RecipePlayer(p));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final Player p = e.getPlayer();

        e.setQuitMessage(null);

        if (Main.getInstance().isVisibleForPlayer(p)) {
            partAPI.deactivateParticles(p);
            Main.getInstance().removePlayer(p);
        }

        //Odebrani hrace z cache na hlasy
        Main.getInstance().getVoteHandler().removePlayer(p);

        //AT
        if (Main.getInstance().at_list.contains(p)) {
            Main.getInstance().at_list.remove(p);
        }

        if (Main.getInstance().at_afk.containsKey(p)) {
            Main.getInstance().at_afk.remove(p);
        }

        //Death messages
        if (Main.getInstance().death_messages.contains(p)) {
            Main.getInstance().death_messages.remove(p);
        }

        //Unregister RecipePlayeru
        RecipeManager.unregisterRecipePlayer(RecipeManager.getRecipePlayer(p));

        //RestartManager
        RestartManager_command.bb.removePlayer(p);
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        final Player p = e.getPlayer();

        if (Main.getInstance().isVisibleForPlayer(p)) {
            partAPI.deactivateParticles(p);
            Main.getInstance().removePlayer(p);
        }

        //Odebrani hrace z cache na hlasy
        Main.getInstance().getVoteHandler().removePlayer(p);

        //AT
        if (Main.getInstance().at_list.contains(p)) {
            Main.getInstance().at_list.remove(p);
        }

        if (Main.getInstance().at_afk.containsKey(p)) {
            Main.getInstance().at_afk.remove(p);
        }

        //Death messages
        if (Main.getInstance().death_messages.contains(p)) {
            Main.getInstance().death_messages.remove(p);
        }

        //Unregister RecipePlayeru
        RecipeManager.unregisterRecipePlayer(RecipeManager.getRecipePlayer(p));
    }

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent e) {
        String damager = e.getDamager().toString();

        // Blokace fireworku - damage na hrace
        if ((e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) && (damager.equalsIgnoreCase("CraftFirework"))) {
            e.setCancelled(true);
        }

        // Blokace sipu na ArmorStandy
        if ((e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE)
                && (e.getDamager().getType() == EntityType.PLAYER) && (e.getEntity().getType() == EntityType.ARMOR_STAND)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onGamemode(PlayerGameModeChangeEvent e) {
        Player p = e.getPlayer();

        // Deaktivace Spectatoru
        if (e.getNewGameMode() == GameMode.SPECTATOR && !p.hasPermission("craftmanager.spectatorallow")) {
            e.setCancelled(true);
            p.sendMessage("§cNelze si zmenit GM na Spectatora!");
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
            if(Main.getInstance().at_afk.containsKey(p)) {
                if(Main.getInstance().at_afk.get(p) != 0) {
                    Main.getInstance().at_afk.put(p, 0);
                }
            }
        }
    }

    private void setupChatColor(Player p) {
        int setting = Main.getInstance().getMySQL().getSettings(p, "chatcolor");
        switch (setting) {
            case 1:
                chc.setColor(p, ChatColor.DARK_BLUE);
                break;
            case 2:
                chc.setColor(p, ChatColor.DARK_GREEN);
                break;
            case 3:
                chc.setColor(p, ChatColor.DARK_AQUA);
                break;
            case 4:
                chc.setColor(p, ChatColor.DARK_RED);
                break;
            case 5:
                chc.setColor(p, ChatColor.DARK_PURPLE);
                break;
            case 6:
                chc.setColor(p, ChatColor.GOLD);
                break;
            case 7:
                chc.setColor(p, ChatColor.GRAY);
                break;
            case 8:
                chc.setColor(p, ChatColor.DARK_GRAY);
                break;
            case 9:
                chc.setColor(p, ChatColor.BLUE);
                break;
            case 10:
                chc.setColor(p, ChatColor.GREEN);
                break;
            case 11:
                chc.setColor(p, ChatColor.AQUA);
                break;
            case 12:
                chc.setColor(p, ChatColor.RED);
                break;
            case 13:
                chc.setColor(p, ChatColor.LIGHT_PURPLE);
                break;
            case 14:
            case 15:
                chc.setColor(p, ChatColor.WHITE);
                break;
            default:
                chc.setColor(p, ChatColor.WHITE);
                break;
        }
    }

}
