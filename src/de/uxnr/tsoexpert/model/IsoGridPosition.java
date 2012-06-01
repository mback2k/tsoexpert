package de.uxnr.tsoexpert.model;

public class IsoGridPosition extends GridPosition {
	private final int isoGridWidth = 117;
	private final int isoGridHeight = 72;
	private final int position;
	private final int offsetX;
	private final int offsetY;

	public IsoGridPosition(int position) {
		this(position, 0, 0);
	}
	
	public IsoGridPosition(int position, int offsetX, int offsetY) {
		this.position = position;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public int getX() {
		return (int) (((this.position % 64) - 0.5 + ((Math.floor(this.position / 64) % 2) / 2)) * this.isoGridWidth) + this.offsetX;
	}

	public int getY() {
		return (int) ((Math.floor(this.position / 64) + 1) * (this.isoGridHeight / 2)) + this.offsetY;
	}
}
