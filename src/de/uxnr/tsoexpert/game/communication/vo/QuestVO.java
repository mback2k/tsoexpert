package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class QuestVO extends AMF3_Object {
	private String activeQuest_string;
	private Integer activeQuestMode;
	private Collection<AMF3_Object> questTriggersFinished;
	private Boolean questWindowShowState;
	private Boolean rewardWindowShowState;
	private Double startQuestTime;

	public String getActiveQuest_string() {
		return this.activeQuest_string;
	}

	public Integer getActiveQuestMode() {
		return this.activeQuestMode;
	}

	public Collection<AMF3_Object> getQuestTriggersFinished() {
		return this.questTriggersFinished;
	}

	public Boolean getQuestWindowShowState() {
		return this.questWindowShowState;
	}

	public Boolean getRewardWindowShowState() {
		return this.rewardWindowShowState;
	}

	public Double getStartQuestTime() {
		return this.startQuestTime;
	}
}
