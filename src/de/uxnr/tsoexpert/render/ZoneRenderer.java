package de.uxnr.tsoexpert.render;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Map;
import java.util.Map.Entry;

import de.uxnr.tsoexpert.model.Background;
import de.uxnr.tsoexpert.model.Building;
import de.uxnr.tsoexpert.model.FreeLandscape;
import de.uxnr.tsoexpert.model.Landscape;
import de.uxnr.tsoexpert.model.grid.GridPosition;
import de.uxnr.tsoexpert.registry.ZoneObjectRegistry;

public class ZoneRenderer {
  public Rectangle renderZone(Graphics2D graphics, Rectangle clip) {
    Map<GridPosition, Object> objects = ZoneObjectRegistry.getInstance().getAll();

    BackgroundRenderer backgroundRenderer = BackgroundRenderer.getInstance();
    FreeLandscapeRenderer freeLandscapeRenderer = FreeLandscapeRenderer.getInstance();
    LandscapeRenderer landscapeRenderer = LandscapeRenderer.getInstance();
    BuildingRenderer buildingRenderer = BuildingRenderer.getInstance();

    Rectangle frame = new Rectangle();

    for (Entry<GridPosition, Object> entry : objects.entrySet()) {
      Object object = entry.getValue();
      if (object instanceof Background) {
        this.renderBackground((Background) object, backgroundRenderer, graphics, clip, frame);
      } else if (object instanceof FreeLandscape) {
        this.renderFreeLandscape((FreeLandscape) object, freeLandscapeRenderer, graphics, clip,
            frame);
      } else if (object instanceof Landscape) {
        this.renderLandscape((Landscape) object, landscapeRenderer, graphics, clip, frame);
      } else if (object instanceof Building) {
        this.renderBuilding((Building) object, buildingRenderer, graphics, clip, frame);
      }
    }

    return frame;
  }

  protected void renderBackground(Background background, BackgroundRenderer backgroundRenderer,
      Graphics2D graphics, Rectangle clip, Rectangle frame) {
    this.updateFrame(backgroundRenderer.renderBackground(background, graphics, clip), frame);
  }

  protected void renderFreeLandscape(FreeLandscape freeLandscape,
      FreeLandscapeRenderer freeLandscapeRenderer, Graphics2D graphics, Rectangle clip,
      Rectangle frame) {
    this.updateFrame(freeLandscapeRenderer.renderFreeLandscape(freeLandscape, graphics, clip),
        frame);
  }

  protected void renderLandscape(Landscape landscape, LandscapeRenderer landscapeRenderer,
      Graphics2D graphics, Rectangle clip, Rectangle frame) {
    this.updateFrame(landscapeRenderer.renderLandscape(landscape, graphics, clip), frame);
  }

  protected void renderBuilding(Building building, BuildingRenderer buildingRenderer,
      Graphics2D graphics, Rectangle clip, Rectangle frame) {
    this.updateFrame(BuildingRenderer.getInstance().renderBuilding(building, graphics, clip), frame);
  }

  private void updateFrame(Rectangle sprite, Rectangle frame) {
    if (sprite != null) {
      frame.x = Math.min(frame.x, sprite.x);
      frame.y = Math.min(frame.y, sprite.y);
      frame.width = Math.max(frame.width, sprite.x + sprite.width);
      frame.height = Math.max(frame.height, sprite.y + sprite.height);
    }
  }
}
