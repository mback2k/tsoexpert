package de.uxnr.tsoexpert.ui.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

import de.uxnr.tsoexpert.TSOExpert;
import de.uxnr.tsoexpert.game.communication.vo.BackgroundTileVO;
import de.uxnr.tsoexpert.game.communication.vo.BuildingVO;
import de.uxnr.tsoexpert.game.communication.vo.FreeLandscapeVO;
import de.uxnr.tsoexpert.game.communication.vo.LandscapeVO;
import de.uxnr.tsoexpert.game.communication.vo.MapValueItemVO;
import de.uxnr.tsoexpert.game.communication.vo.PathVO;
import de.uxnr.tsoexpert.game.communication.vo.ResourceCreationVO;
import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;
import de.uxnr.tsoexpert.map.Sprite;
import de.uxnr.tsoexpert.resource.GameSetting;
import de.uxnr.tsoexpert.resource.SpriteHandler;
import de.uxnr.tsoexpert.resource.XMLHandler;

public class ZoneMap {
	private final Map<String, Double> nofUpgrades = new HashMap<String, Double>();

	private final int backgroundGridWidth;
	private final int backgroundGridHeight;

	private final int isoGridWidth;
	private final int isoGridHeight;

	private final XMLHandler xmlHandler;
	private final ZoneVO zoneVO;

