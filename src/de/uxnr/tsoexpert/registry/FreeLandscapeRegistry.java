package de.uxnr.tsoexpert.registry;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import de.uxnr.tsoexpert.game.communication.vo.FreeLandscapeVO;
import de.uxnr.tsoexpert.model.FreeLandscape;
import de.uxnr.tsoexpert.model.GridPosition;
import de.uxnr.tsoexpert.render.FreeLandscapeRenderer;

public class FreeLandscapeRegistry {
	private final Map<GridPosition, FreeLandscape> freeLandscapes = new TreeMap<GridPosition, FreeLandscape>();

	public void add(FreeLandscapeVO freeLandscapeVO) {
		FreeLandscape freeLandscape = new FreeLandscape(freeLandscapeVO);
		this.freeLandscapes.put(freeLandscape.getPosition(), freeLandscape); // TODO: Fire event
	}

	public void addAll(Collection<FreeLandscapeVO> freeLandscapeVOs) {
		for (FreeLandscapeVO freeLandscapeVO : freeLandscapeVOs) {
			this.add(freeLandscapeVO); // TODO: Fire event
		}
	}

	public void remove(FreeLandscapeVO freeLandscapeVO) {
		FreeLandscape freeLandscape = new FreeLandscape(freeLandscapeVO);
		this.freeLandscapes.remove(freeLandscape); // TODO: Fire event
	}

	public void clear() {
		for (FreeLandscape freeLandscape : this.freeLandscapes.values()) {
			this.freeLandscapes.remove(freeLandscape); // TODO: Fire event
		}
	}

	public Rectangle renderFreeLandscapes(FreeLandscapeRenderer freeLandscapeRenderer, Graphics2D graphics, Rectangle clip) {
		Rectangle frame = new Rectangle();
		for (FreeLandscape freeLandscape : this.freeLandscapes.values()) {
			Rectangle sprite = freeLandscapeRenderer.renderFreeLandscape(freeLandscape, graphics, clip);
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
