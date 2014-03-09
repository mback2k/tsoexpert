package de.uxnr.tsoexpert.game.communication.vo.guild;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class GuildEditValueVO extends AMF3_Object {
  private String newValue;
  private List<Object> parameters; // TODO
  private int type;

  public String getNewValue() {
    return this.newValue;
  }

  public List<Object> getParameters() {
    return this.parameters;
  }

  public int getType() {
    return this.type;
  }
}
