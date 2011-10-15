package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class SquadVO extends AMF3_Object {
	private int amount;
	private int currentHitPoints;
	private Object militaryUnitDescription; // TODO cMilitaryUnitDescription
	private int mTotalHealth;
	private String name_string;

	public int getAmount() {
		return this.amount;
	}

	public int getCurrentHitPoints() {
		return this.currentHitPoints;
	}

	public Object getMilitaryUnitDescription() {
		return this.militaryUnitDescription;
	}

	public int getmTotalHealth() {
		return this.mTotalHealth;
	}

	public String getName_string() {
		return this.name_string;
	}
}
