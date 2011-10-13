package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class FreeLandscapeVO extends AMF3_Object {
	private String name_string;
	private int x;
	private int y;

	public String getName_string() {
		return this.name_string;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
