package de.uxnr.tsoexpert.registry;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import de.uxnr.tsoexpert.game.communication.vo.LandscapeVO;
import de.uxnr.tsoexpert.model.Landscape;
import de.uxnr.tsoexpert.model.grid.IsoGridPosition;
import de.uxnr.tsoexpert.render.LandscapeRenderer;

public class LandscapeRegistry {
	private final Map<IsoGridPosition, Landscape> landscapes = new TreeMap<IsoGridPosition, Landscape>();

	public void add(LandscapeVO landscapeVO) {
		Landscape landscape = new Landscape(landscapeVO);
		this.landscapes.put(landscape.getPosition(), landscape); // TODO: Fire event
	}

	public void addAll(Collection<LandscapeVO> landscapeVOs) {
		for (LandscapeVO landscapeVO : landscapeVOs) {
			this.add(landscapeVO); // TODO: Fire event
		}
	}

	public void remove(LandscapeVO landscapeVO) {
		Landscape landscape = new Landscape(landscapeVO);
		this.landscapes.remove(landscape); // TODO: Fire event
	}

	public void clear() {
		for (Landscape landscape : this.landscapes.values()) {
			this.landscapes.remove(landscape); // TODO: Fire event
		}
	}

	public Rectangle renderLandscapes(LandscapeRenderer landscapeRenderer, Graphics2D graphics, Rectangle clip) {
		Rectangle frame = new Rectangle();
		for (Landscape landscape : this.landscapes.values()) {
			Rectangle sprite = landscapeRenderer.renderLandscape(landscape, graphics, clip);
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
