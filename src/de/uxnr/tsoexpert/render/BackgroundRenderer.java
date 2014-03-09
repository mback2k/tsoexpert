package de.uxnr.tsoexpert.render;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.uxnr.tsoexpert.model.Background;
import de.uxnr.tsoexpert.model.grid.GridPosition;
import de.uxnr.tsoexpert.resource.ISpriteHandler;

public class BackgroundRenderer extends AbstractRenderer {
  private static BackgroundRenderer instance;

  private BackgroundRenderer() {}

  public static BackgroundRenderer getInstance() {
    if (BackgroundRenderer.instance == null) {
      BackgroundRenderer.instance = new BackgroundRenderer();
    }
    return BackgroundRenderer.instance;
  }

  private final Map<Background, Sprite> sprites = new HashMap<Background, Sprite>();

  private Sprite getSprite(final Background background) {
    if (this.sprites.containsKey(background))
      return this.sprites.get(background);

    String name = background.getName();

    String filename =
        this.getSpriteFilename("//GameObjects/Backgrounds/Background[@name='" + name
            + "']/@filename");
    Sprite sprite = this.getSprite("GFX/background_lib/", filename, new ISpriteHandler() {
      @Override
      public void handleSprite(String path, Sprite sprite) throws IOException {
        sprites.put(background, sprite);
      }
    });

    this.sprites.put(background, sprite);

    return sprite;
  }

  public Rectangle renderBackground(Background background, Graphics2D graphics, Rectangle clip) {
    if (this.getMode() == Mode.HIDE)
      return null;

    Sprite sprite = this.getSprite(background);
    if (sprite == null)
      return null;

    Image image = sprite.getImage();
    Rectangle src = sprite.getBounds();
    Rectangle dst = new Rectangle(src);

    GridPosition position = background.getPosition();
    dst.x = (int) (position.getX() + sprite.getOffsetX());
    dst.y = (int) (position.getY() + sprite.getOffsetY());

    if (!clip.intersects(dst))
      return dst;

    this.drawImage(graphics, image, dst, src);

    if (this.getMode() == Mode.DEBUG) {
      String name = background.getName();

      graphics.draw(dst);
      graphics.drawString("BG: " + name, dst.x, dst.y);
    }

    return dst;
  }
}
