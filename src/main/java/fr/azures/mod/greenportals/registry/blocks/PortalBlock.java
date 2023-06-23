package fr.azures.mod.greenportals.registry.blocks;

import fr.azures.mod.greenportals.GreenPortals;
import fr.azures.mod.libs.nomorenbt.common.Data;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class PortalBlock extends Block {

	private Minecraft mc = Minecraft.getInstance();

	public PortalBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public void stepOn(World worldIn, BlockPos posIn, Entity entityIn) {
		Data blockData = GreenPortals.getInstance().blocks.getData("minecraft:overworld", posIn);
		System.out.println(posIn);
		if (mc.player != null) {
			try {
				mc.player.sendMessage(new StringTextComponent("dimX  > " + blockData.getInt("dimX")), mc.player.getUUID());
				mc.player.sendMessage(new StringTextComponent("dimY  > " + blockData.getInt("dimY")), mc.player.getUUID());
				mc.player.sendMessage(new StringTextComponent("dimZ  > " + blockData.getInt("dimZ")), mc.player.getUUID());
				mc.player.sendMessage(new StringTextComponent("dimId > " + blockData.getString("dimId")), mc.player.getUUID());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.stepOn(worldIn, posIn, entityIn);
	}

}
