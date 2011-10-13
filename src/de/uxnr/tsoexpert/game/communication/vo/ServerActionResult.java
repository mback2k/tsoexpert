package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.amf.v3.AMF3_Type;

public class ServerActionResult extends AMF3_Object {
	private double clientTime;
	private AMF3_Type data;
	private int errorCode;

	public double getClientTime() {
		return this.clientTime;
	}

	public AMF3_Type getData() {
		return this.data;
	}

	public int getErrorCode() {
		return this.errorCode;
	}
}
