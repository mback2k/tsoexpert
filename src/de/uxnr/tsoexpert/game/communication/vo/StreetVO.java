package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class StreetVO extends AMF3_Object {
  private int grid;
  private int playerID;
  private int streetBits;
  private int streetCityLevel;
  private int streetVariation;

  public int getGrid() {
    return this.grid;
  }

  public int getPlayerID() {
    return this.playerID;
  }

  public int getStreetBits() {
    return this.streetBits;
  }

  public int getStreetCityLevel() {
    return this.streetCityLevel;
  }

  public int getStreetVariation() {
    return this.streetVariation;
  }
}
