package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class ItemTradeOfferVO extends AMF3_Object {
	private BuffVO buff;
	private ResourceVO costs;
	private int receipientId;

	public BuffVO getBuff() {
		return this.buff;
	}

	public ResourceVO getCosts() {
		return this.costs;
	}

	public int getReceipientId() {
		return this.receipientId;
	}
}
