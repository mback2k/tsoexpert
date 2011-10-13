package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class DataTrackingVO extends AMF3_Object {
	private int amount;
	private Collection<DataIntStringVO> dataTracking;

	public int getAmount() {
		return this.amount;
	}

	public Collection<DataIntStringVO> getDataTracking() {
		return this.dataTracking;
	}
}
