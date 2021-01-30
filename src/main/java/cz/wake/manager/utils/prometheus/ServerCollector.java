package cz.wake.manager.utils.prometheus;

import cz.wake.manager.Main;
import cz.wake.manager.listener.CommandListener;
import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ServerCollector extends Collector {

    @Override
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> familySamples = new ArrayList<>();
        GaugeMetricFamily commandsExecutions = new GaugeMetricFamily(Main.getServerType().name().toLowerCase() + "_commands_executions", "Number of commands executions",
                Collections.singletonList("command"));
        familySamples.add(commandsExecutions);

        for (Map.Entry<String, Integer> command : CommandListener.getCommandsExecutions().entrySet()) {
            commandsExecutions.addMetric(Collections.singletonList(command.getKey()), command.getValue());
        }

        MetricsController.players.labels("online").set(Bukkit.getOnlinePlayers().size());
        MetricsController.players.labels("offline").set(Bukkit.getOfflinePlayers().length);

        // TPS
        double d = MinecraftServer.getServer().recentTps[0];
        MetricsController.tps.set(d);

        for (World world : Bukkit.getWorlds()) {
            MetricsController.loadedChunks.labels(world.getName()).set(world.getLoadedChunks().length);
            MetricsController.playersOnline.labels(world.getName()).set(world.getPlayers().size());
            MetricsController.entities.labels(world.getName()).set(world.getEntities().size());
            MetricsController.livingEntities.labels(world.getName()).set(world.getLivingEntities().size());
        }

        MetricsController.memory.labels("max").set(Runtime.getRuntime().totalMemory());
        MetricsController.memory.labels("free").set(Runtime.getRuntime().freeMemory());
        MetricsController.memory.labels("used").set(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        MetricsController.disk.labels("max").set(new File("/").getTotalSpace());
        MetricsController.disk.labels("used").set((new File("/").getTotalSpace()) - (new File("/").getUsableSpace()));

        try {
            MetricsController.cpu.set(MetricsController.getProcessCpuLoad());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return familySamples;
    }
}
