package de.uxnr.tsoexpert.game.communication.vo.update;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class LootItemsVO extends AMF3_Object {
  private List<Object> items;
  private int mailId;
  private int shopItemId;
  private List<Object> uniqueIDs;

  public List<Object> getItems() {
    return this.items;
  }

  public int getMailId() {
    return this.mailId;
  }

  public int getShopItemId() {
    return this.shopItemId;
  }

  public List<Object> getUniqueIDs() {
    return this.uniqueIDs;
  }
}
