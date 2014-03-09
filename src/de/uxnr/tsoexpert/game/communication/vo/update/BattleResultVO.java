package de.uxnr.tsoexpert.game.communication.vo.update;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.ArmyVO;
import de.uxnr.tsoexpert.game.communication.vo.UniqueID;

public class BattleResultVO extends AMF3_Object {
  private ArmyVO attackingArmyVO;
  private int battleResult;
  private String battleScript;
  private int buildingHitPoints;
  private int casualtiesAttacker;
  private int casualtiesDefender;
  private int combatDuration;
  private ArmyVO defendingArmyVO;
  private int gainedXp;
  private int lostPopulationAttacker;
  private int lostPopulationDefender;
  private UniqueID specialistUniqueID;
  private int unitFightDuration;

  public ArmyVO getAttackingArmyVO() {
    return this.attackingArmyVO;
  }

  public int getBattleResult() {
    return this.battleResult;
  }

  public String getBattleScript() {
    return this.battleScript;
  }

  public int getBuildingHitPoints() {
    return this.buildingHitPoints;
  }

  public int getCasualtiesAttacker() {
    return this.casualtiesAttacker;
  }

  public int getCasualtiesDefender() {
    return this.casualtiesDefender;
  }

  public int getCombatDuration() {
    return this.combatDuration;
  }

  public ArmyVO getDefendingArmyVO() {
    return this.defendingArmyVO;
  }

  public int getGainedXp() {
    return this.gainedXp;
  }

  public int getLostPopulationAttacker() {
    return this.lostPopulationAttacker;
  }

  public int getLostPopulationDefender() {
    return this.lostPopulationDefender;
  }

  public UniqueID getSpecialistUniqueID() {
    return this.specialistUniqueID;
  }

  public int getUnitFightDuration() {
    return this.unitFightDuration;
  }
}
