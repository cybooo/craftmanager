package cz.wake.manager.servers.skycloud;

public enum VillagerType {

    SELL_VILLAGER("sell_villager"),
    BUY_VILLAGER("buy_villager"),
    END_VILLAGER("end_villager"),
    NETHER_VILLAGER("nether_villager"),
    RARE_VILLAGER("rare_villager"),
    SEA_VILLAGER("sea_villager")
    ;

    private String name;

    VillagerType(String name) {
        this.name = name;
    }
}
