package landmaster.landcraft.item;

import landmaster.landcraft.content.*;
import landmaster.landcraft.util.*;
import net.minecraft.creativetab.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class ItemLandiaIngot extends Item {
	public ItemLandiaIngot() {
		setHasSubtypes(true);
		setTranslationKey("landia_ingot").setRegistryName("landia_ingot");
		setCreativeTab(LandCraftContent.creativeTab);
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		return super.getTranslationKey(stack) + "." + LandiaOreType.values()[stack.getMetadata()];
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(tab)) {
			for (int i=0; i<LandiaOreType.values().length; ++i) {
				subItems.add(new ItemStack(this, 1, i));
			}
		}
	}
}
