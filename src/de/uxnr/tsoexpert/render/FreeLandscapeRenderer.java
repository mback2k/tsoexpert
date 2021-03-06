package de.uxnr.tsoexpert.render;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.uxnr.tsoexpert.model.FreeLandscape;
import de.uxnr.tsoexpert.model.grid.GridPosition;
import de.uxnr.tsoexpert.resource.ISpriteHandler;

public class FreeLandscapeRenderer extends AbstractRenderer {
  private static FreeLandscapeRenderer instance;

  private FreeLandscapeRenderer() {}

  public static FreeLandscapeRenderer getInstance() {
    if (FreeLandscapeRenderer.instance == null) {
      FreeLandscapeRenderer.instance = new FreeLandscapeRenderer();
    }
    return FreeLandscapeRenderer.instance;
  }

  private final Map<FreeLandscape, Sprite> sprites = new HashMap<FreeLandscape, Sprite>();

  private Sprite getSprite(final FreeLandscape freeLandscape) {
    if (this.sprites.containsKey(freeLandscape))
      return this.sprites.get(freeLandscape);

    String name = freeLandscape.getName();

    String filename =
        this.getSpriteFilename("//GameObjects/Landscapes/Landscape[@name='" + name + "']/@filename");
    Sprite sprite = this.getSprite("GFX/landscape_lib/", filename, new ISpriteHandler() {
      @Override
      public void handleSprite(String path, Sprite sprite) throws IOException {
        sprites.put(freeLandscape, sprite);
      }
    });

    this.sprites.put(freeLandscape, sprite);

    return sprite;
  }

  public Rectangle renderFreeLandscape(FreeLandscape freeLandscape, Graphics2D graphics,
      Rectangle clip) {
    if (this.getMode() == Mode.HIDE)
      return null;

    Sprite sprite = this.getSprite(freeLandscape);
    if (sprite == null)
      return null;

    Image image = sprite.getImage();
    Rectangle src = sprite.getBounds();
    Rectangle dst = new Rectangle(src);

    GridPosition position = freeLandscape.getPosition();
    dst.x = (int) (position.getX() + sprite.getOffsetX());
    dst.y = (int) (position.getY() + sprite.getOffsetY());

    if (!clip.intersects(dst))
      return dst;

    this.drawImage(graphics, image, dst, src);

    if (this.getMode() == Mode.DEBUG) {
      String name = freeLandscape.getName();

      graphics.draw(dst);
      graphics.drawString("FL: " + name, dst.x, dst.y);
    }

    return dst;
  }
}
