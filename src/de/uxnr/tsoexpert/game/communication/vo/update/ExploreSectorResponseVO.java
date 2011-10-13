package de.uxnr.tsoexpert.game.communication.vo.update;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class ExploreSectorResponseVO extends AMF3_Object {
	private Collection<ExploredSectorVO> exploredSectors_vector;

	public Collection<ExploredSectorVO> getExploredSectors_vector() {
		return this.exploredSectors_vector;
	}
}
