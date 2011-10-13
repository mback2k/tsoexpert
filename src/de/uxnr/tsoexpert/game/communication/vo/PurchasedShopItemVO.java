package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class PurchasedShopItemVO extends AMF3_Object {
	private int dirtyIndicator;
	private int giftedToPlayerId;
	private int hardCurrencySpend;
	private int playerLevelAtPurchase;
	private String resourcesSpend;
	private int shopItemID;
	private double timeOfPurchase;

	public int getDirtyIndicator() {
		return this.dirtyIndicator;
	}

	public int getGiftedToPlayerId() {
		return this.giftedToPlayerId;
	}

	public int getHardCurrencySpend() {
		return this.hardCurrencySpend;
	}

	public int getPlayerLevelAtPurchase() {
		return this.playerLevelAtPurchase;
	}

	public String getResourcesSpend() {
		return this.resourcesSpend;
	}

	public int getShopItemID() {
		return this.shopItemID;
	}

	public double getTimeOfPurchase() {
		return this.timeOfPurchase;
	}
}
