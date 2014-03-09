package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class BuildingVO extends AMF3_Object {
  private ArmyVO armyVO;
  private List<BuffVO> buffs;
  private double buildingCreationTime;
  private int buildingGrid;
  private int buildingMode;
  private String buildingName_string;
  private int buildingProgress;
  private double destructionTime;
  private int hitPoints;
  private boolean initialSetOnXMLMap;
  private boolean isBought;
  private boolean isEngagedInComba;
  private boolean isProductionActive;
  private double lastRepairTime;
  private int offsetX;
  private int offsetY;
  private int origin;
  private int playerID;
  private int recoveringHitPoints;
  private BuffVO recurringBuffVO;
  private int startWorkCounter;
  private boolean upgradeIsInProgress;
  private int upgradeLevel;
  private int upgradeProgress;
  private double upgradeStartTime;

  public ArmyVO getArmyVO() {
    return this.armyVO;
  }

  public List<BuffVO> getBuffs() {
    return this.buffs;
  }

  public double getBuildingCreationTime() {
    return this.buildingCreationTime;
  }

  public int getBuildingGrid() {
    return this.buildingGrid;
  }

  public int getBuildingMode() {
    return this.buildingMode;
  }

  public String getBuildingName_string() {
    return this.buildingName_string;
  }

  public int getBuildingProgress() {
    return this.buildingProgress;
  }

  public double getDestructionTime() {
    return this.destructionTime;
  }

  public int getHitPoints() {
    return this.hitPoints;
  }

  public boolean getInitialSetOnXMLMap() {
    return this.initialSetOnXMLMap;
  }

  public boolean getIsBought() {
    return this.isBought;
  }

  public boolean getIsEngagedInComba() {
    return this.isEngagedInComba;
  }

  public boolean getIsProductionActive() {
    return this.isProductionActive;
  }

  public double getLastRepairTime() {
    return this.lastRepairTime;
  }

  public int getOffsetX() {
    return this.offsetX;
  }

  public int getOffsetY() {
    return this.offsetY;
  }

  public int getOrigin() {
    return this.origin;
  }

  public int getPlayerID() {
    return this.playerID;
  }

  public int getRecoveringHitPoints() {
    return this.recoveringHitPoints;
  }

  public BuffVO getRecurringBuffVO() {
    return this.recurringBuffVO;
  }

  public int getStartWorkCounter() {
    return this.startWorkCounter;
  }

  public boolean getUpgradeIsInProgress() {
    return this.upgradeIsInProgress;
  }

  public int getUpgradeLevel() {
    return this.upgradeLevel;
  }

  public int getUpgradeProgress() {
    return this.upgradeProgress;
  }

  public double getUpgradeStartTime() {
    return this.upgradeStartTime;
  }
}
