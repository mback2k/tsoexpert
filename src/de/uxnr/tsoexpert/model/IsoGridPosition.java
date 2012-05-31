package de.uxnr.tsoexpert.model;

public class IsoGridPosition extends GridPosition {
	public IsoGridPosition(int position) {
		this(position, 117, 72);
	}

	public IsoGridPosition(int position, int isoGridWidth, int isoGridHeight) {
		super((int) (((position % 64) + ((Math.floor(position / 64) % 2) / 2)) * isoGridWidth), (int) (Math.floor(position / 64) * (isoGridHeight / 2)));
	}
}
