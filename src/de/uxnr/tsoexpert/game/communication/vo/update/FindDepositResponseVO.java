package de.uxnr.tsoexpert.game.communication.vo.update;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class FindDepositResponseVO extends AMF3_Object {
  private List<FoundDepositVO> foundDeposits_vector;

  public List<FoundDepositVO> getFoundDeposits_vector() {
    return this.foundDeposits_vector;
  }
}
