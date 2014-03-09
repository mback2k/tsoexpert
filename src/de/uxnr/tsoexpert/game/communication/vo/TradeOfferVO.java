package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class TradeOfferVO extends AMF3_Object {
  private ResourceVO costs;
  private ResourceVO offer;
  private int receipientId;

  public ResourceVO getCosts() {
    return this.costs;
  }

  public ResourceVO getOffer() {
    return this.offer;
  }

  public int getReceipientId() {
    return this.receipientId;
  }
}
