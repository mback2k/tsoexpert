package de.uxnr.tsoexpert.model;

import de.uxnr.tsoexpert.game.communication.vo.FreeLandscapeVO;

public class FreeLandscape {
	private String name;
	private GridPosition position;

	public FreeLandscape(String name, GridPosition position) {
		this.name = name;
		this.position = position;
	}

	public FreeLandscape(FreeLandscapeVO freeLandscapeVO) {
		this(freeLandscapeVO.getName_string(), new FreeGridPosition(freeLandscapeVO.getX(), freeLandscapeVO.getY()));
	}

	public String getName() {
		return this.name;
	}

	public GridPosition getPosition() {
		return this.position;
	}

	public int hashCode() {
		return this.name.hashCode() ^ this.position.hashCode();
	}
}
