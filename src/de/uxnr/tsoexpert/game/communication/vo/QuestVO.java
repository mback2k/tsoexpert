package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class QuestVO extends AMF3_Object {
	private String activeQuest_string;
	private int activeQuestMode;
	private Collection<QuestTriggerVO> questTriggersFinished;
	private boolean questWindowShowState;
	private boolean rewardWindowShowState;
	private double startQuestTime;

	public String getActiveQuest_string() {
		return this.activeQuest_string;
	}

	public int getActiveQuestMode() {
		return this.activeQuestMode;
	}

	public Collection<QuestTriggerVO> getQuestTriggersFinished() {
		return this.questTriggersFinished;
	}

	public boolean getQuestWindowShowState() {
		return this.questWindowShowState;
	}

	public boolean getRewardWindowShowState() {
		return this.rewardWindowShowState;
	}

	public double getStartQuestTime() {
		return this.startQuestTime;
	}
}
