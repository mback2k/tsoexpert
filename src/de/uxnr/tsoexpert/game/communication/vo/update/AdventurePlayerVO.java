package de.uxnr.tsoexpert.game.communication.vo.update;

import de.uxnr.amf.v3.AMF3_Object;

public class AdventurePlayerVO extends AMF3_Object {
  private int adventureID;
  private int avatarID;
  private int playerID;
  private String playerName;
  private int status;

  public int getAdventureID() {
    return this.adventureID;
  }

  public int getAvatarID() {
    return this.avatarID;
  }

  public int getPlayerID() {
    return this.playerID;
  }

  public String getPlayerName() {
    return this.playerName;
  }

  public int getStatus() {
    return this.status;
  }
}
