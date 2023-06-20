package fr.azures.mod.greenportals.registry.items;

import java.util.Set;

import fr.azures.mod.greenportals.registry.ModBlocks;
import fr.azures.mod.greenportals.registry.tileentity.PortalTile;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class PortalGun extends Item {

	public PortalGun(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		BlockPos inPortal = new BlockPos(entity.pick(10.0D, 10.0F, false).getLocation());
		BlockPos outPortal = new BlockPos(27, 87, -27);
		if (world.getBlockState(inPortal).getBlock() == Blocks.WATER || world.getBlockState(inPortal).getBlock() == Blocks.AIR) {
			world.setBlock(inPortal, ModBlocks.PORTAL_BLOCK.get().defaultBlockState(), 1);
			PortalTile inPortalEntity = (PortalTile) world.getBlockEntity(inPortal);
			inPortalEntity.setTeleportationCoordinates(outPortal);
			inPortalEntity.setTeleportationDimension("minecraft:overworld");
			inPortalEntity.setChanged();
			Set<RegistryKey<World>> dimensions = ServerLifecycleHooks.getCurrentServer().levelKeys();
		        dimensions.forEach(dimension -> {
		        if ("minecraft:overworld".contains(dimension.location().toString())) {
		        	try {
			        	ServerLifecycleHooks.getCurrentServer().getLevel(dimension).setChunkForced(outPortal.getX(), outPortal.getZ(), true);
			        	ServerLifecycleHooks.getCurrentServer().getLevel(dimension).setBlock(outPortal, ModBlocks.PORTAL_BLOCK.get().defaultBlockState(), 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
		        	PortalTile outPortalEntity = (PortalTile) ServerLifecycleHooks.getCurrentServer().getLevel(dimension).getBlockEntity(outPortal);
		        	outPortalEntity.setTeleportationCoordinates(new BlockPos(outPortal.getX(), outPortal.getY(), outPortal.getZ()));
		        	outPortalEntity.setTeleportationDimension(world.dimension().location().toString());
		        	outPortalEntity.setChanged();
		        }
		    });
		}
		//TeleporterUtils.teleport(entity, ServerLifecycleHooks.getCurrentServer().getLevel(ServerWorld.END), entity.getX(), entity.getY(), entity.getZ(), entity.xRot, entity.yRot);
		return super.use(world, entity, hand);
	}
}
