package de.uxnr.tsoexpert.resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.uxnr.proxy.util.ListMap;
import de.uxnr.tsoexpert.TSOHandler;
import de.uxnr.tsoexpert.file.FileData;
import de.uxnr.tsoexpert.proxy.IResourceHandler;
import de.uxnr.tsoexpert.render.Sprite;

public class SpriteHandler implements TSOHandler, IResourceHandler {
	private final ListMap<String, ISpriteHandler> spriteHandlers = new ListMap<String, ISpriteHandler>();

	private final Map<String, FileData> binMap = new HashMap<String, FileData>();
	private final Map<String, FileData> gfxMap = new HashMap<String, FileData>();
	private final Map<String, Sprite> sprites = new HashMap<String, Sprite>();

	@Override
	public void handleResource(FileData fd) throws IOException {
		String path = fd.getPath();
		String name = path.substring(0, path.lastIndexOf("."));

		if (path.endsWith(".bin")) {
			this.binMap.put(name, fd);
		} else {
			this.gfxMap.put(name, fd);
		}

		if (this.binMap.containsKey(name) && this.gfxMap.containsKey(name)) {
			this.handleSprite(this.binMap.remove(name), this.gfxMap.remove(name));
		}
	}

	private void handleSprite(FileData bin, FileData gfx) throws IOException {
		String path = gfx.getPath();
		Sprite sprite = new Sprite(bin, gfx);

		this.sprites.put(path, sprite);
		this.handleSprite(path, sprite);
	}

	public Sprite getSprite(String path) {
		return this.sprites.get(path);
	}
	
	public synchronized void handleSprite(String path, Sprite sprite) throws IOException {
		if (this.spriteHandlers.containsKey(path)) {
			for (ISpriteHandler spriteHandler : this.spriteHandlers.get(path)) {
				try {
					spriteHandler.handleSprite(path, sprite);
				} catch (Exception e) {
					throw new IOException(e);
				}
				this.spriteHandlers.remove(path);
			}
		}
	}

	public synchronized void addSpriteHandler(String path, ISpriteHandler spriteHandler) {
		this.spriteHandlers.add(path, spriteHandler);
	}

	public synchronized void removeSpriteHandler(String path, ISpriteHandler spriteHandler) {
		this.spriteHandlers.remove(path, spriteHandler);
	}

	public synchronized void removeSpriteHandlers(String path) {
		this.spriteHandlers.remove(path);
	}
}
