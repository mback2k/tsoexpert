package de.uxnr.tsoexpert.game.communication.vo.guild;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class GuildVO extends AMF3_Object {
  private String motd;
  private int bannerID;
  private String name;
  private List<Object> log;
  private int size;
  private int id;
  private double cacheTimeStamp;
  private int maxSize;
  private GuildPlayerPermissionVO playerPermissions;
  private double foundTime;
  private String tag;
  private String description;
  private List<Object> ranks;
  private List<Object> members;

  public String getMotd() {
    return this.motd;
  }

  public int getBannerID() {
    return this.bannerID;
  }

  public String getName() {
    return this.name;
  }

  public List<Object> getLog() {
    return this.log;
  }

  public int getSize() {
    return this.size;
  }

  public int getId() {
    return this.id;
  }

  public double getCacheTimeStamp() {
    return this.cacheTimeStamp;
  }

  public int getMaxSize() {
    return this.maxSize;
  }

  public GuildPlayerPermissionVO getPlayerPermissions() {
    return this.playerPermissions;
  }

  public double getFoundTime() {
    return this.foundTime;
  }

  public String getTag() {
    return this.tag;
  }

  public String getDescription() {
    return this.description;
  }

  public List<Object> getRanks() {
    return this.ranks;
  }

  public List<Object> getMembers() {
    return this.members;
  }
}
