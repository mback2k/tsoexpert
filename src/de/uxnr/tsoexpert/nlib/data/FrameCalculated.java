package de.uxnr.tsoexpert.nlib.data;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class FrameCalculated {
	public Point calculatePoint;
	public Rectangle calculateRect;
	public double frameOffsX;
	public double frameOffsXScaled;
	public double frameOffsXScaledCache;
	public double frameOffsY;
	public double frameOffsYScaled;
	public double frameOffsYScaledCache;
	public ImageData orginalBitmap;
	public boolean scaled;
	public ImageData scaledBitmap;
	public Rectangle scaledBitmapRectangle;
	public int size_u;
	public int size_v;
	public Object zoomMatrix;

	@Override
	public String toString() {
		return "calculatePoint: "+this.calculatePoint+", calculateRect: "+this.calculateRect;
	}
}
