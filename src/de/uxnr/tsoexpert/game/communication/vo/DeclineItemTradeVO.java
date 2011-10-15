package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class DeclineItemTradeVO extends AMF3_Object {
	private BuffVO buff;

	public BuffVO getBuff() {
		return this.buff;
	}
}
