package de.uxnr.tsoexpert.registry;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import de.uxnr.tsoexpert.game.communication.vo.FreeLandscapeVO;
import de.uxnr.tsoexpert.model.FreeLandscape;
import de.uxnr.tsoexpert.model.grid.FreeGridPosition;

public class FreeLandscapeRegistry {
  private static FreeLandscapeRegistry instance;

  private FreeLandscapeRegistry() {}

  public static FreeLandscapeRegistry getInstance() {
    if (FreeLandscapeRegistry.instance == null) {
      FreeLandscapeRegistry.instance = new FreeLandscapeRegistry();
    }
    return FreeLandscapeRegistry.instance;
  }

  private final Map<FreeGridPosition, FreeLandscape> freeLandscapes =
      new TreeMap<FreeGridPosition, FreeLandscape>();

  public void add(FreeLandscapeVO freeLandscapeVO) {
    FreeLandscape freeLandscape = new FreeLandscape(freeLandscapeVO);
    this.freeLandscapes.put(freeLandscape.getPosition(), freeLandscape); // TODO: Fire event
    ZoneObjectRegistry.getInstance().add(freeLandscape.getPosition(), freeLandscape);
  }

  public void addAll(Collection<FreeLandscapeVO> freeLandscapeVOs) {
    for (FreeLandscapeVO freeLandscapeVO : freeLandscapeVOs) {
      this.add(freeLandscapeVO);
    }
  }

  public void remove(FreeLandscapeVO freeLandscapeVO) {
    FreeLandscape freeLandscape = new FreeLandscape(freeLandscapeVO);
    this.freeLandscapes.remove(freeLandscape.getPosition()); // TODO: Fire event
    ZoneObjectRegistry.getInstance().remove(freeLandscape.getPosition());
  }

  public Map<FreeGridPosition, FreeLandscape> getAll() {
    return this.freeLandscapes;
  }
}
