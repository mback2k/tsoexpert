package de.uxnr.tsoexpert.map;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
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
import de.uxnr.tsoexpert.game.communication.vo.MapValueItemVO;
import de.uxnr.tsoexpert.game.communication.vo.PathVO;
import de.uxnr.tsoexpert.game.communication.vo.ResourceCreationVO;
import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;
import de.uxnr.tsoexpert.resource.GameSetting;
import de.uxnr.tsoexpert.resource.XMLHandler;

public class ZoneMap {
	private static final Map<String, Sprite> backgrounds = new HashMap<String, Sprite>();
	private static final Map<String, Sprite> landscapes = new HashMap<String, Sprite>();
	private static final Map<String, Sprite> buildings = new HashMap<String, Sprite>();

	private static final Map<String, Double> nofUpgrades = new HashMap<String, Double>();
	private static final Map<Integer, int[]> grid = new HashMap<Integer, int[]>();

	private final int backgroundGridWidth;
	private final int backgroundGridHeight;

	private final int isoGridWidth;
	private final int isoGridHeight;

	private final Display display;
	private final Document gfxDoc;
	private final ZoneVO zoneVO;

	private Image doubleBuffer = null;
	private double zoomFactor = 1;
	private int offsetX = 0;
	private int offsetY = 0;
	private int minOffsetX = 0;
	private int minOffsetY = 0;
	private int maxOffsetX = 0;
	private int maxOffsetY = 0;

	private boolean showBackground = true;
	private boolean showFreeLandscape = false;
	private boolean showLandscape = false;
	private boolean showBuilding = false;
	private boolean debugBackground = false;
	private boolean debugFreeLandscape = true;
	private boolean debugLandscape = true;
	private boolean debugBuilding = true;
	private boolean debugResourceCreations = false;
	private boolean debugMapValues = false;

	public ZoneMap(ZoneVO zoneVO) {
		this.zoneVO = zoneVO;
		this.display = Display.getCurrent();
		this.gfxDoc = XMLHandler.getDocument(GameSetting.gfx_settings);
		this.backgroundGridWidth = GameSetting.getNumber(this.gfxDoc, "//Globals/BackgroundGrid/@w").intValue();
		this.backgroundGridHeight = GameSetting.getNumber(this.gfxDoc, "//Globals/BackgroundGrid/@h").intValue();
		this.isoGridWidth = GameSetting.getNumber(this.gfxDoc, "//Globals/IsoGrid/@w").intValue();
		this.isoGridHeight = GameSetting.getNumber(this.gfxDoc, "//Globals/IsoGrid/@h").intValue();
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
		this.offsetX = Math.max(this.offsetX, this.minOffsetX);
		this.offsetX = Math.min(this.offsetX, this.maxOffsetX);
	}

	public void updateOffsetY(int offsetY) {
		this.offsetY += offsetY;
		this.offsetY = Math.max(this.offsetY, this.minOffsetY);
		this.offsetY = Math.min(this.offsetY, this.maxOffsetY);
	}

	public void setShowBackground(boolean showBackground) {
		this.showBackground = showBackground;
	}

	public void setShowFreeLandscape(boolean showFreeLandscape) {
		this.showFreeLandscape = showFreeLandscape;
	}

	public void setShowLandscape(boolean showLandscape) {
		this.showLandscape = showLandscape;
	}

	public void setShowBuilding(boolean showBuilding) {
		this.showBuilding = showBuilding;
	}

	public void setDebugBackground(boolean debugBackground) {
		this.debugBackground = debugBackground;
	}

	public void setDebugFreeLandscape(boolean debugFreeLandscape) {
		this.debugFreeLandscape = debugFreeLandscape;
	}

	public void setDebugLandscape(boolean debugLandscape) {
		this.debugLandscape = debugLandscape;
	}

	public void setDebugBuilding(boolean debugBuilding) {
		this.debugBuilding = debugBuilding;
	}

	public void setDebugResourceCreations(boolean debugResourceCreations) {
		this.debugResourceCreations = debugResourceCreations;
	}

