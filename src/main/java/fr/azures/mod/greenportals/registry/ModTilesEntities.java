package fr.azures.mod.greenportals.registry;

import fr.azures.mod.greenportals.registry.tileentity.PortalTile;
import fr.azures.mod.greenportals.utils.Constants;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTilesEntities {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Constants.MOD_ID);
	public static final RegistryObject<TileEntityType<PortalTile>> PORTAL_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE.register("portal", () -> TileEntityType.Builder.of(PortalTile::new, ModBlocks.PORTAL_BLOCK.get()).build(null));
}
