package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.amf.v3.AMF3_Type;

public class ClientLogMessagesVO extends AMF3_Object {
	private List<AMF3_Type> logMessages_vector;

	public List<AMF3_Type> getLogMessages_vector() {
		return this.logMessages_vector;
	}
}
