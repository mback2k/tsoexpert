package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class ZoneRefreshVO extends AMF3_Object {
  private int refreshReason;
  private String resultString;
  private ZoneVO zoneVO;

  public int getRefreshReason() {
    return this.refreshReason;
  }

  public String getResultString() {
    return this.resultString;
  }

  public ZoneVO getZoneVO() {
    return this.zoneVO;
  }
}
