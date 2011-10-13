package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class BuildQueueVO extends AMF3_Object {
	private Collection<BuildingVO> buildings;
	private int maxCount;

	public Collection<BuildingVO> getBuildings() {
		return this.buildings;
	}

	public int getMaxCount() {
		return this.maxCount;
	}
}
