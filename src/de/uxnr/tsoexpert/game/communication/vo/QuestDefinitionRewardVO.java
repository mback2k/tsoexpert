package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class QuestDefinitionRewardVO extends AMF3_Object {
	private int amount;
	private String name_string;
	private List<QuestTriggerVO> rewardTriggers;
	private int type;

	public int getAmount() {
		return this.amount;
	}

	public String getName_string() {
		return this.name_string;
	}

	public List<QuestTriggerVO> getRewardTriggers() {
		return this.rewardTriggers;
	}

	public int getType() {
		return this.type;
	}
}
