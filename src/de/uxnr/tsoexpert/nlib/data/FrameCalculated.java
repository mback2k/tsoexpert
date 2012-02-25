package de.uxnr.tsoexpert.nlib.data;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class FrameCalculated {
	public Point calculatePoint;
	public Rectangle calculateRect;
	public double frameOffsX;
	public double frameOffsXScaled;
	public double frameOffsXScaledCache;
	public double frameOffsY;
	public double frameOffsYScaled;
	public double frameOffsYScaledCache;
	public Image orginalBitmap;
	public boolean scaled;
	public Image scaledBitmap;
	public Rectangle scaledBitmapRectangle;
	public int size_u;
	public int size_v;
	public Object zoomMatrix;

	@Override
	public String toString() {
		return "calculatePoint: "+this.calculatePoint+", calculateRect: "+this.calculateRect;
	}
}
