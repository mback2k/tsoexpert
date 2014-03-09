package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class QuestDefinitionContainerVO extends AMF3_Object {
  private List<QuestDefinitionVO> questDefinitions;
  private String questFileName;

  public List<QuestDefinitionVO> getQuestDefinitions() {
    return this.questDefinitions;
  }

  public String getQuestFileName() {
    return this.questFileName;
  }
}
