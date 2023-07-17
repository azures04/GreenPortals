package fr.azures.mod.greenportals.registry;

import fr.azures.mod.greenportals.registry.items.NeutralizerGun;
import fr.azures.mod.greenportals.registry.items.PortalGun;
import fr.azures.mod.greenportals.utils.Constants;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS =  DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
	public static final RegistryObject<Item> PORTAL_GUN = ITEMS.register("portal_gun", () -> new PortalGun(new Item.Properties()));
	public static final RegistryObject<Item> BLOCK_NEUTRALIZER_GUN = ITEMS.register("neutralizer_gun", () -> new NeutralizerGun(new Item.Properties()));
	public static final RegistryObject<Item> SAVE_POINT_DEVICE = ITEMS.register("save_point_device", () -> new NeutralizerGun(new Item.Properties()));

}
