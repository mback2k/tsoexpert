package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class ServerClientUpdateVO extends AMF3_Object {
	private double serverClientSynchronizationTime;
	private int zoneId;

	public double getServerClientSynchronizationTime() {
		return this.serverClientSynchronizationTime;
	}

	public int getZoneId() {
		return this.zoneId;
	}
}
