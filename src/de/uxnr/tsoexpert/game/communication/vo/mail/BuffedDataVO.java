package de.uxnr.tsoexpert.game.communication.vo.mail;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.BuffVO;

public class BuffedDataVO extends AMF3_Object {
	private int buffedObjectGridIdx;
	private BuffVO buffVO;

	public int getBuffedObjectGridIdx() {
		return this.buffedObjectGridIdx;
	}

	public BuffVO getBuffVO() {
		return this.buffVO;
	}
}
