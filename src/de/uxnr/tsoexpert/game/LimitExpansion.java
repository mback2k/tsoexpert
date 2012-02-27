package de.uxnr.tsoexpert.game;

public class LimitExpansion {
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
}
