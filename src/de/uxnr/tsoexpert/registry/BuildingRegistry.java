package de.uxnr.tsoexpert.registry;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import de.uxnr.tsoexpert.game.communication.vo.BuildingVO;
import de.uxnr.tsoexpert.model.Building;
import de.uxnr.tsoexpert.model.grid.BuildingGridPosition;

public class BuildingRegistry {
  private static BuildingRegistry instance;

  private BuildingRegistry() {}

  public static BuildingRegistry getInstance() {
    if (BuildingRegistry.instance == null) {
      BuildingRegistry.instance = new BuildingRegistry();
    }
    return BuildingRegistry.instance;
  }

  private final Map<BuildingGridPosition, Building> buildings =
      new TreeMap<BuildingGridPosition, Building>();

  public void add(BuildingVO buildingVO) {
    Building building = new Building(buildingVO);
    this.buildings.put(building.getPosition(), building); // TODO: Fire event
    ZoneObjectRegistry.getInstance().add(building.getPosition(), building);
  }

  public void addAll(Collection<BuildingVO> buildingVOs) {
    for (BuildingVO buildingVO : buildingVOs) {
      this.add(buildingVO);
    }
  }

  public void remove(BuildingVO buildingVO) {
    Building building = new Building(buildingVO);
    this.buildings.remove(building.getPosition()); // TODO: Fire event
    ZoneObjectRegistry.getInstance().remove(building.getPosition());
  }

  public Map<BuildingGridPosition, Building> getAll() {
    return this.buildings;
  }
}
