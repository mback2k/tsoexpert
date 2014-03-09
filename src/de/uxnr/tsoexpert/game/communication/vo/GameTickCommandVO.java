package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.amf.v3.AMF3_Type;

public class GameTickCommandVO extends AMF3_Object {
  private AMF3_Type data;
  private int mode;
  private int playerID;
  private double time;

  public AMF3_Type getData() {
    return this.data;
  }

  public int getMode() {
    return this.mode;
  }

  public int getPlayerID() {
    return this.playerID;
  }

  public double getTime() {
    return this.time;
  }
}
