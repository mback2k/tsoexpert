package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class SectorDiscoveryVO extends AMF3_Object {
	private int discoveryType;
	private int sectorID;

	public int getDiscoveryType() {
		return this.discoveryType;
	}

	public int getSectorID() {
		return this.sectorID;
	}
}
