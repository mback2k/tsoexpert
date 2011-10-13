package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class QuestDefinitionContainerVO extends AMF3_Object {
	private Collection<QuestDefinitionVO> questDefinitions;
	private String questFileName;

	public Collection<QuestDefinitionVO> getQuestDefinitions() {
		return this.questDefinitions;
	}

	public String getQuestFileName() {
		return this.questFileName;
	}
}
