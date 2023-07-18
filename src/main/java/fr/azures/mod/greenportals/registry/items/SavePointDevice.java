package fr.azures.mod.greenportals.registry.items;

import fr.azures.mod.greenportals.GreenPortals;
import fr.azures.mod.greenportals.utils.PlayerUtils;
import fr.azures.mod.greenportals.utils.TeleporterUtils;
import fr.azures.mod.libs.nomorenbt.common.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

@SuppressWarnings("all")
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
			playerState.putObject("dimension", mc.player.level.dimension());
			GreenPortals.getInstance().items.storeData(mc.player.getStringUUID(), this, playerState, false);
		} else {
			if (GreenPortals.getInstance().items.getData(mc.player.getStringUUID(), this) == null) {
				mc.player.sendMessage(new StringTextComponent("Aucune position n'a été sauvegarder !"), mc.player.getUUID());
			} else {
				Data playerState = GreenPortals.getInstance().items.getData(mc.player.getStringUUID(), this);
				mc.player.inventory.items.clear();
				mc.player.inventory.armor.clear();
				mc.player.inventory.offhand.clear();
				
				mc.player.getFoodData().setFoodLevel((int) playerState.getObject("food"));
				
				mc.player.setHealth((int) playerState.getObject("health"));
				
				mc.player.experienceLevel = (int) playerState.getObject("food");
				mc.player.totalExperience = mc.player.experienceLevel * (int) playerState.getObject("experience_level");
				mc.player.experienceProgress = 0.0f;
				BlockPos oldPlayerPos = new BlockPos((BlockPos) playerState.getObject("dimension"));
				TeleporterUtils.teleport(entity, ServerLifecycleHooks.getCurrentServer().getLevel((RegistryKey<World>) playerState.getObject("dimension")), oldPlayerPos.getX(), oldPlayerPos.getY(), oldPlayerPos.getZ(), mc.player.xRot, mc.player.yRot);
			}
		}
		return super.use(world, entity, hand);
	}
}