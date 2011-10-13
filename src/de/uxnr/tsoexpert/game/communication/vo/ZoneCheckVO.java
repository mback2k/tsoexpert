package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class ZoneCheckVO extends AMF3_Object {
	private int clientTime;
	private int gameTickRefreshCounter;
	private int zoneCheckSumResources;
	private int zoneCheckSumZone;
	private int zoneId;

	public int getClientTime() {
		return this.clientTime;
	}

	public int getGameTickRefreshCounter() {
		return this.gameTickRefreshCounter;
	}

	public int getZoneCheckSumResources() {
		return this.zoneCheckSumResources;
	}

	public int getZoneCheckSumZone() {
		return this.zoneCheckSumZone;
	}

	public int getZoneId() {
		return this.zoneId;
	}
}
