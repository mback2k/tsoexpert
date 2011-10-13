package de.uxnr.tsoexpert.game.communication.vo.update;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class AdventureClientInfoVO extends AMF3_Object {
	private String adventureName;
	private double collectedTime;
	private int ownerPlayerID;
	private Collection<AdventurePlayerVO> players;
	private int status;
	private int zoneID;

	public String getAdventureName() {
		return this.adventureName;
	}

	public double getCollectedTime() {
		return this.collectedTime;
	}

	public int getOwnerPlayerID() {
		return this.ownerPlayerID;
	}

	public Collection<AdventurePlayerVO> getPlayers() {
		return this.players;
	}

	public int getStatus() {
		return this.status;
	}

	public int getZoneID() {
		return this.zoneID;
	}
}
