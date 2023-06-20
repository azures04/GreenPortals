package fr.azures.mod.greenportals.registry.blocks;

import fr.azures.mod.greenportals.registry.ModTilesEntities;
import fr.azures.mod.greenportals.registry.tileentity.PortalTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class PortalBlock extends Block {

	public PortalBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public void stepOn(World worldIn, BlockPos posIn, Entity entityIn) {
		/*Set<RegistryKey<World>> dimensions = ServerLifecycleHooks.getCurrentServer().levelKeys();
	    dimensions.forEach(dimension -> {
	        if ("minecraft:the_nether".contains(dimension.location().toString())) {
	            TeleporterUtils.teleport(entityIn, ServerLifecycleHooks.getCurrentServer().getLevel(dimension), posIn.getX(), posIn.getY(), posIn.getZ(), entityIn.yRot, entityIn.xRot);
	        }
	    });*/
		if (Minecraft.getInstance().player != null) {
			Minecraft.getInstance().player.sendMessage(new StringTextComponent(worldIn.getBlockEntity(posIn).serializeNBT().toString()), Minecraft.getInstance().player.getUUID());
		}
		super.stepOn(worldIn, posIn, entityIn);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTilesEntities.PORTAL_TILE_ENTITY_TYPE.get().create();
	}

}
