package de.uxnr.tsoexpert.game.communication.vo.mail;

import de.uxnr.amf.v3.AMF3_Object;

public class MailBodyVO extends AMF3_Object {
  private String text;

  public String getText() {
    return this.text;
  }
}
