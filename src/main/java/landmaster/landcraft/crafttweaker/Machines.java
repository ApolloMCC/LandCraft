package landmaster.landcraft.crafttweaker;

import crafttweaker.*;
import crafttweaker.annotations.*;
import crafttweaker.api.item.*;
import crafttweaker.api.liquid.*;
import crafttweaker.api.oredict.*;
import stanhebben.zenscript.annotations.*;

@ZenClass("mods.landcraft.machines")
@ZenRegister
public class Machines {
	@ZenMethod
	public static void setFeedstock(IOreDictEntry entry, int mass, int temp) {
		CraftTweakerAPI.apply(new AddFeedstockAction(entry.getName(), mass, temp));
	}
	
	@ZenMethod
	public static void removeFeedstock(IOreDictEntry entry) {
		CraftTweakerAPI.apply(new RemoveFeedstockAction(entry.getName()));
	}
	
	@ZenMethod
	public static void addPotRecipe(IItemStack output, IIngredient ingredient1, IIngredient ingredient2, IIngredient ingredient3, ILiquidStack fluid, int energyPerTick, int time) {
		CraftTweakerAPI.apply(new AddPotRecipeAction(output, ingredient1, ingredient2, ingredient3, fluid, energyPerTick, time));
	}
	
	@ZenMethod
	public static void removePotRecipe(IIngredient output) {
		CraftTweakerAPI.apply(new RemovePotRecipeAction(output));
	}
}
