package cz.wake.manager.perks.general;

import cz.wake.manager.utils.ItemFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.json.JSONArray;
import org.json.JSONObject;

public class SkullCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] array) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if (Command.getName().equalsIgnoreCase("skull")) {
                if (player.hasPermission("craftmanager.vip.skull")) {

                    String name, uuid, textureData;

                    OkHttpClient caller = new OkHttpClient();
                    Request request = new Request.Builder().url("https://api.mojang.com/users/profiles/minecraft/" + player.getName()).build();
                    try {
                        System.out.println("CALL: " + request.body());
                        Response response = caller.newCall(request).execute();
                        JSONObject json = new JSONObject(response.body().string());
                        System.out.println("RESPONSE: " + json.toString());

                        uuid = (String) json.get("id");
                        giveHead(player, uuid);


                    } catch (Exception e){
                        player.sendMessage("§cChyba v API Mojangu! Zkus to znova zachvilku! :)");
                        //e.printStackTrace();
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void giveHead(Player p, String uuid){
        String value, formatedUUID;
        OkHttpClient caller2 = new OkHttpClient();
        Request request2 = new Request.Builder().url("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid).build();
        try {
            System.out.println("CALL-1: " + request2.body());
            Response response = caller2.newCall(request2).execute();
            JSONObject json = new JSONObject(response.body().string());
            System.out.println("RESPONSE: " + json.toString());
            JSONArray jsonArray = json.getJSONArray("properties");
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            value = (String) jsonObject.get("value");

            formatedUUID = addUUIDDashes(uuid);

            ItemStack head = ItemFactory.createHead(p.getName(), formatedUUID, value);

            final Inventory inventory = p.getInventory();
            if (inventory.firstEmpty() != -1) {
                inventory.addItem(head);
                p.sendMessage("§eTvoje headka ti byla pridana do inventare!");
            } else {
                final Location loc = p.getLocation();
                p.getWorld().dropItem(loc, head);
                p.sendMessage("§cNemas volne misto v inventari, headka lezi na zemi!");
            }

        } catch (Exception e){
            p.sendMessage("§cChyba v API Mojangu! Zkus to znova zachvilku! :)");
            //e.printStackTrace();
        }
    }

    public static String addUUIDDashes(String idNoDashes) {
        StringBuffer idBuff = new StringBuffer(idNoDashes);
        idBuff.insert(20, '-');
        idBuff.insert(16, '-');
        idBuff.insert(12, '-');
        idBuff.insert(8, '-');
        return idBuff.toString();
    }
}
