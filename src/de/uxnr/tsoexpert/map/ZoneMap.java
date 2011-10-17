package de.uxnr.tsoexpert.map;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;

import de.uxnr.tsoexpert.game.communication.vo.BackgroundTileVO;
import de.uxnr.tsoexpert.game.communication.vo.BuildingVO;
import de.uxnr.tsoexpert.game.communication.vo.FreeLandscapeVO;
import de.uxnr.tsoexpert.game.communication.vo.LandscapeVO;
import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;
import de.uxnr.tsoexpert.resource.GameSetting;
import de.uxnr.tsoexpert.resource.XMLHandler;

public class ZoneMap {
	private static final Map<String, Sprite> backgrounds = new HashMap<String, Sprite>();
	private static final Map<String, Sprite> landscapes = new HashMap<String, Sprite>();
	private static final Map<String, Sprite> buildings = new HashMap<String, Sprite>();

	private final Display display;
	private final Document gfxDoc;
	private final ZoneVO zoneVO;

	private Image doubleBuffer = null;
	private double zoomFactor = 1;
	private double offsetX = 0;
	private double offsetY = 0;
	private int maxOffsetX = 0;
	private int maxOffsetY = 0;

	public ZoneMap(ZoneVO zoneVO) {
		this.display = Display.getCurrent();
		this.gfxDoc = XMLHandler.getDocument(GameSetting.gfx_settings);
		this.zoneVO = zoneVO;
	}

	public void updateZoomFactor(int count) {
		if (count > 0 && this.zoomFactor < 1.5) {
			this.zoomFactor *= 1.1;
			this.offsetX *= 1.1;
			this.offsetY *= 1.1;
		} else if (count < 0 && this.zoomFactor > 0.5) {
			this.zoomFactor *= 0.9;
			this.offsetX *= 0.9;
			this.offsetY *= 0.9;
		}
	}

	public void updateOffsetX(int offsetX) {
		this.offsetX += offsetX;
		this.offsetX = Math.max(this.offsetX, 0);
		this.offsetX = Math.min(this.offsetX, this.maxOffsetX);
	}

	public void updateOffsetY(int offsetY) {
		this.offsetY += offsetY;
		this.offsetY = Math.max(this.offsetY, 0);
		this.offsetY = Math.min(this.offsetY, this.maxOffsetY);
	}

	public void draw(Canvas canvas, GC gc) {
		Point size = canvas.getSize();
		int width = 0;
		int height = 0;

		if (this.doubleBuffer != null) {
			Rectangle area = this.doubleBuffer.getBounds();
			width = area.width;
			height = area.height;
		}
		if (this.doubleBuffer == null || size.x != width || size.y != height) {
			this.doubleBuffer = new Image(this.display, size.x, size.y);
		}

		GC imageGC = new GC(this.doubleBuffer);
		imageGC.setAntialias(SWT.OFF);
		imageGC.setInterpolation(SWT.LOW);
		imageGC.setBackground(gc.getBackground());
		imageGC.setForeground(gc.getForeground());
		imageGC.setFont(gc.getFont());
		imageGC.fillRectangle(0, 0, size.x, size.y);

		Rectangle clip = gc.getClipping();

		this.maxOffsetX = 0;
		this.maxOffsetY = 0;

		this.drawBackground(imageGC, clip);
		this.drawFreeLandscape(imageGC, clip);
		this.drawLandscape(imageGC, clip);
		this.drawBuildings(imageGC, clip);

		this.offsetX = Math.min(this.offsetX, this.maxOffsetX);
		this.offsetY = Math.min(this.offsetY, this.maxOffsetY);

		gc.drawImage(this.doubleBuffer, 0, 0);

		imageGC.dispose();
	}

	private void drawBackground(GC gc, Rectangle clip) {
		int width = GameSetting.getNumber(this.gfxDoc, "//Globals/BackgroundGrid/@w").intValue();
		int height = GameSetting.getNumber(this.gfxDoc, "//Globals/BackgroundGrid/@h").intValue();
		int length = 34;

		int index = 0;
		for (BackgroundTileVO backgroundTile : this.zoneVO.getBackgroundTiles()) {
			String name = backgroundTile.getName_string();
			Sprite sprite = this.getBackground(name);

			if (sprite != null) {
				Image image = sprite.getImage();
				Rectangle src = sprite.getBounds();
				Rectangle dst = sprite.getBounds();

				dst.x = (int) (((index % length) * width) * this.zoomFactor);
				dst.y = (int) ((Math.floor(index / length) * height) * this.zoomFactor);
				dst.width = (int) (src.width * this.zoomFactor);
				dst.height = (int) (src.height * this.zoomFactor);

				this.maxOffsetX = Math.max(this.maxOffsetX, (dst.x + dst.width) - clip.width);
				this.maxOffsetY = Math.max(this.maxOffsetY, (dst.y + dst.height) - clip.height);

				dst.x -= this.offsetX;
				dst.y -= this.offsetY;

				if (clip.intersects(dst)) {
					gc.drawImage(image, src.x, src.y, src.width, src.height, dst.x, dst.y, dst.width, dst.height);
				}

				index++;
			}
		}
	}

