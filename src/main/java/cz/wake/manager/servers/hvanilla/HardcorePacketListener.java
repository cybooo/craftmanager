package cz.wake.manager.servers.hvanilla;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class HardcorePacketListener extends PacketAdapter {

    public HardcorePacketListener(Plugin plugin, ListenerPriority listenerPriority, PacketType... types) {
        super(plugin, listenerPriority, types);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.LOGIN && event.getPlayer().getHealth() > 0) {
            Player player = event.getPlayer();
            PacketContainer packet = event.getPacket();
            PacketLoginWrapper playServerLogin = new PacketLoginWrapper(packet);
            playServerLogin.setHardcore(true);
        }
    }
}
