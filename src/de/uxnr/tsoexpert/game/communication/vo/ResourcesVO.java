package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class ResourcesVO extends AMF3_Object {
	private int free;
	private int military;
	private Collection<ResourceVO> resources_vector;
	private int workers;

	public int getFree() {
		return this.free;
	}

	public int getMilitary() {
		return this.military;
	}

	public Collection<ResourceVO> getResources_vector() {
		return this.resources_vector;
	}

	public int getWorkers() {
		return this.workers;
	}
}
