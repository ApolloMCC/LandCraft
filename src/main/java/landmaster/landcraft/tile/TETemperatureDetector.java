package landmaster.landcraft.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TETemperatureDetector extends TileEntity implements ITickable {
	private BlockPos breederCoords = BlockPos.ORIGIN;
	
	private int[] lastRedstonePowers = new int[EnumFacing.HORIZONTALS.length];
	
	public BlockPos getBreederCoords() {
		return breederCoords;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		breederCoords = new BlockPos(compound.getInteger("breederX"), compound.getInteger("breederY"), compound.getInteger("breederZ"));
		if (compound.hasKey("lastRedstonePowers")) {
			lastRedstonePowers = compound.getIntArray("lastRedstonePowers");
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setInteger("breederX", breederCoords.getX());
		compound.setInteger("breederY", breederCoords.getY());
		compound.setInteger("breederZ", breederCoords.getZ());
		compound.setIntArray("lastRedstonePowers", lastRedstonePowers);
		return compound;
	}

	@Override
	public void update() {
		boolean doNotifyBlockUpdate = false;
		for (int i=0; i<EnumFacing.HORIZONTALS.length; ++i) {
			int curPower = world.getBlockState(pos).getWeakPower(world, pos, EnumFacing.byHorizontalIndex(i));
			if (lastRedstonePowers[i] != curPower) {
				lastRedstonePowers[i] = curPower;
				doNotifyBlockUpdate = true;
				break;
			}
		}
		if (doNotifyBlockUpdate) {
			world.notifyNeighborsOfStateChange(pos, blockType, false);
		}
	}
}
