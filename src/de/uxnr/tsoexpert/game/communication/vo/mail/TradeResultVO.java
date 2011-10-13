package de.uxnr.tsoexpert.game.communication.vo.mail;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.ResourceVO;

public class TradeResultVO extends AMF3_Object {
	private ResourceVO result;

	public ResourceVO getResult() {
		return this.result;
	}
}
