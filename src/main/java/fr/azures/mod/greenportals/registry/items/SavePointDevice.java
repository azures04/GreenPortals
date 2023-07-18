package fr.azures.mod.greenportals.registry.items;

import fr.azures.mod.greenportals.GreenPortals;
import fr.azures.mod.greenportals.utils.PlayerUtils;
import fr.azures.mod.libs.nomorenbt.common.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SavePointDevice extends Item {

	private Minecraft mc = Minecraft.getInstance();

	public SavePointDevice(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		if (mc.player.isShiftKeyDown()) {
			Data playerState = new Data();
			playerState.putObject("items_inventory", PlayerUtils.getInventories(entity).get(0));
			playerState.putObject("armor_inventory", PlayerUtils.getInventories(entity).get(1));
			playerState.putObject("offhand_inventory", PlayerUtils.getInventories(entity).get(2));
			playerState.putObject("food", PlayerUtils.getFood(entity));
			playerState.putObject("health", PlayerUtils.getHealth(entity));
			playerState.putObject("experience_level", PlayerUtils.getExperienceLevel(entity));
			playerState.putObject("coordinates", PlayerUtils.getCoordinates(entity));
			playerState.putObject("abilities", PlayerUtils.getAbilities(entity));
			GreenPortals.getInstance().items.storeData(mc.player.getStringUUID(), this, playerState, false);
		} else {
			if (GreenPortals.getInstance().items.getData(mc.player.getStringUUID(), this)) {
			}
		}
		return super.use(world, entity, hand);
	}
}