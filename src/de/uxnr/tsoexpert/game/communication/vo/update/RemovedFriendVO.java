package de.uxnr.tsoexpert.game.communication.vo.update;

import de.uxnr.amf.v3.AMF3_Object;

public class RemovedFriendVO extends AMF3_Object {
  private int friendRemoverID;
  private int removedFriendID;

  public int getFriendRemoverID() {
    return this.friendRemoverID;
  }

  public int getRemovedFriendID() {
    return this.removedFriendID;
  }
}
