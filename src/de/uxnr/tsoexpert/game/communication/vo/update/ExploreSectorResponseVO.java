package de.uxnr.tsoexpert.game.communication.vo.update;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class ExploreSectorResponseVO extends AMF3_Object {
  private List<ExploredSectorVO> exploredSectors_vector;

  public List<ExploredSectorVO> getExploredSectors_vector() {
    return this.exploredSectors_vector;
  }
}
