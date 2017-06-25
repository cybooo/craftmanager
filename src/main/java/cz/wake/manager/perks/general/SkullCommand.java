package cz.wake.manager.perks.general;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import cz.wake.manager.utils.ItemFactory;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class SkullCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("skull"))) {
                if (player.hasPermission("craftmanager.develop")) {
                    String[] name = getFromName(player.getName());
//                  MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
//                  GameProfile profile = new GameProfile(UUID.randomUUID(), player.getName());
//                  profile.getProperties().put("textures", new Property("textures", name[0], name[1]));

                    ItemStack head = ItemFactory.createHead("§e" + player.getName(), name[0], name[1]);

                    final Inventory inventory = (Inventory) player.getInventory();
                    if (inventory.firstEmpty() != -1) {
                        inventory.addItem(new ItemStack[]{head});
                        player.sendMessage("§eTvoje headka ti byla pridana do inventare!");
                    } else {
                        final Location loc = player.getLocation();
                        player.getWorld().dropItem(loc, head);
                        player.sendMessage("§cNemas volne misto v inventari, headka lezi na zemi!");
                    }
                }
                return true;
            }
        }
        return false;
    }

    public String[] getFromPlayer(Player playerBukkit) {
        EntityPlayer playerNMS = ((CraftPlayer) playerBukkit).getHandle();
        GameProfile profile = playerNMS.getProfile();
        Property property = profile.getProperties().get("textures").iterator().next();
        String texture = property.getValue();
        String signature = property.getSignature();
        return new String[]{texture, signature};
    }

    public String[] getFromName(String name) {
        try {
            URL url_0 = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            InputStreamReader reader_0 = new InputStreamReader(url_0.openStream());
            String uuid = new JsonParser().parse(reader_0).getAsJsonObject().get("id").getAsString();

            URL url_1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader reader_1 = new InputStreamReader(url_1.openStream());
            JsonObject textureProperty = new JsonParser().parse(reader_1).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String texture = textureProperty.get("value").getAsString();
            String signature = textureProperty.get("signature").getAsString();

            return new String[]{uuid, texture, signature};
        } catch (IOException e) {
            System.err.println("Could not get skin data from session servers!");
            e.printStackTrace();
            return null;
        }
    }
}
