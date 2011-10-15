package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class QuestDefinitionTriggerVO extends AMF3_Object {
	private int amount;
	private int condition;
	private String name_string;
	private int type;

	public int getAmount() {
		return this.amount;
	}

	public int getCondition() {
		return this.condition;
	}

	public String getName_string() {
		return this.name_string;
	}

	public int getType() {
		return this.type;
	}
}
