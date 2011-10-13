package de.uxnr.tsoexpert.game.communication.vo.update;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.UniqueID;

public class FindEventZoneResponseVO extends AMF3_Object {
	private String adventureName_string;
	private UniqueID buffUniqueID;
	private int eventZonePlayerID;
	private UniqueID specialistUniqueId;

	public String getAdventureName_string() {
		return this.adventureName_string;
	}

	public UniqueID getBuffUniqueID() {
		return this.buffUniqueID;
	}

	public int getEventZonePlayerID() {
		return this.eventZonePlayerID;
	}

	public UniqueID getSpecialistUniqueId() {
		return this.specialistUniqueId;
	}
}