	private void drawFreeLandscape(GC gc, Rectangle clip) {
		for (FreeLandscapeVO freeLandscape : this.zoneVO.getFreeLandscapes()) {
			String name = freeLandscape.getName_string();
			Sprite sprite = this.getLandscape(name);

			if (sprite != null) {
				Image image = sprite.getImage();
				Rectangle src = sprite.getBounds();
				Rectangle dst = sprite.getBounds();

				dst.x = (int) (freeLandscape.getX() * this.zoomFactor);
				dst.y = (int) (freeLandscape.getY() * this.zoomFactor);
				dst.width = (int) (src.width * this.zoomFactor);
				dst.height = (int) (src.height * this.zoomFactor);

				dst.x -= this.offsetX;
				dst.y -= this.offsetY;

				if (clip.intersects(dst)) {
					gc.drawImage(image, src.x, src.y, src.width, src.height, dst.x, dst.y, dst.width, dst.height);
					gc.drawRectangle(dst);
					gc.drawText("FL: "+name, dst.x, dst.y);
				}
			}
		}
	}

	private void drawLandscape(GC gc, Rectangle clip) {
		int width = GameSetting.getNumber(this.gfxDoc, "//Globals/IsoGrid/@w").intValue();
		int height = GameSetting.getNumber(this.gfxDoc, "//Globals/IsoGrid/@h").intValue();
		int length = 64;

		for (LandscapeVO landscape : this.zoneVO.getLandscapes()) {
			String name = landscape.getName_string();
			Sprite sprite = this.getLandscape(name);
			int index = landscape.getGrid();

			if (sprite != null) {
				Image image = sprite.getImage();
				Rectangle src = sprite.getBounds();
				Rectangle dst = sprite.getBounds();

				dst.x = (int) (((index % length) * width) * this.zoomFactor);
				dst.y = (int) ((Math.floor(index / length) * height) * this.zoomFactor);
				dst.width = (int) (src.width * this.zoomFactor);
				dst.height = (int) (src.height * this.zoomFactor);

				dst.x -= this.offsetX;
				dst.y -= this.offsetY;

				if (clip.intersects(dst)) {
					gc.drawImage(image, src.x, src.y, src.width, src.height, dst.x, dst.y, dst.width, dst.height);
					gc.drawRectangle(dst);
					gc.drawText("L: "+name, dst.x, dst.y);
				}
			}
		}
	}

	private void drawBuildings(GC gc, Rectangle clip) {
		int width = GameSetting.getNumber(this.gfxDoc, "//Globals/IsoGrid/@w").intValue();
		int height = GameSetting.getNumber(this.gfxDoc, "//Globals/IsoGrid/@h").intValue();
		int length = 64;

		for (BuildingVO building : this.zoneVO.getBuildings()) {
			String name = building.getBuildingName_string();
			Sprite sprite = this.getBuilding(name);
			int index = building.getBuildingGrid();

			if (sprite != null) {
				Image image = sprite.getImage();
				Rectangle src = sprite.getBounds();
				Rectangle dst = sprite.getBounds();

				dst.x = (int) (((index % length) * width) * this.zoomFactor);
				dst.y = (int) ((Math.floor(index / length) * height) * this.zoomFactor);
				dst.width = (int) (src.width * this.zoomFactor);
				dst.height = (int) (src.height * this.zoomFactor);

				dst.x -= this.offsetX;
				dst.y -= this.offsetY;

				if (clip.intersects(dst)) {
					gc.drawImage(image, src.x, src.y, src.width, src.height, dst.x, dst.y, dst.width, dst.height);
					gc.drawRectangle(dst);
					gc.drawText("B: "+name, dst.x, dst.y);
				}

				index++;
			}
		}
	}

	private Sprite getBackground(String name) {
		return this.getImage(name, "res/GFX/background_lib/", "//GameObjects/Backgrounds/Background[@name='"+name+"']/@filename", backgrounds);
	}

	private Sprite getLandscape(String name) {
		return this.getImage(name, "res/GFX/landscape_lib/", "//GameObjects/Landscapes/Landscape[@name='"+name+"']/@filename", landscapes);
	}

	private Sprite getBuilding(String name) {
		return this.getImage(name, "res/GFX/building_lib/", "//GameObjects/Buildings/Building[@name='"+name+"']/@filename", buildings);
	}

	private Sprite getImage(String name, String path, String xpath, Map<String, Sprite> cache) {
		Sprite sprite = null;
		if (cache.containsKey(name)) {
			sprite = cache.get(name);
		} else {
			String filename = GameSetting.getString(this.gfxDoc, xpath);
			if (filename != null) {
				File file = new File(path+filename);
				if (file.exists() && file.isFile() && file.canRead()) {
					try {
						sprite = new Sprite(file);
						cache.put(name, sprite);
					} catch (IOException e) {
						return null;
					}
				}
			}
		}
		return sprite;
	}
}
