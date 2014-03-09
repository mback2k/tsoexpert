package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class BuffVO extends AMF3_Object {
  private int amount;
  private int applianceMode;
  private String buffName_string;
  private int recurringChance;
  private int remaining;
  private String resourceName_string;
  private double startTime;
  private int uniqueId1;
  private int uniqueId2;

  public int getAmount() {
    return this.amount;
  }

  public int getApplianceMode() {
    return this.applianceMode;
  }

  public String getBuffName_string() {
    return this.buffName_string;
  }

  public int getRecurringChance() {
    return this.recurringChance;
  }

  public int getRemaining() {
    return this.remaining;
  }

  public String getResourceName_string() {
    return this.resourceName_string;
  }

  public double getStartTime() {
    return this.startTime;
  }

  public int getUniqueId1() {
    return this.uniqueId1;
  }

  public int getUniqueId2() {
    return this.uniqueId2;
  }
}
