package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class SectorVO extends AMF3_Object {
  private int cityLevelAtWhichSectorIsActivated;
  private int explorePriority;
  private int playerID;
  private int sectorID;

  public int getCityLevelAtWhichSectorIsActivated() {
    return this.cityLevelAtWhichSectorIsActivated;
  }

  public int getExplorePriority() {
    return this.explorePriority;
  }

  public int getPlayerID() {
    return this.playerID;
  }

  public int getSectorID() {
    return this.sectorID;
  }
}
