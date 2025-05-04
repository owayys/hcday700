package owayys.hcday700;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.*;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof Player player) {
            Block blockBelow = BlockUtils.getBlockBelow(player);
            if (blockBelow instanceof ChestBlock || blockBelow instanceof BarrelBlock || blockBelow instanceof EnderChestBlock || blockBelow instanceof ShulkerBoxBlock) {
                player.awardStat(ChestStat.JUMPS_ON_CHESTS);
            }
        }
    }
}