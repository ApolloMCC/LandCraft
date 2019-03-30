package landmaster.landcraft.crafttweaker;

import crafttweaker.*;
import landmaster.landcraft.api.*;

public class RemoveFeedstockAction implements IAction {
	private String name;
	
	public RemoveFeedstockAction(String name) {
		this.name = name;
	}
	
	@Override
	public void apply() {
		BreederFeedstock.removeOreDict(name);
	}
	
	@Override
	public String describe() {
		return "Removed "+name+" as feedstock";
	}
	
}
