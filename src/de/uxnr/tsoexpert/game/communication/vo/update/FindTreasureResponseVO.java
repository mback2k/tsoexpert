package de.uxnr.tsoexpert.game.communication.vo.update;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.UniqueID;

public class FindTreasureResponseVO extends AMF3_Object {
  private LootItemsVO lootItemsVO;
  private UniqueID specialistUniqueId;

  public LootItemsVO getLootItemsVO() {
    return this.lootItemsVO;
  }

  public UniqueID getSpecialistUniqueId() {
    return this.specialistUniqueId;
  }
}
