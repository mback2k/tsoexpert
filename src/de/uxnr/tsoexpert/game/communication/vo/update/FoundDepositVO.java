package de.uxnr.tsoexpert.game.communication.vo.update;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.DepositVO;
import de.uxnr.tsoexpert.game.communication.vo.UniqueID;

public class FoundDepositVO extends AMF3_Object {
  private DepositVO depositVO;
  private int exploredDepositResult;
  private UniqueID specialistID;

  public DepositVO getDepositVO() {
    return this.depositVO;
  }

  public int getExploredDepositResult() {
    return this.exploredDepositResult;
  }

  public UniqueID getSpecialistID() {
    return this.specialistID;
  }
}
