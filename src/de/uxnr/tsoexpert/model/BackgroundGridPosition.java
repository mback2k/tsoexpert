package de.uxnr.tsoexpert.model;

public class BackgroundGridPosition extends GridPosition {
	private final int backgroundGridWidth = 234;
	private final int backgroundGridHeight = 144;
	private final int position;

	public BackgroundGridPosition(int position) {
		this.position = position;
	}

	public int getX() {
		return (int) ((this.position % 34) * this.backgroundGridWidth);
	}

	public int getY() {
		return (int) ((Math.round(this.position / 34)) * this.backgroundGridHeight);
	}
}
