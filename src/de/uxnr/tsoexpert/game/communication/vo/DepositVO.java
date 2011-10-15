package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class DepositVO extends AMF3_Object {
	private int accessible;
	private int amount;
	private int depositGroupdId;
	private int emptied; // TODO uint
	private String goSetListName_string;
	private int gridIdx; // TODO uint
	private int maxAmount;
	private String name_string;

	public int getAccessible() {
		return this.accessible;
	}

	public int getAmount() {
		return this.amount;
	}

	public int getDepositGroupdId() {
		return this.depositGroupdId;
	}

	public int getEmptied() {
		return this.emptied;
	}

	public String getGoSetListName_string() {
		return this.goSetListName_string;
	}

	public int getGridIdx() {
		return this.gridIdx;
	}

	public int getMaxAmount() {
		return this.maxAmount;
	}

	public String getName_string() {
		return this.name_string;
	}
}
