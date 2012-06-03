package de.uxnr.tsoexpert.ui.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import org.w3c.dom.Document;

import de.uxnr.tsoexpert.TSOExpert;
import de.uxnr.tsoexpert.game.communication.vo.MapValueItemVO;
import de.uxnr.tsoexpert.game.communication.vo.PathVO;
import de.uxnr.tsoexpert.game.communication.vo.ResourceCreationVO;
import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;
import de.uxnr.tsoexpert.registry.BackgroundRegistry;
import de.uxnr.tsoexpert.registry.BuildingRegistry;
import de.uxnr.tsoexpert.registry.FreeLandscapeRegistry;
import de.uxnr.tsoexpert.registry.LandscapeRegistry;
import de.uxnr.tsoexpert.render.AbstractRenderer.Mode;
import de.uxnr.tsoexpert.render.BackgroundRenderer;
import de.uxnr.tsoexpert.render.BuildingRenderer;
import de.uxnr.tsoexpert.render.FreeLandscapeRenderer;
import de.uxnr.tsoexpert.render.LandscapeRenderer;
import de.uxnr.tsoexpert.render.ZoneRenderer;
import de.uxnr.tsoexpert.resource.GameSetting;
import de.uxnr.tsoexpert.resource.XMLHandler;

public class ZoneMap {
	private final ZoneRenderer zoneRenderer = new ZoneRenderer();

	private final int isoGridWidth;
	private final int isoGridHeight;

	private final XMLHandler xmlHandler;
	private final ZoneVO zoneVO;

	private BufferedImage doubleBuffer = null;
	private Rectangle offset = new Rectangle();
	private Rectangle frame = new Rectangle();
	private int zoomFactor = 50;

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
		this.isoGridWidth = GameSetting.getNumber(doc, "//Globals/IsoGrid/@w").intValue();
		this.isoGridHeight = GameSetting.getNumber(doc, "//Globals/IsoGrid/@h").intValue();

		BackgroundRegistry.getInstance().addAll(this.zoneVO.getBackgroundTiles());
		FreeLandscapeRegistry.getInstance().addAll(this.zoneVO.getFreeLandscapes());
		LandscapeRegistry.getInstance().addAll(this.zoneVO.getLandscapes());
		BuildingRegistry.getInstance().addAll(this.zoneVO.getBuildings());
	}

	public double getZoomFactor() {
		return (double) (this.zoomFactor / 100.0);
	}

	public void updateZoomFactor(int count) {
		double oldZoomFactor = this.getZoomFactor();
		double offsetX = (this.offset.x + (this.offset.width / 2)) / oldZoomFactor;
		double offsetY = (this.offset.y + (this.offset.height / 2)) / oldZoomFactor;
		double frameX = this.frame.x / oldZoomFactor;
		double frameY = this.frame.y / oldZoomFactor;
		double frameWidth = (this.frame.width / oldZoomFactor) + Math.ceil(this.offset.width / oldZoomFactor);
		double frameHeight = (this.frame.height / oldZoomFactor) + Math.ceil(this.offset.height / oldZoomFactor);
		this.zoomFactor += 10 * count;
		this.zoomFactor = Math.max(this.zoomFactor, 20);
		this.zoomFactor = Math.min(this.zoomFactor, 100);
		double newZoomFactor = this.getZoomFactor();
		this.frame.x = (int) (frameX * newZoomFactor);
		this.frame.y = (int) (frameY * newZoomFactor);
		this.frame.width = (int) ((frameWidth - Math.ceil(this.offset.width / newZoomFactor)) * newZoomFactor);
		this.frame.height = (int) ((frameHeight - Math.ceil(this.offset.height / newZoomFactor)) * newZoomFactor);
		this.offset.x = (int) (offsetX * newZoomFactor) - (this.offset.width / 2);
		this.offset.y = (int) (offsetY * newZoomFactor) - (this.offset.height / 2);
		this.offset.x = Math.max(this.offset.x, this.frame.x);
		this.offset.y = Math.max(this.offset.y, this.frame.y);
		this.offset.x = Math.min(this.offset.x, this.frame.width);
		this.offset.y = Math.min(this.offset.y, this.frame.height);
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
		if (this.showBackground)
			BackgroundRenderer.getInstance().setMode(Mode.SHOW);
	}

	public void setShowFreeLandscape(boolean showFreeLandscape) {
		this.showFreeLandscape = showFreeLandscape;
		if (this.showFreeLandscape)
			FreeLandscapeRenderer.getInstance().setMode(Mode.SHOW);
	}

	public void setShowLandscape(boolean showLandscape) {
		this.showLandscape = showLandscape;
		if (this.showLandscape)
			LandscapeRenderer.getInstance().setMode(Mode.SHOW);
	}

	public void setShowBuilding(boolean showBuilding) {
		this.showBuilding = showBuilding;
		if (this.showBuilding)
			BuildingRenderer.getInstance().setMode(Mode.SHOW);
	}

	public void setDebugBackground(boolean debugBackground) {
		this.debugBackground = debugBackground;
		if (this.debugBackground)
			BackgroundRenderer.getInstance().setMode(Mode.DEBUG);
	}

	public void setDebugFreeLandscape(boolean debugFreeLandscape) {
		this.debugFreeLandscape = debugFreeLandscape;
		if (this.debugFreeLandscape)
			FreeLandscapeRenderer.getInstance().setMode(Mode.DEBUG);
	}

	public void setDebugLandscape(boolean debugLandscape) {
		this.debugLandscape = debugLandscape;
		if (this.debugLandscape)
			LandscapeRenderer.getInstance().setMode(Mode.DEBUG);
	}

	public void setDebugBuilding(boolean debugBuilding) {
		this.debugBuilding = debugBuilding;
		if (this.debugBuilding)
			BuildingRenderer.getInstance().setMode(Mode.DEBUG);
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

		double zoomFactor = this.getZoomFactor();

		Rectangle clip = new Rectangle(this.offset);
		Graphics2D g = this.doubleBuffer.createGraphics();

//		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setBackground(graphics.getBackground());
		g.setColor(graphics.getColor());
		g.setFont(graphics.getFont());
		g.fillRect(0, 0, size.width, size.height);
		g.translate(clip.x*-1, clip.y*-1);
		g.scale(zoomFactor, zoomFactor);

		clip.x = (int) Math.floor(clip.x / zoomFactor);
		clip.y = (int) Math.floor(clip.y / zoomFactor);
		clip.width = (int) Math.ceil(clip.width / zoomFactor);
		clip.height = (int) Math.ceil(clip.height / zoomFactor);

		this.frame = this.zoneRenderer.renderZone(g, clip);
		this.frame.width -= clip.width;
		this.frame.height -= clip.height;
		this.frame.x *= zoomFactor;
		this.frame.y *= zoomFactor;
		this.frame.width *= zoomFactor;
		this.frame.height *= zoomFactor;
		this.offset.x = Math.max(this.offset.x, this.frame.x);
		this.offset.y = Math.max(this.offset.y, this.frame.y);
		this.offset.x = Math.min(this.offset.x, this.frame.width);
		this.offset.y = Math.min(this.offset.y, this.frame.height);

		if (this.debugResourceCreations) {
			this.drawResourceCreations(g, clip);
		}
		if (this.debugMapValues) {
			this.drawMapValues(g, clip);
		}

		graphics.drawImage(this.doubleBuffer, 0, 0, null);

		g.dispose();
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
}
