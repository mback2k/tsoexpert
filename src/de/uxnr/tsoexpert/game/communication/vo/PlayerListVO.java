package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class PlayerListVO extends AMF3_Object {
  private List<PlayerListItemVO> players;

  public List<PlayerListItemVO> getPlayers() {
    return this.players;
  }
}
