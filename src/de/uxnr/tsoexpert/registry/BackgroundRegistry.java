package de.uxnr.tsoexpert.registry;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import de.uxnr.tsoexpert.game.communication.vo.BackgroundTileVO;
import de.uxnr.tsoexpert.model.Background;
import de.uxnr.tsoexpert.model.BackgroundGridPosition;
import de.uxnr.tsoexpert.render.BackgroundRenderer;

public class BackgroundRegistry {
	private final Map<BackgroundGridPosition, Background> backgrounds = new TreeMap<BackgroundGridPosition, Background>();

	public void add(BackgroundTileVO backgroundTileVO, int index) {
		Background background = new Background(backgroundTileVO, index);
		this.backgrounds.put(background.getPosition(), background); // TODO: Fire event
	}

	public void addAll(Collection<BackgroundTileVO> backgroundTileVOs) {
		int index = 0;
		for (BackgroundTileVO backgroundTileVO : backgroundTileVOs) {
			this.add(backgroundTileVO, index++); // TODO: Fire event
		}
	}

	public void remove(BackgroundTileVO backgroundTileVO, int index) {
		Background background = new Background(backgroundTileVO, index);
		this.backgrounds.remove(background); // TODO: Fire event
	}

	public void clear() {
		for (Background background : this.backgrounds.values()) {
			this.backgrounds.remove(background); // TODO: Fire event
		}
	}

	public Rectangle renderBackgrounds(BackgroundRenderer backgroundRenderer, Graphics2D graphics, Rectangle clip) {
		Rectangle frame = new Rectangle();
		for (Background background : this.backgrounds.values()) {
			Rectangle sprite = backgroundRenderer.renderBackground(background, graphics, clip);
			if (sprite != null) {
				frame.x = Math.min(frame.x, sprite.x);
				frame.y = Math.min(frame.y, sprite.y);
				frame.width = Math.max(frame.width, sprite.x + sprite.width);
				frame.height =  Math.max(frame.height, sprite.y + sprite.height);
			}
		}
		return frame;
	}
}
