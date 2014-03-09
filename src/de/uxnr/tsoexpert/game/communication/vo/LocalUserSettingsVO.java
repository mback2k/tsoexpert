package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.amf.v3.type.Date;

public class LocalUserSettingsVO extends AMF3_Object {
  private Object currentTradeOffer; // TODO cTradeData
  private boolean effectsMuted;
  private boolean loopsMuted;
  private Date tradeCooldownStartTime;

  public Object getCurrentTradeOffer() {
    return this.currentTradeOffer;
  }

  public boolean getEffectsMuted() {
    return this.effectsMuted;
  }

  public boolean getLoopsMuted() {
    return this.loopsMuted;
  }

  public Date getTradeCooldownStartTime() {
    return this.tradeCooldownStartTime;
  }
}
