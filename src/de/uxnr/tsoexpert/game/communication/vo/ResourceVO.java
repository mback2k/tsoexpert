package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class ResourceVO extends AMF3_Object {
  private int amount;
  private String name_string;

  public int getAmount() {
    return this.amount;
  }

  public String getName_string() {
    return this.name_string;
  }
}
