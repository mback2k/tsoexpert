package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class BuyShopItemVO extends AMF3_Object {
  private int giftedPlayerID;
  private int itemID;
  private List<BuyOneClickShopItemVO> shopItemContent_vector;

  public int getGiftedPlayerID() {
    return this.giftedPlayerID;
  }

  public int getItemID() {
    return this.itemID;
  }

  public List<BuyOneClickShopItemVO> getShopItemContent_vector() {
    return this.shopItemContent_vector;
  }
}
