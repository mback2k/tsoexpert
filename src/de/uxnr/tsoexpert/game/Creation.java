package de.uxnr.tsoexpert.game;

import java.util.HashMap;
import java.util.Map;

public class Creation {

	private static Map<Integer, Creation> CreationMap = new HashMap<Integer, Creation>();

	private CreationTyp typ;
	private final int id;
	private float time;
	private Resource externalResource;

	private Creation(int id) {
		this.id = id;
	}

	public static Creation getById(int id) {
		Creation c = CreationMap.get(id);
		if (c == null) {
			c = new Creation(id);
		}
		return c;
	}

}
