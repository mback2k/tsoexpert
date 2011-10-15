package de.uxnr.tsoexpert.game.communication.vo;

public class SpecialistTask_TravelToZoneVO extends SpecialistTaskVO {
	private int destinationZoneID;
	private int garrisonGridIdx;
	private int pathPos;

	public int getDestinationZoneID() {
		return this.destinationZoneID;
	}

	public int getGarrisonGridIdx() {
		return this.garrisonGridIdx;
	}

	public int getPathPos() {
		return this.pathPos;
	}
}
