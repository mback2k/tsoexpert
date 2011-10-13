package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class StartTimedProductionVO extends AMF3_Object {
	private int amount;
	private int productionType;
	private String type_string;
	private Collection<UniqueID> uniqueIds;

	public int getAmount() {
		return this.amount;
	}

	public int getProductionType() {
		return this.productionType;
	}

	public String getType_string() {
		return this.type_string;
	}

	public Collection<UniqueID> getUniqueIds() {
		return this.uniqueIds;
	}
}
