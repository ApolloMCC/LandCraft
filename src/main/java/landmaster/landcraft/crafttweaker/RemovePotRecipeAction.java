package landmaster.landcraft.crafttweaker;

import crafttweaker.*;
import crafttweaker.api.item.*;
import crafttweaker.mc1120.item.*;
import landmaster.landcraft.api.*;

public class RemovePotRecipeAction implements IAction {
	private IIngredient output;
	
	public RemovePotRecipeAction(IIngredient output) {
		this.output = output;
	}
	
	@Override
	public void apply() {
		PotRecipes.getRecipeList().removeIf(proc -> proc.possibleOutputs().stream()
				.anyMatch(ro -> output.matches(MCItemStack.createNonCopy(ro.out))));
	}
	
	@Override
	public String describe() {
		return "Removing pot recipe for "+this.output;
	}
	
}
