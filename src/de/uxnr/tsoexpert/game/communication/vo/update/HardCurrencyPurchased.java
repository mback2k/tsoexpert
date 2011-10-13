package de.uxnr.tsoexpert.game.communication.vo.update;

import de.uxnr.amf.v3.AMF3_Object;

public class HardCurrencyPurchased extends AMF3_Object {
	private int mAmount;
	private int mHardCurrencyPurchasedID;

	public int getAmount() {
		return this.mAmount;
	}

	public int getHardCurrencyPurchasedID() {
		return this.mHardCurrencyPurchasedID;
	}
}