	public void setDebugMapValues(boolean debugMapValues) {
		this.debugMapValues = debugMapValues;
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

		this.minOffsetX = 0;
		this.minOffsetY = 0;
		this.maxOffsetX = 0;
		this.maxOffsetY = 0;

		if (this.showBackground || this.debugBackground) {
			this.drawBackground(imageGC, clip);
		}
		if (this.showFreeLandscape || this.debugFreeLandscape) {
			this.drawFreeLandscape(imageGC, clip);
		}
		if (this.showLandscape || this.debugLandscape) {
			this.drawLandscape(imageGC, clip);
		}
		if (this.showBuilding || this.debugBuilding) {
			this.drawBuildings(imageGC, clip);
		}
		if (this.debugResourceCreations) {
			this.drawResourceCreations(imageGC, clip);
		}
		if (this.debugMapValues) {
			this.drawMapValues(imageGC, clip);
		}

		this.offsetX = Math.max(this.offsetX, this.minOffsetX);
		this.offsetY = Math.max(this.offsetY, this.minOffsetY);
		this.offsetX = Math.min(this.offsetX, this.maxOffsetX);
		this.offsetY = Math.min(this.offsetY, this.maxOffsetY);

		gc.drawImage(this.doubleBuffer, 0, 0);

		imageGC.dispose();
	}

