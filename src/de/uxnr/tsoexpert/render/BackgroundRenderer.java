package de.uxnr.tsoexpert.render;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import de.uxnr.tsoexpert.model.Background;
import de.uxnr.tsoexpert.model.GridPosition;

public class BackgroundRenderer extends AbstractRenderer {
	private final Map<Background, Sprite> sprites = new HashMap<Background, Sprite>();

	private Sprite getSprite(Background background) {
		if (this.sprites.containsKey(background))
			return this.sprites.get(background);
		
		String name = background.getName();
		
		String filename = this.getSpriteFilename("//GameObjects/Backgrounds/Background[@name='"+name+"']/@filename");
		Sprite sprite = this.getSprite("GFX/background_lib/", filename);

		if (sprite != null)
			this.sprites.put(background, sprite);

		return sprite;
	}

	public Rectangle renderBackground(Background background, Graphics2D graphics, Rectangle clip) {
		if (this.getMode() == Mode.HIDE)
			return null;

		Sprite sprite = this.getSprite(background);
		GridPosition position = background.getPosition();
		
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
			String name = background.getName();
			
			graphics.draw(dst);
			graphics.drawString("BG: "+name, dst.x, dst.y);
		}

		return dst;
	}
}
