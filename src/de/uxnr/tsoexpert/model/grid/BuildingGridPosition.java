package de.uxnr.tsoexpert.model.grid;

public class BuildingGridPosition extends IsoGridPosition {
  public BuildingGridPosition(int position) {
    this(position, 0, 0);
  }

  public BuildingGridPosition(int position, int offsetX, int offsetY) {
    super(position, offsetX, offsetY);
  }

  public Layer getLayer() {
    return Layer.BUILDING;
  }
}
