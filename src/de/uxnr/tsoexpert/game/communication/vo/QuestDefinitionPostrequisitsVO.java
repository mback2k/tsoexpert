package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class QuestDefinitionPostrequisitsVO extends AMF3_Object {
	private double startDelay;
	private String startQuest_string;

	public double getStartDelay() {
		return this.startDelay;
	}

	public String getStartQuest_string() {
		return this.startQuest_string;
	}
}
