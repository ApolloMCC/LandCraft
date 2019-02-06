package landmaster.landcraft.item;

import landmaster.landcraft.content.*;
import net.minecraft.item.*;

public class ItemModHoe extends ItemHoe {
	public ItemModHoe(ToolMaterial material, String name) {
		super(material);
		setTranslationKey(name).setRegistryName(name);
		setCreativeTab(LandCraftContent.creativeTab);
	}
}
