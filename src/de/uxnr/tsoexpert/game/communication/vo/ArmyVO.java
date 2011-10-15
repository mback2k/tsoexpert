package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class ArmyVO extends AMF3_Object {
	private Collection<SquadVO> squads;

	public Collection<SquadVO> getSquads() {
		return this.squads;
	}
}
