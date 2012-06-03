package de.uxnr.tsoexpert.game;

import org.w3c.dom.Node;

public class LimitExpansion implements Parsable{
	private Building building;
	private int amount;

	public LimitExpansion(Building building, int amount) {
		this.building = building;
		this.amount = amount;
	}

	public Building getBuilding() {
		return this.building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public void parse(Node node) throws InvalidGameSettingsException {
		// TODO Auto-generated method stub
		
	}
}
