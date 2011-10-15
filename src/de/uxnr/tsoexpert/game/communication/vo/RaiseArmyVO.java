package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class RaiseArmyVO extends AMF3_Object {
	private BuildingVO armyHolderBuildingVO;
	private SpecialistVO armyHolderSpecialistVO;
	private Collection<SquadVO> unitSquads;

	public BuildingVO getArmyHolderBuildingVO() {
		return this.armyHolderBuildingVO;
	}

	public SpecialistVO getArmyHolderSpecialistVO() {
		return this.armyHolderSpecialistVO;
	}

	public Collection<SquadVO> getUnitSquads() {
		return this.unitSquads;
	}
}
