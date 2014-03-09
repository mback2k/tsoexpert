package de.uxnr.tsoexpert.game.communication.vo.mail;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.ResourceVO;

public class TradeBodyVO extends AMF3_Object {
  private ResourceVO costs;
  private ResourceVO offer;

  public ResourceVO getCosts() {
    return this.costs;
  }

  public ResourceVO getOffer() {
    return this.offer;
  }
}
