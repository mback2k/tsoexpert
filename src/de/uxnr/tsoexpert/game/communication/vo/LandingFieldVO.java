package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class LandingFieldVO extends AMF3_Object {
	private int grid;
	private int id;

	public int getGrid() {
		return this.grid;
	}

	public int getId() {
		return this.id;
	}
}
