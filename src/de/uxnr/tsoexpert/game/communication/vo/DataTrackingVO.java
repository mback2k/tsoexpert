package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class DataTrackingVO extends AMF3_Object {
  private int amount;
  private List<DataIntStringVO> dataTracking;

  public int getAmount() {
    return this.amount;
  }

  public List<DataIntStringVO> getDataTracking() {
    return this.dataTracking;
  }
}
