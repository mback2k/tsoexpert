package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class BuyOneClickShopItemVO extends AMF3_Object {
	private int buildingGridIdx;
	private int itemId;

	public int getBuildingGridIdx() {
		return this.buildingGridIdx;
	}

	public int getItemId() {
		return this.itemId;
	}
}
