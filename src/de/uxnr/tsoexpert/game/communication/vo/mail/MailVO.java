package de.uxnr.tsoexpert.game.communication.vo.mail;

import de.uxnr.amf.v3.AMF3_Object;

public class MailVO extends AMF3_Object {
	private String subject;
	private int type;
	private int senderId;
	private int id;
	private String senderName;
	private boolean read;
	private Object body;
	private String reciepientId;
	private int deletedAt;
	private double timestamp;

	public String getSubject() {
		return this.subject;
	}

	public int getType() {
		return this.type;
	}

	public int getSenderId() {
		return this.senderId;
	}

	public int getId() {
		return this.id;
	}

	public String getSenderName() {
		return this.senderName;
	}

	public boolean isRead() {
		return this.read;
	}

	public Object getBody() {
		return this.body;
	}

	public String getReciepientId() {
		return this.reciepientId;
	}

	public int getDeletedAt() {
		return this.deletedAt;
	}

	public double getTimestamp() {
		return this.timestamp;
	}
}
