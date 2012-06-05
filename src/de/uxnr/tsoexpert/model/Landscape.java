package de.uxnr.tsoexpert.model;

import de.uxnr.tsoexpert.game.communication.vo.LandscapeVO;
import de.uxnr.tsoexpert.model.grid.IsoGridPosition;

public class Landscape {
	private String name;
	private IsoGridPosition position;

	public Landscape(String name, IsoGridPosition position) {
		this.name = name;
		this.position = position;
	}

	public Landscape(LandscapeVO landscapeVO) {
		this(landscapeVO.getName_string(), new IsoGridPosition(landscapeVO.getGrid()));
	}

	public String getName() {
		return this.name;
	}

	public IsoGridPosition getPosition() {
		return this.position;
	}

	public int hashCode() {
		return this.name.hashCode() ^ this.position.hashCode();
	}
}
