package de.uxnr.tsoexpert.game.communication.vo.mail;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.PlayerListItemVO;

public class FriendBodyVO extends AMF3_Object {
  private PlayerListItemVO player;

  public PlayerListItemVO getPlayer() {
    return this.player;
  }
}
