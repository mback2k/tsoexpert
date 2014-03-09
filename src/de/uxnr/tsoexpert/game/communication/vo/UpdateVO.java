package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class UpdateVO extends AMF3_Object {
  private int synchronisationErrorBitField;
  private ZoneCheckVO zoneCheckVO;

  public int getSynchronisationErrorBitField() {
    return this.synchronisationErrorBitField;
  }

  public ZoneCheckVO getZoneCheckVO() {
    return this.zoneCheckVO;
  }
}
