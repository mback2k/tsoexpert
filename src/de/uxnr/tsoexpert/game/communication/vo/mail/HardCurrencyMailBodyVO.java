package de.uxnr.tsoexpert.game.communication.vo.mail;

import de.uxnr.amf.v3.AMF3_Object;

public class HardCurrencyMailBodyVO extends AMF3_Object {
	private int amount;
	private String text;

	public int getAmount() {
		return this.amount;
	}

	public String getText() {
		return this.text;
	}
}
