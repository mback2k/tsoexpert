package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class AdventureVO extends AMF3_Object {
	private String adventureDefinitionName;
	private int adventureDuration;
	private int adventureID;
	private int ownerPlayerID;
	private Collection<PlayerVO> players;
	private int serverDownDuration;
	private double startTime;
	private int state;

	public String getAdventureDefinitionName() {
		return this.adventureDefinitionName;
	}

	public int getAdventureDuration() {
		return this.adventureDuration;
	}

	public int getAdventureID() {
		return this.adventureID;
	}

	public int getOwnerPlayerID() {
		return this.ownerPlayerID;
	}

	public Collection<PlayerVO> getPlayers() {
		return this.players;
	}

	public int getServerDownDuration() {
		return this.serverDownDuration;
	}

	public double getStartTime() {
		return this.startTime;
	}

	public int getState() {
		return this.state;
	}
}