	private void drawBackground(GC gc, Rectangle clip) {
		int width = this.backgroundGridWidth;
		int height = this.backgroundGridHeight;
		int length = 34;

		int index = 0;
		for (BackgroundTileVO backgroundTile : this.zoneVO.getBackgroundTiles()) {
			String name = backgroundTile.getName_string();
			Sprite sprite = this.getBackground(name);

			if (sprite != null) {
				Image image = sprite.getImage();
				Rectangle src = sprite.getBounds();
				Rectangle dst = sprite.getBounds();

				dst.x = (int) ((((index % length) * width) + sprite.getOffsetX()) * this.zoomFactor);
				dst.y = (int) (((Math.floor(index / length) * height) + sprite.getOffsetY()) * this.zoomFactor);
				dst.width = (int) (src.width * this.zoomFactor);
				dst.height = (int) (src.height * this.zoomFactor);

				this.minOffsetX = Math.min(this.minOffsetX, dst.x);
				this.minOffsetY = Math.min(this.minOffsetY, dst.y);
				this.maxOffsetX = Math.max(this.maxOffsetX, (dst.x + dst.width) - clip.width);
				this.maxOffsetY = Math.max(this.maxOffsetY, (dst.y + dst.height) - clip.height);

				dst.x -= this.offsetX;
				dst.y -= this.offsetY;

				if (clip.intersects(dst)) {
					gc.drawImage(image, src.x, src.y, src.width, src.height, dst.x, dst.y, dst.width, dst.height);

					if (this.debugBackground) {
						gc.drawRectangle(dst);
						gc.drawText("BG: "+name, dst.x, dst.y);
					}
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

				dst.x = (int) ((freeLandscape.getX() + sprite.getOffsetX()) * this.zoomFactor);
				dst.y = (int) ((freeLandscape.getY() + sprite.getOffsetY()) * this.zoomFactor);
				dst.width = (int) (src.width * this.zoomFactor);
				dst.height = (int) (src.height * this.zoomFactor);

				dst.x -= this.offsetX;
				dst.y -= this.offsetY;

				if (clip.intersects(dst)) {
					gc.drawImage(image, src.x, src.y, src.width, src.height, dst.x, dst.y, dst.width, dst.height);

					if (this.debugFreeLandscape) {
						gc.drawRectangle(dst);
						gc.drawText("FL: "+name, dst.x, dst.y);
					}
				}
			}
		}
	}

	private void drawLandscape(GC gc, Rectangle clip) {
		int width = this.isoGridWidth;
		int height = this.isoGridHeight / 2;
		int length = 64;

		for (LandscapeVO landscape : this.zoneVO.getLandscapes()) {
			String name = landscape.getName_string();
			Sprite sprite = this.getLandscape(name);
			int index = landscape.getGrid();

			if (sprite != null) {
				Image image = sprite.getImage();
				Rectangle src = sprite.getBounds();
				Rectangle dst = sprite.getBounds();

				double margin = (Math.floor(index / length) % 2) / 2;

				dst.x = (int) (((((index % length) + margin) * width) + sprite.getOffsetX()) * this.zoomFactor);
				dst.y = (int) (((Math.floor(index / length) * height) + sprite.getOffsetY()) * this.zoomFactor);
				dst.width = (int) (src.width * this.zoomFactor);
				dst.height = (int) (src.height * this.zoomFactor);

				dst.x -= this.offsetX;
				dst.y -= this.offsetY;

				if (clip.intersects(dst)) {
					gc.drawImage(image, src.x, src.y, src.width, src.height, dst.x, dst.y, dst.width, dst.height);

					if (this.debugLandscape) {
						gc.drawRectangle(dst);
						gc.drawText("L: "+name, dst.x, dst.y);
					}
				}
			}
		}
	}

	private void drawBuildings(GC gc, Rectangle clip) {
		int width = this.isoGridWidth;
		int height = this.isoGridHeight / 2;
		int length = 64;

		for (BuildingVO building : this.zoneVO.getBuildings()) {
			String name = building.getBuildingName_string();
			Sprite sprite = this.getBuilding(name, building.getUpgradeLevel(), 0);
			int index = building.getBuildingGrid();

			if (sprite != null) {
				Image image = sprite.getImage();
				Rectangle src = sprite.getBounds();
				Rectangle dst = sprite.getBounds();

				double margin = (Math.floor(index / length) % 2) / 2;

				dst.x = (int) (((((index % length) + margin) * width) + sprite.getOffsetX()) * this.zoomFactor);
				dst.y = (int) (((Math.floor(index / length) * height) + sprite.getOffsetY()) * this.zoomFactor);
				dst.width = (int) (src.width * this.zoomFactor);
				dst.height = (int) (src.height * this.zoomFactor);

				dst.x -= this.offsetX;
				dst.y -= this.offsetY;

				if (clip.intersects(dst)) {
					gc.drawImage(image, src.x, src.y, src.width, src.height, dst.x, dst.y, dst.width, dst.height);

					if (this.debugBuilding) {
						gc.drawRectangle(dst);
						gc.drawText("B: "+name, dst.x, dst.y);
					}
				}

				grid.put(index, new int[] { dst.x + (dst.width / 2), dst.y + (dst.height / 2) });

				index++;
			}
		}
	}

	private void drawMapValues(GC gc, Rectangle clip) {
		int width = this.isoGridWidth;
		int height = this.isoGridHeight / 2;
		int length = 62;

		Color red = this.display.getSystemColor(SWT.COLOR_RED);
		Color blue = this.display.getSystemColor(SWT.COLOR_BLUE);
		Color green = this.display.getSystemColor(SWT.COLOR_GREEN);

		int index = 0;
		for (MapValueItemVO mapValueItemVO : this.zoneVO.getMapValues()) {
			double margin = (Math.floor(index / length) % 2) / 2;

			int x = (int) (((((index % length) + margin) * width) * this.zoomFactor) - this.offsetX);
			int y = (int) (((Math.floor(index / length) * height) * this.zoomFactor) - this.offsetY);
			int w= (int) (width * this.zoomFactor);
			int h = (int) (height * this.zoomFactor);

			switch (mapValueItemVO.getBackgroundBlocking()) {
				case 0: // BLOCK_TYPE_ALLOW_ALL
					gc.setForeground(green);
					break;
				case 1: // BLOCK_TYPE_ALLOW_NOTHING
					gc.setForeground(red);
					break;
				case 2: // BLOCK_TYPE_ALLOW_STREETS
					gc.setForeground(blue);
					break;
			}

			Rectangle dst = new Rectangle(x, y, w, h);

			if (clip.intersects(dst)) {
				gc.drawRectangle(dst);
				gc.drawText("S: "+mapValueItemVO.getSectorId(), dst.x, dst.y);
			}

			index++;
		}
	}

	private void drawResourceCreations(GC gc, Rectangle clip) {
		int width = this.isoGridWidth;
		int height = this.isoGridHeight / 2;
		int length = 64;

		Color red = this.display.getSystemColor(SWT.COLOR_RED);
		Color blue = this.display.getSystemColor(SWT.COLOR_BLUE);

		for (ResourceCreationVO resourceCreationVO : this.zoneVO.getResourceCreations()) {
			int index = resourceCreationVO.getResourceCreationHouseGrid();

			double margin = (Math.floor(index / length) % 2) / 2;
			int startX = (int) (((((index % length) + margin) * width) * this.zoomFactor) - this.offsetX);
			int startY = (int) (((Math.floor(index / length) * height) * this.zoomFactor) - this.offsetY);
			int endX = startX;
			int endY = startY;

			if (grid.containsKey(index)) {
				startX = endX = grid.get(index)[0];
				startY = endY = grid.get(index)[1];
			}

			gc.setForeground(red);

			PathVO pathVO = resourceCreationVO.getPathVO();
			if (pathVO != null) {
				for (de.uxnr.amf.v3.type.Integer path : pathVO.getPath()) {
					index = path.get();

					endX = (int) (((((index % length) + margin) * width) * this.zoomFactor) - this.offsetX);
					endY = (int) (((Math.floor(index / length) * height) * this.zoomFactor) - this.offsetY);

					if (grid.containsKey(index)) {
						startX = endX = grid.get(index)[0];
						startY = endY = grid.get(index)[1];
					}

					gc.drawLine(startX, startY, endX, endY);
					gc.setForeground(blue);

					startX = endX;
					startY = endY;
				}
			}
		}
	}

	private Sprite getBackground(String name) {
		return this.getImage(name, -1, -1, "res/GFX/background_lib/", "//GameObjects/Backgrounds/Background[@name='"+name+"']/@filename", backgrounds);
	}

	private Sprite getLandscape(String name) {
		return this.getImage(name, -1, -1, "res/GFX/landscape_lib/", "//GameObjects/Landscapes/Landscape[@name='"+name+"']/@filename", landscapes);
	}

	private Sprite getBuilding(String name, int level, int type) {
		Double nofUpgrades;
		if (ZoneMap.nofUpgrades.containsKey(name)) {
			nofUpgrades = ZoneMap.nofUpgrades.get(name);
		} else {
			nofUpgrades = GameSetting.getNumber(this.gfxDoc, "//GameObjects/Buildings/Building[@name='"+name+"']/@nofUpgrades");
			ZoneMap.nofUpgrades.put(name, nofUpgrades);
		}
		if (nofUpgrades != null) {
			level = Math.min(level, nofUpgrades.intValue()) - 1;
		} else {
			level = 0;
		}
		return this.getImage(name, level, type, "res/GFX/building_lib/", "//GameObjects/Buildings/Building[@name='"+name+"']/@filename", buildings);
	}

	private Sprite getImage(String name, int level, int type, String path, String xpath, Map<String, Sprite> cache) {
		Sprite sprite = null;
		String key = name + level + type;
		if (cache.containsKey(key)) {
			sprite = cache.get(key);
		} else {
			String filename = GameSetting.getString(this.gfxDoc, xpath);
			if (filename != null) {
				if (level != -1 && type != -1) {
					filename = filename.replaceAll("\\.", "["+level+"_"+type+"].");
				}
				File file = new File(path+filename);
				if (file.exists() && file.isFile() && file.canRead()) {
					try {
						sprite = new Sprite(file);
					} catch (IOException e) {
						sprite = null;
					}
				}
			}
			cache.put(key, sprite);
		}
		return sprite;
	}
}
