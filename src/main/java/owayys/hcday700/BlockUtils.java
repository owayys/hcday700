package owayys.hcday700;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class BlockUtils {
    public static Block getBlockBelow(Player player) {
        Level level = player.level();
        Vec3[] checkPoints = getVec3s(player);

        for (Vec3 startPoint : checkPoints) {
            Vec3 endPoint = new Vec3(startPoint.x, startPoint.y - 0.3, startPoint.z);

            BlockHitResult hitResult = level.clip(new ClipContext(
                    startPoint,
                    endPoint,
                    ClipContext.Block.COLLIDER,
                    ClipContext.Fluid.NONE,
                    player));

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                Block blockBelow = level.getBlockState(hitResult.getBlockPos()).getBlock();
                if (!(blockBelow instanceof AirBlock)) {
                    return blockBelow;
                }
            }
        }

        return level.getBlockState(player.blockPosition().below()).getBlock();
    }

    private static Vec3 @NotNull [] getVec3s(Player player) {
        double PLAYER_WIDTH = 0.8;
        double halfWidth = PLAYER_WIDTH / 2;

        return new Vec3[] {
                new Vec3(player.getX() - halfWidth + 0.1, player.getY() - 0.01, player.getZ() - halfWidth + 0.1),
                new Vec3(player.getX() + halfWidth - 0.1, player.getY() - 0.01, player.getZ() - halfWidth + 0.1),
                new Vec3(player.getX() - halfWidth + 0.1, player.getY() - 0.01, player.getZ() + halfWidth - 0.1),
                new Vec3(player.getX() + halfWidth - 0.1, player.getY() - 0.01, player.getZ() + halfWidth - 0.1),
                new Vec3(player.getX(), player.getY() - 0.01, player.getZ())
        };
    }
}
