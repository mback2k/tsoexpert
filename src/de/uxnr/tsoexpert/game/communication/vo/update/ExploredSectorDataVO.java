package de.uxnr.tsoexpert.game.communication.vo.update;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.BuildingVO;
import de.uxnr.tsoexpert.game.communication.vo.DepositVO;
import de.uxnr.tsoexpert.game.communication.vo.LandscapeVO;
import de.uxnr.tsoexpert.game.communication.vo.ResourceCreationVO;
import de.uxnr.tsoexpert.game.communication.vo.StreetVO;

public class ExploredSectorDataVO extends AMF3_Object {
	private Collection<BuildingVO> buildings;
	private Collection<DepositVO> deposits;
	private Collection<LandscapeVO> landscapes;
	private Collection<ResourceCreationVO> resourceCreations;
	private Collection<StreetVO> streets;

	public Collection<BuildingVO> getBuildings() {
		return this.buildings;
	}

	public Collection<DepositVO> getDeposits() {
		return this.deposits;
	}

	public Collection<LandscapeVO> getLandscapes() {
		return this.landscapes;
	}

	public Collection<ResourceCreationVO> getResourceCreations() {
		return this.resourceCreations;
	}

	public Collection<StreetVO> getStreets() {
		return this.streets;
	}
}
