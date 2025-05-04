package owayys.hcday700;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class ChestStat {
    public static final Identifier JUMPS_ON_CHESTS_ID = Identifier.of(Hcday700.MOD_ID, "jumps_on_chests");
    public static net.minecraft.stat.Stat<Identifier> JUMPS_ON_CHESTS;

    public static void registerStats() {
        Registry.register(Registries.CUSTOM_STAT, JUMPS_ON_CHESTS_ID, JUMPS_ON_CHESTS_ID);

        JUMPS_ON_CHESTS = Stats.CUSTOM.getOrCreateStat(JUMPS_ON_CHESTS_ID, StatFormatter.DEFAULT);

        System.out.println("Registered custom stat: " + JUMPS_ON_CHESTS_ID);
    }
}