package owayys.hcday700;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.EnderChestBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventHandler {
    private static final Map<UUID, Double> lastYPosition = new HashMap<>();
    private static final Map<UUID, Boolean> wasOnGround = new HashMap<>();
    private static final Map<UUID, Long> lastJumpTime = new HashMap<>();

    private static final long JUMP_COOLDOWN_MS = 500; // Prevent multiple detections of the same jump
    private static final double JUMP_THRESHOLD = 0.1; // Minimum Y movement to be considered a jump

    public static void registerEvents() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            long currentTime = System.currentTimeMillis();

            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                UUID playerId = player.getUuid();

                boolean onGround = player.isOnGround();
                double currentY = player.getY();

                double lastY = lastYPosition.getOrDefault(playerId, currentY);
                boolean wasPlayerOnGround = wasOnGround.getOrDefault(playerId, true);
                long playerLastJumpTime = lastJumpTime.getOrDefault(playerId, 0L);

                if (wasPlayerOnGround
                        && !onGround
                        && currentY > lastY
                        && currentY - lastY > JUMP_THRESHOLD
                        && currentTime - playerLastJumpTime > JUMP_COOLDOWN_MS) {
                    onPlayerJump(player);
                    lastJumpTime.put(playerId, currentTime);
                }
                lastYPosition.put(playerId, currentY);
                wasOnGround.put(playerId, onGround);
            }
        });
    }

    private static void onPlayerJump(PlayerEntity player) {
        Block blockBelow = BlockUtils.getBlockBelow(player);
        if (blockBelow instanceof ChestBlock ||
                blockBelow instanceof BarrelBlock ||
                blockBelow instanceof EnderChestBlock ||
                blockBelow instanceof ShulkerBoxBlock) {

            player.incrementStat(ChestStat.JUMPS_ON_CHESTS);
        }
    }
}
