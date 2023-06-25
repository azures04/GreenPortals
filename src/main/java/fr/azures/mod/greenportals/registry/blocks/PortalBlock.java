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
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class PortalBlock extends Block {

	private Minecraft mc = Minecraft.getInstance();

	public PortalBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public void entityInside(BlockState stateIn, World worldIn, BlockPos posIn, Entity entityIn) {
		Data blockData = GreenPortals.getInstance().blocks.getData("global", posIn);
		if (mc.level != null) {
			try {
				System.out.println(blockData.getString("dimId"));
				Set<RegistryKey<World>> dimensions = ServerLifecycleHooks.getCurrentServer().levelKeys();
		        dimensions.forEach(dimension -> {
		        if (dimension.location().toString().contains(blockData.getString("dimId"))) {
		        	TeleporterUtils.teleport(entityIn, ServerLifecycleHooks.getCurrentServer().getLevel(dimension), blockData.getInt("dimX"), blockData.getInt("dimY"), blockData.getInt("dimZ"), entityIn.xRot,  entityIn.yRot);
		        }
		    });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
