package de.uxnr.tsoexpert.game.communication.vo.update;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class LootItemsVO extends AMF3_Object {
	private Collection<Object> items;
	private int mailId;
	private int shopItemId;
	private Collection<Object> uniqueIDs;

	public Collection<Object> getItems() {
		return this.items;
	}

	public int getMailId() {
		return this.mailId;
	}

	public int getShopItemId() {
		return this.shopItemId;
	}

	public Collection<Object> getUniqueIDs() {
		return this.uniqueIDs;
	}
}
