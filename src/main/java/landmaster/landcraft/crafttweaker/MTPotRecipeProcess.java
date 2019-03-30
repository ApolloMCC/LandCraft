package landmaster.landcraft.crafttweaker;

import java.util.*;

import com.google.common.collect.*;

import crafttweaker.api.item.*;
import crafttweaker.mc1120.item.*;
import landmaster.landcraft.api.*;
import net.minecraft.item.*;
import net.minecraftforge.fluids.*;

public class MTPotRecipeProcess extends PotRecipes.BasicRecipeProcess {
	public final IIngredient ingr1, ingr2, ingr3;
	public final FluidStack fs;
	
	public MTPotRecipeProcess(IIngredient ingr1, IIngredient ingr2, IIngredient ingr3, FluidStack fs, PotRecipes.RecipeOutput out) {
		super(out);
		this.ingr1 = ingr1;
		this.ingr2 = ingr2;
		this.ingr3 = ingr3;
		this.fs = fs;
		if (this.fs != null) this.fs.amount = out.fluidPerTick;
	}
	
	@Override
	public boolean match(ItemStack in1, ItemStack in2, ItemStack in3, FluidStack fs) {
		boolean found = false;
		for (List<IIngredient> perm: Collections2.permutations(Arrays.asList(ingr1, ingr2, ingr3))) {
			if (perm.get(0).matches(MCItemStack.createNonCopy(in1))
					&& perm.get(1).matches(MCItemStack.createNonCopy(in2))
					&& perm.get(2).matches(MCItemStack.createNonCopy(in3))) {
				found = true;
				break;
			}
		}
		return found && fs.equals(this.fs);
	}
	
}
