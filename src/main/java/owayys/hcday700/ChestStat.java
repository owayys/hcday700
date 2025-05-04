package owayys.hcday700;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = Hcday700.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChestStat {
    public static final ResourceLocation JUMPS_ON_CHESTS_ID = ResourceLocation.fromNamespaceAndPath(Hcday700.MODID, "jumps_on_chests");
    public static net.minecraft.stats.Stat<ResourceLocation> JUMPS_ON_CHESTS;

    @SubscribeEvent
    public static void registerCustomStats(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.CUSTOM_STAT)) {
            event.register(Registries.CUSTOM_STAT, JUMPS_ON_CHESTS_ID, () -> JUMPS_ON_CHESTS_ID);
        }
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            JUMPS_ON_CHESTS = Stats.CUSTOM.get(JUMPS_ON_CHESTS_ID, StatFormatter.DEFAULT);

            System.out.println("Registered custom stat: " + JUMPS_ON_CHESTS_ID);
        });
    }
}