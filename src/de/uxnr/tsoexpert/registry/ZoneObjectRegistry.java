package de.uxnr.tsoexpert.registry;

import java.util.Map;
import java.util.TreeMap;

import de.uxnr.tsoexpert.model.grid.GridPosition;

public class ZoneObjectRegistry {
	private static ZoneObjectRegistry instance;

	private ZoneObjectRegistry() {
	}

	public static ZoneObjectRegistry getInstance() {
		if (ZoneObjectRegistry.instance == null) {
			ZoneObjectRegistry.instance = new ZoneObjectRegistry();
		}
		return ZoneObjectRegistry.instance;
	}

	private final Map<GridPosition, Object> objects = new TreeMap<GridPosition, Object>();

	public void add(GridPosition position, Object object) {
		this.objects.put(position, object); // TODO: Fire event
	}

	public void remove(GridPosition position) {
		this.objects.remove(position); // TODO: Fire event
	}

	public Map<GridPosition, Object> getAll() {
		return this.objects;
	}
}
