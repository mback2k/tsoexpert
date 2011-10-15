package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class PathVO extends AMF3_Object {
	private Collection<Integer> mPath;

	public Collection<Integer> getPath() {
		return this.mPath;
	}
}
