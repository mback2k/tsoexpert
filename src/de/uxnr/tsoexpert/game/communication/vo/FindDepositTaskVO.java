package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;

public class FindDepositTaskVO extends AMF3_Object {
	private String search_string;
	private UniqueID uniqueID;

	public String getSearch_string() {
		return this.search_string;
	}

	public UniqueID getUniqueID() {
		return this.uniqueID;
	}
}
