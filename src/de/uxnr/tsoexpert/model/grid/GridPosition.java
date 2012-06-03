package de.uxnr.tsoexpert.model.grid;

import java.awt.Point;

abstract public class GridPosition implements Comparable<GridPosition> {
	abstract public int getX();
	abstract public int getY();

	public Point getPoint() {
		return new Point(this.getX(), this.getY());
	}

	public int hashCode() {
		return (this.getX() + (this.getY() * 64)) ^ GridPosition.class.hashCode();
	}

	@Override
	public int compareTo(GridPosition buildingGridPosition) {
		return (this.getX() + (this.getY() * 64)) - (buildingGridPosition.getX() + (buildingGridPosition.getY() * 64));
	}
}
