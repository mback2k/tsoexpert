package de.uxnr.tsoexpert.render;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.uxnr.tsoexpert.model.Landscape;
import de.uxnr.tsoexpert.model.grid.GridPosition;
import de.uxnr.tsoexpert.resource.ISpriteHandler;

public class LandscapeRenderer extends AbstractRenderer {
	private static LandscapeRenderer instance;

	private LandscapeRenderer() {
	}

	public static LandscapeRenderer getInstance() {
		if (LandscapeRenderer.instance == null) {
			LandscapeRenderer.instance = new LandscapeRenderer();
		}
		return LandscapeRenderer.instance;
	}

	private final Map<Landscape, Sprite> sprites = new HashMap<Landscape, Sprite>();

	private Sprite getSprite(final Landscape landscape) {
		if (this.sprites.containsKey(landscape))
			return this.sprites.get(landscape);
		
		String name = landscape.getName();
		
		String filename = this.getSpriteFilename("//GameObjects/Landscapes/Landscape[@name='"+name+"']/@filename");
		Sprite sprite = this.getSprite("GFX/landscape_lib/", filename, new ISpriteHandler() {
			@Override
			public void handleSprite(String path, Sprite sprite) throws IOException {
				sprites.put(landscape, sprite);
			}
		});

		this.sprites.put(landscape, sprite);

		return sprite;
	}

	public Rectangle renderLandscape(Landscape landscape, Graphics2D graphics, Rectangle clip) {
		if (this.getMode() == Mode.HIDE)
			return null;

		Sprite sprite = this.getSprite(landscape);
		if (sprite == null)
			return null;
		
		Image image = sprite.getImage();
		Rectangle src = sprite.getBounds();
		Rectangle dst = new Rectangle(src);

		GridPosition position = landscape.getPosition();
		dst.x = (int) (position.getX() + sprite.getOffsetX());
		dst.y = (int) (position.getY() + sprite.getOffsetY());

		if (!clip.intersects(dst))
			return dst;

		this.drawImage(graphics, image, dst, src);
		
		if (this.getMode() == Mode.DEBUG) {
			String name = landscape.getName();
			
			graphics.draw(dst);
			graphics.drawString("L: "+name, dst.x, dst.y);
		}

		return dst;
	}
}
