package de.uxnr.tsoexpert.game.communication.vo.update;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class AdventureClientInfoVO extends AMF3_Object {
  private String adventureName;
  private double collectedTime;
  private int ownerPlayerID;
  private List<AdventurePlayerVO> players;
  private int status;
  private int zoneID;

  public String getAdventureName() {
    return this.adventureName;
  }

  public double getCollectedTime() {
    return this.collectedTime;
  }

  public int getOwnerPlayerID() {
    return this.ownerPlayerID;
  }

  public List<AdventurePlayerVO> getPlayers() {
    return this.players;
  }

  public int getStatus() {
    return this.status;
  }

  public int getZoneID() {
    return this.zoneID;
  }
}
