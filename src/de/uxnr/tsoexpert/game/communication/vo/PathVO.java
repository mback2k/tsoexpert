package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;

import de.uxnr.amf.v3.AMF3_Object;

public class PathVO extends AMF3_Object {
	private List<Integer> mPath;

	public List<Integer> getPath() {
		return this.mPath;
	}
}
