package de.uxnr.tsoexpert.model;

import de.uxnr.tsoexpert.game.communication.vo.BuildingVO;
import de.uxnr.tsoexpert.model.grid.IsoGridPosition;

public class Building {
	private String name;
	private IsoGridPosition position;
	private int level;

	public Building(String name, IsoGridPosition position, int level) {
		this.name = name;
		this.position = position;
		this.level = level;
	}

	public Building(BuildingVO buildingVO) {
		this(buildingVO.getBuildingName_string(), new IsoGridPosition(buildingVO.getBuildingGrid(), buildingVO.getOffsetX(), buildingVO.getOffsetY()), buildingVO.getUpgradeLevel());
	}

	public String getName() {
		return this.name;
	}

	public IsoGridPosition getPosition() {
		return this.position;
	}

	public int getLevel() {
		return this.level;
	}

	public int hashCode() {
		return this.name.hashCode() ^ this.position.hashCode() + this.level;
	}
}
