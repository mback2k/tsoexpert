package de.uxnr.tsoexpert.resource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

import de.uxnr.tsoexpert.TSOHandler;
import de.uxnr.tsoexpert.file.FileData;
import de.uxnr.tsoexpert.map.Sprite;

public class SpriteHandler implements TSOHandler, IResourceHandler {
	private final Map<String, FileData> binMap = new HashMap<String, FileData>();
	private final Map<String, FileData> gfxMap = new HashMap<String, FileData>();
	private final Map<String, Sprite> sprites = new HashMap<String, Sprite>();
	private final Map<String, Sprite> cache = new HashMap<String, Sprite>();

	private XMLHandler xmlHandler;
	private Document doc;

	public SpriteHandler(XMLHandler xmlHandler) {
		this.xmlHandler = xmlHandler;
	}

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

	public Sprite getSprite(String name, int level, int type, String path, String xpath) {
		Sprite sprite = null;
		String key = name + level + type;

		if (this.cache.containsKey(key)) {
			sprite = this.cache.get(key);

		} else {
			if (this.doc == null) {
				this.doc = this.xmlHandler.getDocument(GameSetting.gfx_settings);
			}

			String filename = GameSetting.getString(doc, xpath);
			if (filename != null) {
				if (level != -1 && type != -1) {
					StringBuilder sb = new StringBuilder(filename);
					sb.insert(filename.lastIndexOf("."), "["+level+"_"+type+"]");
					filename = sb.toString();
				}

				path = new File(path, filename).getPath();
				if (this.sprites.containsKey(path)) {
					sprite = this.sprites.get(path);
					this.cache.put(key, sprite);
				}
			}
		}

		return sprite;
	}
}
