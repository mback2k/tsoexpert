package de.uxnr.tsoexpert.registry;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import de.uxnr.tsoexpert.game.communication.vo.BuildingVO;
import de.uxnr.tsoexpert.model.Building;
import de.uxnr.tsoexpert.model.grid.BuildingGridPosition;
import de.uxnr.tsoexpert.render.BuildingRenderer;

public class BuildingRegistry {
	private final Map<BuildingGridPosition, Building> buildings = new TreeMap<BuildingGridPosition, Building>();

	public void add(BuildingVO buildingVO) {
		Building building = new Building(buildingVO);
		this.buildings.put(building.getPosition(), building); // TODO: Fire event
	}

	public void addAll(Collection<BuildingVO> buildingVOs) {
		for (BuildingVO buildingVO : buildingVOs) {
			this.add(buildingVO); // TODO: Fire event
		}
	}

	public void remove(BuildingVO buildingVO) {
		Building building = new Building(buildingVO);
		this.buildings.remove(building); // TODO: Fire event
	}

	public void clear() {
		for (Building building : this.buildings.values()) {
			this.buildings.remove(building); // TODO: Fire event
		}
	}

	public Rectangle renderBuildings(BuildingRenderer buildingRenderer, Graphics2D graphics, Rectangle clip) {
		Rectangle frame = new Rectangle();
		for (Building building : this.buildings.values()) {
			Rectangle sprite = buildingRenderer.renderBuilding(building, graphics, clip);
			if (sprite != null) {
				frame.x = Math.min(frame.x, sprite.x);
				frame.y = Math.min(frame.y, sprite.y);
				frame.width = Math.max(frame.width, sprite.x + sprite.width);
				frame.height =  Math.max(frame.height, sprite.y + sprite.height);
			}
		}
		return frame;
	}
}
