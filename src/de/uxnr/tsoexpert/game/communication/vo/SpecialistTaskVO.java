package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class SpecialistTaskVO extends AMF3_Object {
  private int collectedTime;
  private int phase;
  private int type;

  public int getCollectedTime() {
    return this.collectedTime;
  }

  public int getPhase() {
    return this.phase;
  }

  public int getType() {
    return this.type;
  }
}
