package de.uxnr.tsoexpert.game.communication.vo.guild;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class GuildEditValueVO extends AMF3_Object {
	private String newValue;
	private Collection<Object> parameters; // TODO
	private int type;

	public String getNewValue() {
		return this.newValue;
	}

	public Collection<Object> getParameters() {
		return this.parameters;
	}

	public int getType() {
		return this.type;
	}
}
