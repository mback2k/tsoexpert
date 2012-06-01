package de.uxnr.tsoexpert.resource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.uxnr.tsoexpert.TSOHandler;
import de.uxnr.tsoexpert.file.FileData;
import de.uxnr.tsoexpert.proxy.IResourceHandler;
import de.uxnr.tsoexpert.render.Sprite;

public class SpriteHandler implements TSOHandler, IResourceHandler {
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
	}

	public Sprite getSprite(String folder, String filename) {
		String path = new File(folder, filename).getPath();

		return this.sprites.get(path);
	}
}
