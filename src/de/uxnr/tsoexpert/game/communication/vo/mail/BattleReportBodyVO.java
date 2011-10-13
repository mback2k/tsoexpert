package de.uxnr.tsoexpert.game.communication.vo.mail;

import de.uxnr.amf.v3.AMF3_Object;

public class BattleReportBodyVO extends AMF3_Object {
	private String battleScript;

	public String getBattleScript() {
		return this.battleScript;
	}
}
