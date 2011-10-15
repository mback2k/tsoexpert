package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class BuyShopItemVO extends AMF3_Object {
	private int giftedPlayerID;
	private int itemID;
	private Collection<BuyOneClickShopItemVO> shopItemContent_vector;

	public int getGiftedPlayerID() {
		return this.giftedPlayerID;
	}

	public int getItemID() {
		return this.itemID;
	}

	public Collection<BuyOneClickShopItemVO> getShopItemContent_vector() {
		return this.shopItemContent_vector;
	}
}
