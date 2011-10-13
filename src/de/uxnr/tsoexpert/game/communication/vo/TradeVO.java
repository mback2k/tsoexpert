package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class TradeVO extends AMF3_Object {
	private int mailId;
	private int receiverPlayerId;
	private ResourceVO resourceToAdd;
	private ResourceVO resourceToDeduct;
	private int senderPlayerId;

	public int getMailId() {
		return this.mailId;
	}

	public int getReceiverPlayerId() {
		return this.receiverPlayerId;
	}

	public ResourceVO getResourceToAdd() {
		return this.resourceToAdd;
	}

	public ResourceVO getResourceToDeduct() {
		return this.resourceToDeduct;
	}

	public int getSenderPlayerId() {
		return this.senderPlayerId;
	}
}
