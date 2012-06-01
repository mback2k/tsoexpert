package de.uxnr.tsoexpert.render;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import de.uxnr.tsoexpert.model.Building;
import de.uxnr.tsoexpert.model.GridPosition;
import de.uxnr.tsoexpert.resource.GameSetting;

public class BuildingRenderer extends AbstractRenderer {
	private final Map<String, Double> nofUpgrades = new HashMap<String, Double>();
	private final Map<Building, Sprite> sprites = new HashMap<Building, Sprite>();

	private int getAvailableLevel(String name, int level) {
		Double nofUpgrades;
		if (this.nofUpgrades.containsKey(name)) {
			nofUpgrades = this.nofUpgrades.get(name);
		} else {
			nofUpgrades = GameSetting.getNumber(this.getGfxSettings(), "//GameObjects/Buildings/Building[@name='"+name+"']/@nofUpgrades");
			this.nofUpgrades.put(name, nofUpgrades);
		}
		if (nofUpgrades != null) {
			level = Math.min(level, nofUpgrades.intValue()) - 1;
		} else {
			level = 0;
		}
		return level;
	}

	private String getSpriteFilename(String xpath, int level, int type) {
		String filename = this.getSpriteFilename(xpath);
		if (level != -1 && type != -1) {
			StringBuilder sb = new StringBuilder(filename);
			sb.insert(filename.lastIndexOf("."), "["+level+"_"+type+"]");
			filename = sb.toString();
		}
		return filename;
	}

	private Sprite getSprite(Building building) {
		if (this.sprites.containsKey(building))
			return this.sprites.get(building);
		
		String name = building.getName();
		int level = getAvailableLevel(name, building.getLevel());
		int type = 0; // TODO: Figure out what this part of the filename is used for
		
		String filename = this.getSpriteFilename("//GameObjects/Buildings/Building[@name='"+name+"']/@filename", level, type);
		Sprite sprite = this.getSprite("GFX/building_lib/", filename);

		if (sprite != null)
			this.sprites.put(building, sprite);

		return sprite;
	}

	public Rectangle renderBuilding(Building building, Graphics2D graphics, Rectangle clip) {
		if (this.getMode() == Mode.HIDE)
			return null;

		Sprite sprite = this.getSprite(building);
		GridPosition position = building.getPosition();
		
		if (sprite == null)
			return null;
		
		Image image = sprite.getImage();
		Rectangle src = sprite.getBounds();
		Rectangle dst = new Rectangle(src);

		dst.x = (int) (position.getX() + sprite.getOffsetX());
		dst.y = (int) (position.getY() + sprite.getOffsetY());

		if (!clip.intersects(dst))
			return dst;

		this.drawImage(graphics, image, dst, src);
		
		if (this.getMode() == Mode.DEBUG) {
			String name = building.getName();
			
			graphics.draw(dst);
			graphics.drawString("B: "+name, dst.x, dst.y);
		}

		dst.x = position.getX() - 2;
		dst.y = position.getY() - 2;
		dst.width = 4;
		dst.height = 4;
		
		if (clip.intersects(dst)) {
			graphics.draw(dst);
		}

		return dst;
	}
}
