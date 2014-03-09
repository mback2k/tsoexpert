package de.uxnr.tsoexpert.model;

import de.uxnr.tsoexpert.game.communication.vo.BackgroundTileVO;
import de.uxnr.tsoexpert.model.grid.BackgroundGridPosition;

public class Background {
  private String name;
  private BackgroundGridPosition position;

  public Background(String name, BackgroundGridPosition position) {
    this.name = name;
    this.position = position;
  }

  public Background(BackgroundTileVO backgroundTileVO, int index) {
    this(backgroundTileVO.getName_string(), new BackgroundGridPosition(index));
  }

  public String getName() {
    return this.name;
  }

  public BackgroundGridPosition getPosition() {
    return this.position;
  }

  public int hashCode() {
    return this.name.hashCode() ^ this.position.hashCode();
  }
}
