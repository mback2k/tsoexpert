package de.uxnr.tsoexpert.model.grid;

public class FreeGridPosition extends GridPosition {
	private final int x;
	private final int y;

	public FreeGridPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Layer getLayer() {
		return Layer.LANDSCAPE;
	}
}
