package de.uxnr.tsoexpert.game.communication.vo.update;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.UniqueID;

public class ExploredSectorVO extends AMF3_Object {
  private ExploredSectorDataVO sectorData;
  private int sectorID;
  private UniqueID specialistID;

  public ExploredSectorDataVO getSectorData() {
    return this.sectorData;
  }

  public int getSectorID() {
    return this.sectorID;
  }

  public UniqueID getSpecialistID() {
    return this.specialistID;
  }
}
