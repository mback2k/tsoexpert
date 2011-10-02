package de.uxnr.tsoexpert.game;

import java.util.Vector;

import org.w3c.dom.Node;

public class Resource implements Parsable {

	private String name;
	private int maxLimit;
	private int id;
	private Vector<Creation> creator;
	private Vector<LimitExpansion> limitExpansions;

	public Resource(String name2) {
		this.name = name2;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxLimit() {
		return this.maxLimit;
	}

	public void setMaxLimit(int maxLimit) {
		this.maxLimit = maxLimit;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vector<Creation> getCreator() {
		return this.creator;
	}

	public void setCreator(Vector<Creation> creator) {
		this.creator = creator;
	}

	public Vector<LimitExpansion> getLimitExpansions() {
		return this.limitExpansions;
	}

	public void setLimitExpansions(Vector<LimitExpansion> limitExpansions) {
		this.limitExpansions = limitExpansions;
	}

	@Override
	public void parse(Node node) {
		// TODO Auto-generated method stub

	}

}
