package de.uxnr.tsoexpert.game.communication.vo.guild;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class GuildLogListItemVO extends AMF3_Object {
	private int identifier;
	private Collection<Object> parameters;
	private double timestamp;

	public int getIdentifier() {
		return this.identifier;
	}

	public Collection<Object> getParameters() {
		return this.parameters;
	}

	public double getTimestamp() {
		return this.timestamp;
	}
}
