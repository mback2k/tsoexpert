package de.uxnr.tsoexpert.model.grid;

import java.awt.Point;

abstract public class GridPosition implements Comparable<GridPosition> {
	public enum Layer {
		BACKGROUND, LANDSCAPE, BUILDING;
	}

	abstract public int getX();
	abstract public int getY();
	abstract public Layer getLayer();

	public Point getPoint() {
		return new Point(this.getX(), this.getY());
	}

	public int hashCode() {
		return (this.getX() << 16) & 0xFFFF | (this.getY() & 0xFFFF);
	}

	@Override
	public int compareTo(GridPosition gridPosition) {
		Layer al = this.getLayer();
		Layer bl = gridPosition.getLayer();
		if (al != bl) {
			return al.ordinal() - bl.ordinal();
		}
		int ay = this.getY();
		int by = gridPosition.getY();
		if (ay != by) {
			return ay - by;
		}
		int ax = this.getX();
		int bx = gridPosition.getX();
		if (ax != bx) {
			return ax - bx;
		}
		return 0;
	}
}
