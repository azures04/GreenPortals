package fr.azures.mod.greenportals;

import org.apache.commons.lang3.tuple.Pair;

import fr.azures.mod.greenportals.registry.ModBlocks;
import fr.azures.mod.greenportals.registry.ModItems;
import fr.azures.mod.greenportals.registry.ModTilesEntities;
import fr.azures.mod.greenportals.utils.Constants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;

@SuppressWarnings("UtilityClassWithPublicConstructor")
@Mod(Constants.MOD_ID)
public class GreenPortals {
	public GreenPortals() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModItems.ITEMS.register(bus);
		ModBlocks.BLOCKS.register(bus);
		ModTilesEntities.TILE_ENTITY_TYPE.register(bus);
		ModLoadingContext.get().registerExtensionPoint(
			ExtensionPoint.DISPLAYTEST,
			() -> Pair.of( () -> FMLNetworkConstants.IGNORESERVERONLY, ( remote, isServer ) -> true )
		);
		Minecraft.getInstance().allowsMultiplayer();
	}
}
