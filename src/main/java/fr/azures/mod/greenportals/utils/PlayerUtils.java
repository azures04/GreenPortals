package fr.azures.mod.greenportals.utils;

import java.util.ArrayList;

import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class PlayerUtils {
	
	public static ArrayList<ItemStack[]> getInventories(PlayerEntity player) {
		ArrayList<ItemStack[]> inventories = new ArrayList<ItemStack[]>();
		inventories.add(player.inventory.items.toArray(new ItemStack[0]));
		inventories.add(player.inventory.armor.toArray(new ItemStack[0]));
		inventories.add(player.inventory.offhand.toArray(new ItemStack[0]));
		return inventories;
	}
	
	public static float getHealth(PlayerEntity player) {
		return player.getHealth();
	}
	
	public static float getFood(PlayerEntity player) {
		return player.getFoodData().getFoodLevel();
	}
	
	public static float getExperienceLevel(PlayerEntity player) {
		return player.experienceLevel;
	}
	
	public static PlayerAbilities getAbilities(PlayerEntity player) {
		return player.abilities;
	}
	
	public static BlockPos getCoordinates(PlayerEntity player) {
		return new BlockPos(player.getX(), player.getY(), player.getZ());
	}
}
