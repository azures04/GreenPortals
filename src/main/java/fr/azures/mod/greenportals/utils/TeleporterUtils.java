package fr.azures.mod.greenportals.utils;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;

public class TeleporterUtils {
	
	public static void teleport(
		Entity entity,
		ServerWorld destination_world,
		double x,
		double y,
		double z,
		float yaw,
		float pitch ) {
		
		if( entity instanceof ServerPlayerEntity ) {
			ServerPlayerEntity player = (ServerPlayerEntity)entity;
			destination_world.getChunkSource().addRegionTicket(
				TicketType.POST_TELEPORT,
				new ChunkPos( new BlockPos( x, y, z ) ),
				1,
				entity.getId()
			);
			player.stopRiding();
			if( player.isSleeping() ) {
				player.stopSleepInBed( true, true );
			}
			player.teleportTo( destination_world, x, y, z, yaw, pitch );
			player.setYHeadRot( yaw );
			player.onUpdateAbilities();
		} else {
			yaw = MathHelper.wrapDegrees( yaw );
			pitch = MathHelper.clamp( MathHelper.wrapDegrees( pitch ), -90, 90 );
			entity.moveTo( x, y, z, yaw, pitch );
			entity.setYHeadRot( yaw );
		}
		if( !( entity instanceof LivingEntity ) || !( (LivingEntity)entity ).isFallFlying() ) {
			entity.setDeltaMovement( entity.getDeltaMovement().multiply( 1.0D, 0.0D, 1.0D ) );
			entity.setOnGround( true );
		}
		if( entity instanceof CreatureEntity ) {
			( (CreatureEntity)entity ).getNavigation().stop();
		}
	}
}
