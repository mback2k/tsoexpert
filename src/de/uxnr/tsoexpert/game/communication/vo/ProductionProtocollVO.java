package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class ProductionProtocollVO extends AMF3_Object {
	private int buildingGrid;
	private int currentResourceNr;
	private double currentTime;
	private int lastBuildingMode;
	private int phase;
	private int processCntr;
	private String resourceName;
	private String text;

	public int getBuildingGrid() {
		return this.buildingGrid;
	}

	public int getCurrentResourceNr() {
		return this.currentResourceNr;
	}

	public double getCurrentTime() {
		return this.currentTime;
	}

	public int getLastBuildingMode() {
		return this.lastBuildingMode;
	}

	public int getPhase() {
		return this.phase;
	}

	public int getProcessCntr() {
		return this.processCntr;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public String getText() {
		return this.text;
	}
}
