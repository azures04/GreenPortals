package fr.azures.mod.greenportals;

import org.apache.commons.lang3.tuple.Pair;
import fr.azures.mod.greenportals.registry.ModBlocks;
import fr.azures.mod.greenportals.registry.ModItems;
import fr.azures.mod.greenportals.utils.Constants;
import fr.azures.mod.libs.nomorenbt.client.LocalStorage;
import fr.azures.mod.libs.nomorenbt.client.LocalStorage.Blocks;
import fr.azures.mod.libs.nomorenbt.client.LocalStorage.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
	
@SuppressWarnings("UtilityClassWithPublicConstructor")
@Mod(Constants.MOD_ID)
public class GreenPortals {
	
	public static LocalStorage localStorage;
	public Blocks blocks;
	public Items items;
	private static GreenPortals instance;
	
	public GreenPortals() throws Exception {
		this.localStorage = new LocalStorage();
		this.blocks = localStorage.blocks;
		this.items = localStorage.items;
		this.instance = this;
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModItems.ITEMS.register(bus);
		ModBlocks.BLOCKS.register(bus);
		ModLoadingContext.get().registerExtensionPoint(
			ExtensionPoint.DISPLAYTEST,
			() -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true )
		);
	}
	
	public static GreenPortals getInstance() {
		return instance;
	}
	
}
