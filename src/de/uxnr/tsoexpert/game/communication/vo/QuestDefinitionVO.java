package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class QuestDefinitionVO extends AMF3_Object {
	private Collection<QuestDefinitionHintVO> questHints;
	private String questName_string;
	private Collection<QuestDefinitionPostrequisitsVO> questPostrequisits;
	private Collection<QuestDefinitionRewardVO> questReward;
	private Collection<QuestDefinitionTriggerVO> questTriggers;
	private boolean showQuestWindow;
	private boolean showRewardWindow;
	private String specialType_string;

	public Collection<QuestDefinitionHintVO> getQuestHints() {
		return this.questHints;
	}

	public String getQuestName_string() {
		return this.questName_string;
	}

	public Collection<QuestDefinitionPostrequisitsVO> getQuestPostrequisits() {
		return this.questPostrequisits;
	}

	public Collection<QuestDefinitionRewardVO> getQuestReward() {
		return this.questReward;
	}

	public Collection<QuestDefinitionTriggerVO> getQuestTriggers() {
		return this.questTriggers;
	}

	public String getSpecialType_string() {
		return this.specialType_string;
	}

	public boolean isShowQuestWindow() {
		return this.showQuestWindow;
	}

	public boolean isShowRewardWindow() {
		return this.showRewardWindow;
	}
}
