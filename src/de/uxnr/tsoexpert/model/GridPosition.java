package de.uxnr.tsoexpert.model;

import java.awt.Point;

public class GridPosition implements Comparable<GridPosition> {
	private int x;
	private int y;

	public GridPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public GridPosition(Point point) {
		this(point.x, point.y);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Point getPoint() {
		return new Point(this.getX(), this.getY());
	}

	public int hashCode() {
		return (this.x + (this.y * 64)) ^ GridPosition.class.hashCode();
	}

	@Override
	public int compareTo(GridPosition buildingGridPosition) {
		return (this.x + (this.y * 64)) - (buildingGridPosition.x + (buildingGridPosition.y * 64));
	}
}
