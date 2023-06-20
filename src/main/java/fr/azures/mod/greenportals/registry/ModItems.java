package fr.azures.mod.greenportals.registry;

import fr.azures.mod.greenportals.registry.items.PortalGun;
import fr.azures.mod.greenportals.utils.Constants;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS =  DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
	public static final RegistryObject<Item> PORTALGUN = ITEMS.register("portalgun", () -> new PortalGun(new Item.Properties()));
}
