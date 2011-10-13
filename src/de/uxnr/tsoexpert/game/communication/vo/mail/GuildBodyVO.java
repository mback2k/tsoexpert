package de.uxnr.tsoexpert.game.communication.vo.mail;

import de.uxnr.amf.v3.AMF3_Object;

public class GuildBodyVO extends AMF3_Object {
	private int bannerId;
	private String guildName;

	public int getBannerId() {
		return this.bannerId;
	}

	public String getGuildName() {
		return this.guildName;
	}
}
