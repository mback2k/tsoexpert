package de.uxnr.tsoexpert.resource;

import java.io.IOException;

import de.uxnr.tsoexpert.render.Sprite;

public interface ISpriteHandler {
  public void handleSprite(String path, Sprite sprite) throws IOException;
}
