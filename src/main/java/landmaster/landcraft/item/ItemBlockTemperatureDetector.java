package landmaster.landcraft.item;

import landmaster.landcore.api.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockTemperatureDetector extends ItemBlock {
	public ItemBlockTemperatureDetector(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
		if (!Tools.getTagSafe(stack, false).hasKey("BlockEntityTag")) {
			return false;
		}
		return super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
	}
}
