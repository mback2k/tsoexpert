package de.uxnr.tsoexpert.game.communication.vo.update;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.BuildingVO;
import de.uxnr.tsoexpert.game.communication.vo.DepositVO;
import de.uxnr.tsoexpert.game.communication.vo.LandscapeVO;
import de.uxnr.tsoexpert.game.communication.vo.ResourceCreationVO;
import de.uxnr.tsoexpert.game.communication.vo.StreetVO;

public class ExploredSectorDataVO extends AMF3_Object {
  private List<BuildingVO> buildings;
  private List<DepositVO> deposits;
  private List<LandscapeVO> landscapes;
  private List<ResourceCreationVO> resourceCreations;
  private List<StreetVO> streets;

  public List<BuildingVO> getBuildings() {
    return this.buildings;
  }

  public List<DepositVO> getDeposits() {
    return this.deposits;
  }

  public List<LandscapeVO> getLandscapes() {
    return this.landscapes;
  }

  public List<ResourceCreationVO> getResourceCreations() {
    return this.resourceCreations;
  }

  public List<StreetVO> getStreets() {
    return this.streets;
  }
}
