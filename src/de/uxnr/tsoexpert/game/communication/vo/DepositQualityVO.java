package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class DepositQualityVO extends AMF3_Object {
	private int depositBonus;
	private int diceThrow;

	public int getDepositBonus() {
		return this.depositBonus;
	}

	public int getDiceThrow() {
		return this.diceThrow;
	}
}