	private BufferedImage doubleBuffer = null;
	private Rectangle offset = new Rectangle();
	private Rectangle frame = new Rectangle();
	private double zoomFactor = 1.0;

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
		this.xmlHandler = (XMLHandler) TSOExpert.getHandler("XMLHandler");
		Document doc = this.xmlHandler.getDocument(GameSetting.gfx_settings);
		this.backgroundGridWidth = GameSetting.getNumber(doc, "//Globals/BackgroundGrid/@w").intValue();
		this.backgroundGridHeight = GameSetting.getNumber(doc, "//Globals/BackgroundGrid/@h").intValue();
		this.isoGridWidth = GameSetting.getNumber(doc, "//Globals/IsoGrid/@w").intValue();
		this.isoGridHeight = GameSetting.getNumber(doc, "//Globals/IsoGrid/@h").intValue();
	}

	public void updateZoomFactor(int count) {
		double x = (this.offset.x + (this.offset.width / 2)) / this.zoomFactor;
		double y = (this.offset.y + (this.offset.height / 2)) / this.zoomFactor;
		if (count > 0 && this.zoomFactor < 1.5) {
			this.zoomFactor *= 1.1;
		} else if (count < 0 && this.zoomFactor > 0.5) {
			this.zoomFactor *= 0.9;
		}
		this.offset.x = (int) (x * this.zoomFactor) - (this.offset.width / 2);
		this.offset.y = (int) (y * this.zoomFactor) - (this.offset.height / 2);
	}

	public void updateOffsetX(int offsetX) {
		this.offset.x += offsetX;
		this.offset.x = Math.max(this.offset.x, this.frame.x);
		this.offset.x = Math.min(this.offset.x, this.frame.width);
	}

	public void updateOffsetY(int offsetY) {
		this.offset.y += offsetY;
		this.offset.y = Math.max(this.offset.y, this.frame.y);
		this.offset.y = Math.min(this.offset.y, this.frame.height);
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

	public void draw(Dimension size, Graphics2D graphics) {
		if (this.doubleBuffer != null) {
			this.offset.width = this.doubleBuffer.getWidth(null);
			this.offset.height = this.doubleBuffer.getHeight(null);
		}
		if (this.doubleBuffer == null || this.offset.width != size.width || this.offset.height != size.height) {
			this.doubleBuffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
			this.offset.width = this.doubleBuffer.getWidth(null);
			this.offset.height = this.doubleBuffer.getHeight(null);
		}

		Rectangle clip = new Rectangle(this.offset);
		Graphics2D g = this.doubleBuffer.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setBackground(graphics.getBackground());
		g.setColor(graphics.getColor());
		g.setFont(graphics.getFont());
		g.fillRect(0, 0, size.width, size.height);
		g.translate(clip.x*-1, clip.y*-1);
		g.scale(this.zoomFactor, this.zoomFactor);

		clip.x = (int) Math.floor(clip.x/this.zoomFactor);
		clip.y = (int) Math.floor(clip.y/this.zoomFactor);
		clip.width = (int) Math.ceil(clip.width/this.zoomFactor);
		clip.height = (int) Math.ceil(clip.height/this.zoomFactor);

		if (this.showBackground || this.debugBackground) {
			this.frame = this.drawBackground(g, clip);
			this.frame.x *= this.zoomFactor;
			this.frame.y *= this.zoomFactor;
			this.frame.width *= this.zoomFactor;
			this.frame.height *= this.zoomFactor;
			this.offset.x = Math.max(this.offset.x, this.frame.x);
			this.offset.y = Math.max(this.offset.y, this.frame.y);
			this.offset.x = Math.min(this.offset.x, this.frame.width);
			this.offset.y = Math.min(this.offset.y, this.frame.height);
		}
		if (this.showFreeLandscape || this.debugFreeLandscape) {
			this.drawFreeLandscape(g, clip);
		}
		if (this.showLandscape || this.debugLandscape) {
			this.drawLandscape(g, clip);
		}
		if (this.showBuilding || this.debugBuilding) {
			this.drawBuildings(g, clip);
		}
		if (this.debugResourceCreations) {
			this.drawResourceCreations(g, clip);
		}
		if (this.debugMapValues) {
			this.drawMapValues(g, clip);
		}

		graphics.drawImage(this.doubleBuffer, 0, 0, null);

		g.dispose();
	}

	private Rectangle drawBackground(Graphics2D g, Rectangle clip) {
		Rectangle frame = new Rectangle();
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
				Rectangle dst = new Rectangle(src);

				dst.x = (int) (((index % length) * width) + sprite.getOffsetX());
				dst.y = (int) ((Math.floor(index / length) * height) + sprite.getOffsetY());

				frame.x = Math.min(frame.x, dst.x);
				frame.y = Math.min(frame.y, dst.y);
				frame.width = Math.max(frame.width, (dst.x + dst.width) - clip.width);
				frame.height =  Math.max(frame.height, (dst.y + dst.height) - clip.height);

				if (clip.intersects(dst)) {
					this.drawImage(g, image, dst, src);

					if (this.debugBackground) {
						g.draw(dst);
						g.drawString("BG: "+name, dst.x, dst.y);
					}
				}
			}

			index++;
		}

		return frame;
	}

	private void drawFreeLandscape(Graphics2D g, Rectangle clip) {
		for (FreeLandscapeVO freeLandscape : this.zoneVO.getFreeLandscapes()) {
			String name = freeLandscape.getName_string();
			Sprite sprite = this.getLandscape(name);

			if (sprite != null) {
				Image image = sprite.getImage();
				Rectangle src = sprite.getBounds();
				Rectangle dst = new Rectangle(src);

				dst.x = (int) (freeLandscape.getX() + sprite.getOffsetX());
				dst.y = (int) (freeLandscape.getY() + sprite.getOffsetY());

				if (clip.intersects(dst)) {
					this.drawImage(g, image, dst, src);

					if (this.debugFreeLandscape) {
						g.draw(dst);
						g.drawString("FL: "+name, dst.x, dst.y);
					}
				}
			}
		}
	}

	private void drawLandscape(Graphics2D g, Rectangle clip) {
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
				Rectangle dst = new Rectangle(src);

				double margin = (Math.floor(index / length) % 2) / 2;

				dst.x = (int) ((((index % length) + margin) * width) + sprite.getOffsetX());
				dst.y = (int) ((Math.floor(index / length) * height) + sprite.getOffsetY());

				if (clip.intersects(dst)) {
					this.drawImage(g, image, dst, src);

					if (this.debugLandscape) {
						g.draw(dst);
						g.drawString("L: "+name, dst.x, dst.y);
					}
				}
			}
		}
	}

	private void drawBuildings(Graphics2D g, Rectangle clip) {
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
				Rectangle dst = new Rectangle(src);

				double margin = (Math.floor(index / length) % 2) / 2;

				dst.x = (int) ((((index % length) + margin) * width) + sprite.getOffsetX());
				dst.y = (int) ((Math.floor(index / length) * height) + sprite.getOffsetY());

				if (clip.intersects(dst)) {
					this.drawImage(g, image, dst, src);

					if (this.debugBuilding) {
						g.draw(dst);
						g.drawString("B: "+name, dst.x, dst.y);
					}

//					int mWayWarehouseToWorkyard = 0;
					int mWayWorkyardToDeposit = 0;
//					int mProductionTime = 0;
//					int mOverallTime = 0;

					int distance = Integer.MAX_VALUE;
					for (BuildingVO warehouse : this.zoneVO.getBuildings()) {
						if (warehouse.getBuildingName_string().equalsIgnoreCase("warehouse") ||
							warehouse.getBuildingName_string().equalsIgnoreCase("mayorhouse")) {
						}
					}

					for (ResourceCreationVO resourceCreation : this.zoneVO.getResourceCreations()) {
						int house = resourceCreation.getResourceCreationHouseGrid();
						if (house == index) {
							PathVO path = resourceCreation.getPathVO();
							if (path == null)
								continue;

							List<de.uxnr.amf.v3.type.Integer> list = path.getPath();
							if (list == null)
								continue;

							int length1 = list.size() * 10000;
//							int length2 = length1 * 2;

							mWayWorkyardToDeposit = (length1 / 5) / 100;
						}
					}

					g.drawString("B: "+name+" ("+mWayWorkyardToDeposit+")", dst.x, dst.y);

					dst.x = (int) (((index % length) + margin) * width) - 2;
					dst.y = (int) (Math.floor(index / length) * height) - 2;
					dst.width = 4;
					dst.height = 4;

					if (clip.intersects(dst)) {
						g.draw(dst);
					}
				}
			}
		}
	}

	private void drawMapValues(Graphics2D g, Rectangle clip) {
		int width = this.isoGridWidth;
		int height = this.isoGridHeight / 2;
		int length = 62;

		Color foreground = g.getColor();

		int index = 0;
		for (MapValueItemVO mapValueItemVO : this.zoneVO.getMapValues()) {
			double margin = (Math.floor(index / length) % 2) / 2;

			Rectangle dst = new Rectangle(width, height);
			dst.x = (int) (((index % length) + margin) * width);
			dst.y = (int) (Math.floor(index / length) * height);

			switch (mapValueItemVO.getBackgroundBlocking()) {
				case 0: // BLOCK_TYPE_ALLOW_ALL
					g.setColor(Color.GREEN);
					break;
				case 1: // BLOCK_TYPE_ALLOW_NOTHING
					g.setColor(Color.RED);
					break;
				case 2: // BLOCK_TYPE_ALLOW_STREETS
					g.setColor(Color.BLUE);
					break;
			}

			if (clip.intersects(dst)) {
				g.draw(dst);
				g.drawString("S: "+mapValueItemVO.getSectorId(), dst.x, dst.y);
			}

			index++;
		}

		g.setColor(foreground);
	}

	private void drawResourceCreations(Graphics2D g, Rectangle clip) {
		int width = this.isoGridWidth;
		int height = this.isoGridHeight / 2;
		int length = 64;

		Color foreground = g.getColor();

		for (ResourceCreationVO resourceCreation : this.zoneVO.getResourceCreations()) {
			int index = resourceCreation.getResourceCreationHouseGrid();

			double margin = (Math.floor(index / length) % 2) / 2;
			Point start = new Point();
			start.x = (int) (((index % length) + margin) * width);
			start.y = (int) (Math.floor(index / length) * height);
			Point end = new Point(start);

			PathVO path = resourceCreation.getPathVO();
			if (path != null) {
				g.setColor(Color.RED);

				for (de.uxnr.amf.v3.type.Integer streetGrid : path.getPath()) {
					index = streetGrid.get();

					end.x = (int) (((index % length) + margin) * width);
					end.y = (int) (Math.floor(index / length) * height);

					g.setColor(Color.BLUE);
					g.drawLine(start.x, start.y, end.x, end.y);

					start.setLocation(end);
				}
			}
		}

		g.setColor(foreground);
	}

	private Sprite getBackground(String name) {
		return this.getSprite(name, -1, -1, "GFX/background_lib/", "//GameObjects/Backgrounds/Background[@name='"+name+"']/@filename");
	}

	private Sprite getLandscape(String name) {
		return this.getSprite(name, -1, -1, "GFX/landscape_lib/", "//GameObjects/Landscapes/Landscape[@name='"+name+"']/@filename");
	}

	private Sprite getBuilding(String name, int level, int type) {
		Double nofUpgrades;
		if (this.nofUpgrades.containsKey(name)) {
			nofUpgrades = this.nofUpgrades.get(name);
		} else {
			Document doc = this.xmlHandler.getDocument(GameSetting.gfx_settings);
			nofUpgrades = GameSetting.getNumber(doc, "//GameObjects/Buildings/Building[@name='"+name+"']/@nofUpgrades");
			this.nofUpgrades.put(name, nofUpgrades);
		}
		if (nofUpgrades != null) {
			level = Math.min(level, nofUpgrades.intValue()) - 1;
		} else {
			level = 0;
		}
		return this.getSprite(name, level, type, "GFX/building_lib/", "//GameObjects/Buildings/Building[@name='"+name+"']/@filename");
	}

	private Sprite getSprite(String name, int level, int type, String path, String xpath) {
		SpriteHandler spriteHandler = (SpriteHandler) TSOExpert.getHandler("SpriteHandler");
		return spriteHandler.getSprite(name, level, type, path, xpath);
	}

	private boolean drawImage(Graphics2D g, Image image, Rectangle dst, Rectangle src) {
		return g.drawImage(image, dst.x, dst.y, dst.x+dst.width, dst.y+dst.height, src.x, src.y, src.x+src.width, src.y+src.height, null);
	}
}
