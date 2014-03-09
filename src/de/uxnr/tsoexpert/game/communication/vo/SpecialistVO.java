package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class SpecialistVO extends AMF3_Object {
  private ArmyVO armyVO;
  private int battlesWon;
  private int buildingsDestroyed;
  private int currentHitPoints;
  private int diceBonus;
  private int faceType;
  private int garrisonBuildingGridPos;
  private int playerID;
  private int retreatThreshold;
  private int specialistType;
  private SpecialistTaskVO task;
  private UniqueID uniqueID;
  private int unitsDefeated;
  private int xp;
  private int xpProduced;

  public ArmyVO getArmyVO() {
    return this.armyVO;
  }

  public int getBattlesWon() {
    return this.battlesWon;
  }

  public int getBuildingsDestroyed() {
    return this.buildingsDestroyed;
  }

  public int getCurrentHitPoints() {
    return this.currentHitPoints;
  }

  public int getDiceBonus() {
    return this.diceBonus;
  }

  public int getFaceType() {
    return this.faceType;
  }

  public int getGarrisonBuildingGridPos() {
    return this.garrisonBuildingGridPos;
  }

  public int getPlayerID() {
    return this.playerID;
  }

  public int getRetreatThreshold() {
    return this.retreatThreshold;
  }

  public int getSpecialistType() {
    return this.specialistType;
  }

  public SpecialistTaskVO getTask() {
    return this.task;
  }

  public UniqueID getUniqueID() {
    return this.uniqueID;
  }

  public int getUnitsDefeated() {
    return this.unitsDefeated;
  }

  public int getXp() {
    return this.xp;
  }

  public int getXpProduced() {
    return this.xpProduced;
  }
}
