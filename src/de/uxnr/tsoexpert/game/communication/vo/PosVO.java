package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class PosVO extends AMF3_Object {
  private double x;
  private double y;

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }
}
