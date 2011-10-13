package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.amf.v3.AMF3_Type;

public class ClientLogMessagesVO extends AMF3_Object {
	private Collection<AMF3_Type> logMessages_vector;

	public Collection<AMF3_Type> getLogMessages_vector() {
		return this.logMessages_vector;
	}
}
