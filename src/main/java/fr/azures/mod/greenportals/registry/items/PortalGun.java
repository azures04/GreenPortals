package fr.azures.mod.greenportals.registry.items;

import java.util.Set;

import fr.azures.mod.greenportals.GreenPortals;
import fr.azures.mod.greenportals.registry.ModBlocks;
import fr.azures.mod.libs.nomorenbt.common.Data;
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

	private Minecraft mc = Minecraft.getInstance();

	public PortalGun(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		BlockPos inPortal = new BlockPos(entity.pick(10.0D, 10.0F, false).getLocation());
		BlockPos outPortal = new BlockPos(27, 87, -27);
		Data inPortalData = new Data();
		inPortalData.putString("dimId", mc.level.dimension().toString());
    	inPortalData.putInt("dimX", outPortal.getX());
    	inPortalData.putInt("dimY", outPortal.getY());
    	inPortalData.putInt("dimZ", outPortal.getZ());
		GreenPortals.getInstance().blocks.storeData(mc.level.dimension().toString(), inPortal, inPortalData);
		if (world.getBlockState(inPortal).getBlock() == Blocks.WATER || world.getBlockState(inPortal).getBlock() == Blocks.AIR) {
			world.setBlock(inPortal, ModBlocks.PORTAL_BLOCK.get().defaultBlockState(), 1);
			Set<RegistryKey<World>> dimensions = ServerLifecycleHooks.getCurrentServer().levelKeys();
		        dimensions.forEach(dimension -> {
		        if ("minecraft:overworld".contains(dimension.location().toString())) {
		        	try {
			        	ServerLifecycleHooks.getCurrentServer().getLevel(dimension).setChunkForced(outPortal.getX(), outPortal.getZ(), true);
			        	ServerLifecycleHooks.getCurrentServer().getLevel(dimension).setBlock(outPortal, ModBlocks.PORTAL_BLOCK.get().defaultBlockState(), 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
		        	Data outPortalData = new Data();
		        	outPortalData.putString("dimId", mc.level.dimension().toString());
		        	outPortalData.putInt("dimX", outPortal.getX());
		        	outPortalData.putInt("dimY", outPortal.getY());
		        	outPortalData.putInt("dimZ", outPortal.getZ());
		    		GreenPortals.getInstance().blocks.storeData(dimension.location().toString(), inPortal, outPortalData);
		        }
		    });
		}
		//TeleporterUtils.teleport(entity, ServerLifecycleHooks.getCurrentServer().getLevel(ServerWorld.END), entity.getX(), entity.getY(), entity.getZ(), entity.xRot, entity.yRot);
		return super.use(world, entity, hand);
	}
}
