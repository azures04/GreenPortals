package fr.azures.mod.greenportals.registry.blocks;

import java.util.Set;

import fr.azures.mod.greenportals.GreenPortals;
import fr.azures.mod.greenportals.utils.TeleporterUtils;
import fr.azures.mod.libs.nomorenbt.common.Data;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class PortalBlock extends Block {

	private Minecraft mc = Minecraft.getInstance();
    private static final VoxelShape SHAPE = VoxelShapes.box(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);

	public PortalBlock(Properties properties) {
		super(properties.noCollission());
	}
	
	@Override 
	public void entityInside(BlockState state, World worldIn, BlockPos posIn, Entity entityIn) {
		Data blockData = GreenPortals.getInstance().blocks.getData(mc.getLevelSource().getBaseDir().toString(), posIn);
		if (mc.player != null) {
			try {
				Set<RegistryKey<World>> dimensions = ServerLifecycleHooks.getCurrentServer().levelKeys();
		        dimensions.forEach(dimension -> {
		        	if (dimension.location().toString().contains(blockData.getString("dimId"))) {
		        		System.out.println(ServerLifecycleHooks.getCurrentServer().getLevel(dimension).dimension().location());
		        		try {
			        		TeleporterUtils.teleport(entityIn, ServerLifecycleHooks.getCurrentServer().getLevel(dimension), blockData.getInt("dimX") + 2, blockData.getInt("dimY"), blockData.getInt("dimZ") + 2, entityIn.xRot,  entityIn.yRot);
			        		System.out.println(mc.player.level.dimension().location().toString());
		        		} catch (Exception e) {
							e.printStackTrace();
						}
		        	}
		        });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

}
