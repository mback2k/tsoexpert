package de.uxnr.tsoexpert.game.communication.vo.guild;

import de.uxnr.amf.v3.AMF3_Object;

public class GuildPlayerPermissionVO extends AMF3_Object {
  private int banner;
  private int description;
  private int guildMail;
  private int invite;
  private int joinRequestAccept;
  private int joinRequestAllow;
  private int kick;
  private int motd;
  private int note;
  private int officerNote;
  private int officersChannel;
  private int ranksAssign;
  private int ranksEdit;

  public int getBanner() {
    return this.banner;
  }

  public int getDescription() {
    return this.description;
  }

  public int getGuildMail() {
    return this.guildMail;
  }

  public int getInvite() {
    return this.invite;
  }

  public int getJoinRequestAccept() {
    return this.joinRequestAccept;
  }

  public int getJoinRequestAllow() {
    return this.joinRequestAllow;
  }

  public int getKick() {
    return this.kick;
  }

  public int getMotd() {
    return this.motd;
  }

  public int getNote() {
    return this.note;
  }

  public int getOfficerNote() {
    return this.officerNote;
  }

  public int getOfficersChannel() {
    return this.officersChannel;
  }

  public int getRanksAssign() {
    return this.ranksAssign;
  }

  public int getRanksEdit() {
    return this.ranksEdit;
  }
}
