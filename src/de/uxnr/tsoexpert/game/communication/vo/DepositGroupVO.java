package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class DepositGroupVO extends AMF3_Object {
  private int mAverageAmount;
  private List<DepositVO> mDepositsVector;
  private String mDepositType_string;
  private int mId;
  private int mMaxAccessible;
  private int mUbiRandom;

  public int getAverageAmount() {
    return this.mAverageAmount;
  }

  public List<DepositVO> getDepositsVector() {
    return this.mDepositsVector;
  }

  public String getDepositType_string() {
    return this.mDepositType_string;
  }

  public int getId() {
    return this.mId;
  }

  public int getMaxAccessible() {
    return this.mMaxAccessible;
  }

  public int getUbiRandom() {
    return this.mUbiRandom;
  }
}
