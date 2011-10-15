package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class QuestDefinitionHintVO extends AMF3_Object {
	private int direction;
	private String name_string;
	private int offsetX;
	private int offsetY;
	private String pointTo;

	public int getDirection() {
		return this.direction;
	}

	public String getName_string() {
		return this.name_string;
	}

	public int getOffsetX() {
		return this.offsetX;
	}

	public int getOffsetY() {
		return this.offsetY;
	}

	public String getPointTo() {
		return this.pointTo;
	}
}
