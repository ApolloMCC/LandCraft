package landmaster.landcraft.block;

import landmaster.landcraft.content.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;

public class BlockModStairs extends BlockStairs {
	public BlockModStairs(IBlockState state, String name) {
		super(state);
		this.setTranslationKey(name).setRegistryName(name);
		this.useNeighborBrightness = true;
		this.setCreativeTab(LandCraftContent.creativeTab);
	}
}
