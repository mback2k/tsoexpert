package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class PlayerListVO extends AMF3_Object {
	private Collection<PlayerListItemVO> players;

	public Collection<PlayerListItemVO> getPlayers() {
		return this.players;
	}
}
