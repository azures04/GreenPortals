package fr.azures.mod.greenportals.registry.tileentity;

import fr.azures.mod.greenportals.registry.ModTilesEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector4f;

public class PortalTile extends TileEntity implements ITickableTileEntity {

	private BlockPos teleportationCoordinates;
	private String teleportationDimension;
	
	public PortalTile(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public PortalTile() {
		super(ModTilesEntities.PORTAL_TILE_ENTITY_TYPE.get() );
	}

	@Override
	public void tick() {
		
	}

    /*@Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(worldPosition, worldPosition.offset(1, 2.0, 1));
    }*/
    
    @Override
    public void load(BlockState state, CompoundNBT nbt) {
    	BlockPos pos = new BlockPos(nbt.getInt("dimX"), nbt.getInt("dimY"), nbt.getInt("dimZ"));
    	String dim = nbt.getString("dimId");
    	this.teleportationCoordinates = pos;
    	this.teleportationDimension = dim;
    	super.load(state, nbt);
    }
    
    @Override
    public CompoundNBT save(CompoundNBT nbt) {
    	nbt.putInt("dimX", teleportationCoordinates.getX());
    	nbt.putInt("dimY", teleportationCoordinates.getY());
    	nbt.putInt("dimZ", teleportationCoordinates.getZ());
    	nbt.putString("dimId", teleportationDimension);
    	return super.save(nbt);
    }

	public void setTeleportationCoordinates(BlockPos teleportationPos) {
		this.teleportationCoordinates = teleportationPos;
	}
	
	public void setTeleportationDimension(String teleportationWorld) {
		this.teleportationDimension = teleportationWorld;
	}
	
	public BlockPos getTeleportationCoordinates() {
		return this.teleportationCoordinates;
	}
	
	public String getTeleportationDimension() {
		return this.teleportationDimension;
	}
  
}
