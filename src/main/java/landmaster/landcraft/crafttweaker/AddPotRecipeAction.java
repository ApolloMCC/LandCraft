package landmaster.landcraft.crafttweaker;

import crafttweaker.*;
import crafttweaker.api.item.*;
import crafttweaker.api.liquid.*;
import landmaster.landcraft.api.*;
import net.minecraft.item.*;
import net.minecraftforge.fluids.*;

public class AddPotRecipeAction implements IAction {
	private IItemStack output;
	private IIngredient ingredient1, ingredient2, ingredient3;
	private ILiquidStack fluid;
	private int energyPerTick, time;
	
	public AddPotRecipeAction(IItemStack output, IIngredient ingredient1, IIngredient ingredient2, IIngredient ingredient3, ILiquidStack fluid, int energyPerTick, int time) {
		this.output = output;
		this.ingredient1 = ingredient1;
		this.ingredient2 = ingredient2;
		this.ingredient3 = ingredient3;
		this.fluid = fluid;
		this.energyPerTick = energyPerTick;
		this.time = time;
	}
	
	@Override
	public void apply() {
		PotRecipes.addRecipe(new MTPotRecipeProcess(ingredient1, ingredient2, ingredient3, (FluidStack)fluid.getInternal(),
				new PotRecipes.RecipeOutput((ItemStack)output.getInternal(), fluid.getAmount(), energyPerTick, time)));
	}
	
	@Override
	public String describe() {
		return "Adding pot recipe for "+this.output;
	}
}
