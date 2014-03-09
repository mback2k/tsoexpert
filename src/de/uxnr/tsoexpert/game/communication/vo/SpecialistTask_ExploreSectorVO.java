package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.tsoexpert.game.communication.vo.update.ExploredSectorDataVO;

public class SpecialistTask_ExploreSectorVO extends SpecialistTaskVO {
  private ExploredSectorDataVO exploredSectorDataVO;
  private int exploredSectorId;

  public ExploredSectorDataVO getExploredSectorDataVO() {
    return this.exploredSectorDataVO;
  }

  public int getExploredSectorId() {
    return this.exploredSectorId;
  }
}
