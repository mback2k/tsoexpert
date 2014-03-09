package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.amf.v3.AMF3_Type;

public class ServerAction extends AMF3_Object {
  private AMF3_Type data;
  private int endGrid;
  private int grid;
  private int type;

  public AMF3_Type getData() {
    return this.data;
  }

  public int getEndGrid() {
    return this.endGrid;
  }

  public int getGrid() {
    return this.grid;
  }

  public int getType() {
    return this.type;
  }
}
