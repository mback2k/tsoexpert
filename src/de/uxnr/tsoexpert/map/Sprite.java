package de.uxnr.tsoexpert.map;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import de.uxnr.tsoexpert.nlib.SpriteLibContainer;
import de.uxnr.tsoexpert.nlib.data.FrameCalculated;
import de.uxnr.tsoexpert.nlib.data.Indices;
import de.uxnr.tsoexpert.nlib.data.Main;
import de.uxnr.tsoexpert.nlib.data.SubtypeCalculated;

public class Sprite {
	private static final SpriteLibContainer spriteLibContainer = new SpriteLibContainer();

	private final Main main;
	private final Image image;

	private Rectangle bounds;

	public Sprite(File file) throws IOException {
		String filename = file.getAbsolutePath().replaceAll("\\.(\\w*)$", ".bin");
		FileInputStream stream = new FileInputStream(filename);

		this.main = spriteLibContainer.loadSpriteLibFromBinaryData(stream);
		this.image = new Image(Display.getCurrent(), file.getAbsolutePath());
	}

	public Image getImage() {
		return this.image;
	}

	public Rectangle getBounds() {
		if (this.bounds == null) {
			if (this.main.spriteIndices_vector.size() > 0) {
				Indices indices = this.main.spriteIndices_vector.get(0);
				if (indices.subtypeCalculated_vector.size() > 0) {
					SubtypeCalculated subtypeCalculated = indices.subtypeCalculated_vector.get(0);
					if (subtypeCalculated.frameList_vector.size() > 0) {
						FrameCalculated frameCalculated = subtypeCalculated.frameList_vector.get(0);
						this.bounds = frameCalculated.calculateRect;
					}
				}
			}
		}
		if (this.bounds == null) {
			this.bounds = this.image.getBounds();
		}
		return new Rectangle(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
	}
}
