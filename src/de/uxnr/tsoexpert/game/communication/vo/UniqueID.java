package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class UniqueID extends AMF3_Object {
  private int uniqueID1;
  private int uniqueID2;

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;

    if (!(obj instanceof UniqueID))
      return false;

    UniqueID uniqueID = (UniqueID) obj;

    if (this.uniqueID1 != uniqueID.uniqueID1)
      return false;

    if (this.uniqueID2 != uniqueID.uniqueID2)
      return false;

    return true;
  }

  public int getUniqueID1() {
    return this.uniqueID1;
  }

  public int getUniqueID2() {
    return this.uniqueID2;
  }
}
