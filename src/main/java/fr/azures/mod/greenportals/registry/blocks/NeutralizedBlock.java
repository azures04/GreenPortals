package fr.azures.mod.greenportals.registry.blocks;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NeutralizedBlock extends Block {

	public NeutralizedBlock(Properties properties) {
		super(properties.noCollission());
	}
	
	@Override
	public void onPlace(BlockState newState, World world, BlockPos pos, BlockState oldState, boolean bool) {
        int delay = 5000;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            	world.setBlock(pos, oldState, 2);
            }
        };

        timer.schedule(task, delay);
	}

}
