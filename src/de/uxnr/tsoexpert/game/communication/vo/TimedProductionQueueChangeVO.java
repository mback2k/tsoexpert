package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class TimedProductionQueueChangeVO extends AMF3_Object {
  private int productionType;
  private int queueIndex;

  public int getProductionType() {
    return this.productionType;
  }

  public int getQueueIndex() {
    return this.queueIndex;
  }
}
