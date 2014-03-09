package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class MapValueItemVO extends AMF3_Object {
  private int mBackgroundBlocking;
  private int mSectorId;

  public int getBackgroundBlocking() {
    return this.mBackgroundBlocking;
  }

  public int getSectorId() {
    return this.mSectorId;
  }
}
