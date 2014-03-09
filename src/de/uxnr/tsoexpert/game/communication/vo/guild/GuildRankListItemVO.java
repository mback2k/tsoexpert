package de.uxnr.tsoexpert.game.communication.vo.guild;

import de.uxnr.amf.v3.AMF3_Object;

public class GuildRankListItemVO extends AMF3_Object {
  private int id;
  private String name;
  private int position;

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public int getPosition() {
    return this.position;
  }
}
