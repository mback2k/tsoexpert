package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class DataIntStringVO extends AMF3_Object {
  private String string;
  private int value;

  public String getString() {
    return this.string;
  }

  public int getValue() {
    return this.value;
  }
}
