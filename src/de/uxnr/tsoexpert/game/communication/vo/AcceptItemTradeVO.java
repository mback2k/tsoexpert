package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class AcceptItemTradeVO extends AMF3_Object {
	private BuffVO buff;
	private ResourceVO costs;
	private int mailId;
	private int receiverID;

	public BuffVO getBuff() {
		return this.buff;
	}

	public ResourceVO getCosts() {
		return this.costs;
	}

	public int getMailId() {
		return this.mailId;
	}

	public int getReceiverID() {
		return this.receiverID;
	}
}
