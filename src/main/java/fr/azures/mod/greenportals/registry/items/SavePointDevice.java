package fr.azures.mod.greenportals.registry.items;

import java.util.HashMap;

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
	
	private HashMap<String, Object> currentSave;
	
	public SavePointDevice(Properties properties) {
		super(properties);
		currentSave = new HashMap<String, Object>();
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		if (mc.player.isShiftKeyDown()) {
			if (currentSave.size() != 0) {
				currentSave.clear();
			}
			currentSave.put("items_inventory", PlayerUtils.getInventories(entity).get(0));
			currentSave.put("armor_inventory", PlayerUtils.getInventories(entity).get(1));
			currentSave.put("offhand_inventory", PlayerUtils.getInventories(entity).get(2));
			currentSave.put("food", PlayerUtils.getFood(entity));
			currentSave.put("health", PlayerUtils.getHealth(entity));
			currentSave.put("experience_level", PlayerUtils.getExperienceLevel(entity));
			currentSave.put("coordinates", PlayerUtils.getCoordinates(entity));
			currentSave.put("dimension", mc.player.level.dimension());
			System.out.println(currentSave.keySet());

		} else {
			System.out.println(GreenPortals.getInstance().items.getData(mc.player.getStringUUID(), this));
			if (currentSave.size() == 0) {
				mc.player.sendMessage(new StringTextComponent("Aucune position n'a été sauvegarder !"), mc.player.getUUID());
			} else {
				mc.player.inventory.items.clear();
				mc.player.inventory.armor.clear();
				mc.player.inventory.offhand.clear();
				ItemStack[] items = (ItemStack[]) currentSave.get("items_inventory");
				if (items != null) {
				    for (int i = 0; i < items.length && i < mc.player.inventory.items.size(); i++) {
				        mc.player.inventory.items.set(i, items[i]);
				    }
				}

				ItemStack[] armor = (ItemStack[]) currentSave.get("items_armor");
				if (armor != null) {
				    for (int i = 0; i < armor.length && i < mc.player.inventory.armor.size(); i++) {
				        mc.player.inventory.armor.set(i, armor[i]);
				    }
				}

				ItemStack[] offhand = (ItemStack[]) currentSave.get("items_offhand");
				if (offhand != null) {
				    for (int i = 0; i < offhand.length && i < mc.player.inventory.offhand.size(); i++) {
				        mc.player.inventory.offhand.set(i, offhand[i]);
				    }
				}

				mc.player.getFoodData().setFoodLevel((Integer) currentSave.get("food"));
				
				mc.player.setHealth((float) currentSave.get("health"));
				
				mc.player.experienceLevel = (Integer) currentSave.get("food");
				mc.player.totalExperience = mc.player.experienceLevel * (Integer) currentSave.get("experience_level");
				mc.player.experienceProgress = 0.0f;
				BlockPos oldPlayerPos = new BlockPos((BlockPos) currentSave.get("coordinates"));
				TeleporterUtils.teleport(entity, ServerLifecycleHooks.getCurrentServer().getLevel((RegistryKey<World>) currentSave.get("dimension")), oldPlayerPos.getX(), oldPlayerPos.getY(), oldPlayerPos.getZ(), mc.player.xRot, mc.player.yRot);
			}
		}
		return super.use(world, entity, hand);
	}
}