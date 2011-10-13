package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class LandscapeVO extends AMF3_Object {
	private int grid;
	private String name_string;
	private int playerID;

	public int getGrid() {
		return this.grid;
	}

	public String getName_string() {
		return this.name_string;
	}

	public int getPlayerID() {
		return this.playerID;
	}
}
