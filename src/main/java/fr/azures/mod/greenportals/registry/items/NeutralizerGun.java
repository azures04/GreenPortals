package fr.azures.mod.greenportals.registry.items;

import fr.azures.mod.greenportals.registry.ModBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;

public class NeutralizerGun extends Item {

	public NeutralizerGun(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		BlockRayTraceResult rayTraceResult = rayTraceBlocks(world, entity, RayTraceContext.FluidMode.NONE);
		if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
			BlockPos targetPos = rayTraceResult.getBlockPos();
			world.setBlock(targetPos, ModBlocks.NEUTRALIZED_BLOCK.get().defaultBlockState(), 1);
		} else if (rayTraceResult.getType() == RayTraceResult.Type.ENTITY) {
	        Entity targetEntity = rayTraceResult.hitInfo instanceof Entity ? (Entity) rayTraceResult.hitInfo : null;
	        targetEntity.kill();
		}
		return super.use(world, entity, hand);
	}

	public static BlockRayTraceResult rayTraceBlocks(World worldIn, PlayerEntity player, RayTraceContext.FluidMode fluidMode) {
		float f = player.xRot;
		float f1 = player.yRot;
		Vector3d vec3d = player.getEyePosition(1.0F);
		float f2 = MathHelper.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
		float f3 = MathHelper.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
		float f4 = -MathHelper.cos(-f * ((float) Math.PI / 180F));
		float f5 = MathHelper.sin(-f * ((float) Math.PI / 180F));
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d0 = 5.0D;
		if (player instanceof ServerPlayerEntity) {
			d0 = ((ServerPlayerEntity) player).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue();
		}
		Vector3d vec3d1 = vec3d.add((double) f6 * d0, (double) f5 * d0, (double) f7 * d0);
		return worldIn.clip(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.OUTLINE, fluidMode, player));
	}
}
