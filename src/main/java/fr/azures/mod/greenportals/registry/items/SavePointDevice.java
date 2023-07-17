package fr.azures.mod.greenportals.registry.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SavePointDevice extends Item {

	public SavePointDevice(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		if (Minecraft.getInstance().player.isShiftKeyDown()) {
			
		}
		return super.use(world, entity, hand);
	}
}