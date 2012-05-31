package de.uxnr.tsoexpert.model;

public class BackgroundGridPosition extends GridPosition {
	public BackgroundGridPosition(int position) {
		this(position, 234, 144);
	}

	public BackgroundGridPosition(int position, int backgroundGridWith, int backgroundGridHeight) {
		super((position % 34) * backgroundGridWith, (position / 34) * backgroundGridHeight);
	}
}
