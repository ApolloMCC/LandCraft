package landmaster.landcraft.jei;

import java.util.*;
import java.util.stream.*;

import landmaster.landcraft.crafttweaker.*;
import mezz.jei.api.ingredients.*;
import net.minecraft.client.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.*;
import net.minecraft.util.text.*;
import net.minecraftforge.fluids.*;

public class PotMTRecipeJEI extends PotRecipeJEI {
	private MTPotRecipeProcess prc;
	
	public PotMTRecipeJEI(MTPotRecipeProcess prc) {
		super(prc);
		this.prc = prc;
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRenderer.drawString(TextFormatting.DARK_RED.toString()+TextFormatting.BOLD
				+I18n.format("info.pot.jei.energy_and_time", prc.out.energyPerTick, prc.out.time),
				65, 58, 0x000000);
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class,
				Arrays.asList(
						prc.ingr1.getItems().stream().map(stack -> (ItemStack)stack.getInternal()).collect(Collectors.toList()),
						prc.ingr2.getItems().stream().map(stack -> (ItemStack)stack.getInternal()).collect(Collectors.toList()),
						prc.ingr3.getItems().stream().map(stack -> (ItemStack)stack.getInternal()).collect(Collectors.toList())
						));
		ingredients.setInput(FluidStack.class, prc.fs);
		ingredients.setOutput(ItemStack.class, prc.out.out);
	}
	
}
