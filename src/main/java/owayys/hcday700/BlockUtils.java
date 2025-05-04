package owayys.hcday700;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtils {
    public static Block getBlockBelow(PlayerEntity player) {
        World world = player.getWorld();

        BlockPos playerPos = player.getBlockPos();

        BlockPos[] nearbyPositions = {
                playerPos.down(),
                playerPos.north().down(),
                playerPos.south().down(),
                playerPos.east().down(),
                playerPos.west().down(),
                playerPos.north().east().down(),
                playerPos.north().west().down(),
                playerPos.south().east().down(),
                playerPos.south().west().down()
        };

        for (BlockPos pos : nearbyPositions) {
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if (!(block instanceof AirBlock)) {
                return block;
            }
        }

        return world.getBlockState(playerPos.down()).getBlock();
    }
}