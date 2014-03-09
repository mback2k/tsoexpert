package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class QuestDefinitionVO extends AMF3_Object {
  private List<QuestDefinitionHintVO> questHints;
  private String questName_string;
  private List<QuestDefinitionPostrequisitsVO> questPostrequisits;
  private List<QuestDefinitionRewardVO> questReward;
  private List<QuestDefinitionTriggerVO> questTriggers;
  private boolean showQuestWindow;
  private boolean showRewardWindow;
  private String specialType_string;

  public List<QuestDefinitionHintVO> getQuestHints() {
    return this.questHints;
  }

  public String getQuestName_string() {
    return this.questName_string;
  }

  public List<QuestDefinitionPostrequisitsVO> getQuestPostrequisits() {
    return this.questPostrequisits;
  }

  public List<QuestDefinitionRewardVO> getQuestReward() {
    return this.questReward;
  }

  public List<QuestDefinitionTriggerVO> getQuestTriggers() {
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
