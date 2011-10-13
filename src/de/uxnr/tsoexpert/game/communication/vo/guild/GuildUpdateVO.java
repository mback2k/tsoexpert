package de.uxnr.tsoexpert.game.communication.vo.guild;

import de.uxnr.amf.v3.AMF3_Object;

public class GuildUpdateVO extends AMF3_Object {
	private GuildVO guild;

	public GuildVO getGuild() {
		return this.guild;
	}
}
