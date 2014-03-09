package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.amf.v3.AMF3_Type;

public class ServerResponse extends AMF3_Object {
  private AMF3_Type data;
  private int type;

  public AMF3_Type getData() {
    return this.data;
  }

  public int getType() {
    return this.type;
  }
}
