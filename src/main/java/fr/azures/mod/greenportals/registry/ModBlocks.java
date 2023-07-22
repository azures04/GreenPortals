	package fr.azures.mod.greenportals.registry;

import java.util.function.Supplier;

import fr.azures.mod.greenportals.registry.blocks.NeutralizedBlock;
import fr.azures.mod.greenportals.registry.blocks.PortalBlock;
import fr.azures.mod.greenportals.utils.Constants;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

	public static final RegistryObject<Block> PORTAL_BLOCK = createBlock("portal_block", () -> new PortalBlock(AbstractBlock.Properties.of(Material.STONE)));
	public static final RegistryObject<Block> NEUTRALIZED_BLOCK = createBlock("neutralized_block", () -> new NeutralizedBlock(AbstractBlock.Properties.of(Material.STONE)));

	public static RegistryObject<Block> createBlock(String name, Supplier<? extends Block> supplier) {
		RegistryObject<Block> block = BLOCKS.register(name, supplier);
		ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
		return block;
	}
}
