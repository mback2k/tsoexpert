package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class TimedProductionVO extends AMF3_Object {
	private int amount;
	private double collectedTime;
	private int playerId;
	private int producedItems;
	private int productionType;
	private String type_string;
	private UniqueID uniqueId;
	private List<UniqueID> uniqueIds;

	public int getAmount() {
		return this.amount;
	}

	public double getCollectedTime() {
		return this.collectedTime;
	}

	public int getPlayerId() {
		return this.playerId;
	}

	public int getProducedItems() {
		return this.producedItems;
	}

	public int getProductionType() {
		return this.productionType;
	}

	public String getType_string() {
		return this.type_string;
	}

	public UniqueID getUniqueId() {
		return this.uniqueId;
	}

	public List<UniqueID> getUniqueIds() {
		return this.uniqueIds;
	}
}
