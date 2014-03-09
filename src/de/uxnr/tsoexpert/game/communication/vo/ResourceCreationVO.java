package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class ResourceCreationVO extends AMF3_Object {
  private boolean assignedSettler;
  private int depositBuildingGridPos;
  private int gatheredResource;
  private int pathPos;
  private PathVO pathVO;
  private int playerId;
  private int productionState;
  private int protocollResourceCreationLastBuildingMode;
  private int protocollResourceCreationProcessCntr;
  private boolean remove;
  private int resourceCreationHouseGrid;
  private int resourceDefinitionID;
  private int settlerKIState;

  public int getDepositBuildingGridPos() {
    return this.depositBuildingGridPos;
  }

  public int getGatheredResource() {
    return this.gatheredResource;
  }

  public int getPathPos() {
    return this.pathPos;
  }

  public PathVO getPathVO() {
    return this.pathVO;
  }

  public int getPlayerId() {
    return this.playerId;
  }

  public int getProductionState() {
    return this.productionState;
  }

  public int getProtocollResourceCreationLastBuildingMode() {
    return this.protocollResourceCreationLastBuildingMode;
  }

  public int getProtocollResourceCreationProcessCntr() {
    return this.protocollResourceCreationProcessCntr;
  }

  public int getResourceCreationHouseGrid() {
    return this.resourceCreationHouseGrid;
  }

  public int getResourceDefinitionID() {
    return this.resourceDefinitionID;
  }

  public int getSettlerKIState() {
    return this.settlerKIState;
  }

  public boolean isAssignedSettler() {
    return this.assignedSettler;
  }

  public boolean isRemove() {
    return this.remove;
  }
}
