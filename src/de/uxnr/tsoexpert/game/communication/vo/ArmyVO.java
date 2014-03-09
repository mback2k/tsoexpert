package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class ArmyVO extends AMF3_Object {
  private List<SquadVO> squads;

  public List<SquadVO> getSquads() {
    return this.squads;
  }
}
