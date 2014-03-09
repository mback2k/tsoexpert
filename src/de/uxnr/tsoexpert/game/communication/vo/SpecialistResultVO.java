package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class SpecialistResultVO extends AMF3_Object {
  private int type;
  private UniqueID uniqueID;
  private boolean withCosts;

  public int getType() {
    return this.type;
  }

  public UniqueID getUniqueID() {
    return this.uniqueID;
  }

  public boolean isWithCosts() {
    return this.withCosts;
  }
}
