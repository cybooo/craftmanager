package cz.wake.manager.utils.prometheus;

import cz.wake.manager.Main;
import cz.wake.manager.utils.Log;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class MetricsController {

    public static final Gauge players = Gauge.build().name(Main.getInstance().getIdServer() + "_players_total").help("Online and offline players").labelNames("state").create().register();
    public static final Gauge loadedChunks = Gauge.build().name(Main.getInstance().getIdServer() + "_loaded_chunks_total").help("Chunks loaded per world").labelNames("world").create().register();
    public static final Gauge playersOnline = Gauge.build().name(Main.getInstance().getIdServer() + "_players_online_total").help("Players currently online per world").labelNames("world").create().register();
    public static final Gauge entities = Gauge.build().name(Main.getInstance().getIdServer() + "_entities_total").help("Entities loaded per world").labelNames("world").create().register();
    public static final Gauge livingEntities = Gauge.build().name(Main.getInstance().getIdServer() + "_living_entities_total").help("Living entities loaded per world").labelNames("world").create().register();
    public static final Gauge memory = Gauge.build().name(Main.getInstance().getIdServer() + "_jvm_memory").help("JVM memory usage").labelNames("type").create().register();
    public static final Gauge tps = Gauge.build().name(Main.getInstance().getIdServer() + "_tps").help("Server TPS (ticks per second)").create().register();
    public static final Gauge disk = Gauge.build().name(Main.getInstance().getIdServer() + "_disk").help("Space of disk").labelNames("type").create().register();
    public static final Gauge cpu = Gauge.build().name(Main.getInstance().getIdServer() + "_cpu").help("Processor").create().register();

    private static MetricsController instance;

    protected final HTTPServer server;

    public static void setup(Main plugin) {
        Log.withPrefix("Setup metrics " + instance(plugin).toString());
    }

    public static MetricsController instance(Main plugin) {
        if (instance == null)
            instance = new MetricsController(plugin);
        return instance;
    }

    public MetricsController(Main plugin) {
        DefaultExports.initialize();

        new ServerCollector().register();
        try {
            server = new HTTPServer(plugin.getConfig().getInt("prometheus.port"));
            Log.withPrefix("Setup HTTPServer for Metrics");
        } catch (IOException e) {
            throw new IllegalStateException("Failed to set up HTTPServer for Metrics", e);
        }
    }

    public HTTPServer getServer() {
        return server;
    }


    public static double getProcessCpuLoad() throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});

        if (list.isEmpty()) return Double.NaN;

        Attribute att = (Attribute) list.get(0);
        Double value = (Double) att.getValue();

        // usually takes a couple of seconds before we get real values
        if (value == -1.0) return Double.NaN;
        // returns a percentage value with 1 decimal point precision
        return ((int) (value * 1000) / 10.0);
    }
}
