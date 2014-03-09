package de.uxnr.tsoexpert.game.communication.vo;

public class SpecialistTask_MoveVO extends SpecialistTaskVO {
  private int currentGarrisonGridIdx;
  private int newGarrisonGridIdx;
  private int pathPos;

  public int getCurrentGarrisonGridIdx() {
    return this.currentGarrisonGridIdx;
  }

  public int getNewGarrisonGridIdx() {
    return this.newGarrisonGridIdx;
  }

  public int getPathPos() {
    return this.pathPos;
  }
}
