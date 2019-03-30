package landmaster.landcraft.crafttweaker;

import crafttweaker.*;
import landmaster.landcraft.api.*;

public class AddFeedstockAction implements IAction {
	private String entryName;
	private int mass, temp;
	
	public AddFeedstockAction(String entryName, int mass, int temp) {
		this.entryName = entryName;
		this.mass = mass;
		this.temp = temp;
	}

	@Override
	public void apply() {
		BreederFeedstock.addOreDict(entryName, mass, temp);
	}

	@Override
	public String describe() {
		return "Added/modified "+entryName+" as feedstock for breeder reactor with mass="+mass+", temp="+temp;
	}
	
}
