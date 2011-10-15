package de.uxnr.tsoexpert.game.communication.vo.guild;

import de.uxnr.tsoexpert.game.communication.vo.PlayerListItemVO;

public class GuildPlayerListItemVO extends PlayerListItemVO {
	private String note;
	private String officerNote;
	private int rankID;

	public String getNote() {
		return this.note;
	}

	public String getOfficerNote() {
		return this.officerNote;
	}

	public int getRankID() {
		return this.rankID;
	}
}
