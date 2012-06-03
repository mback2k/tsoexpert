package de.uxnr.tsoexpert.registry;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import de.uxnr.tsoexpert.game.communication.vo.LandscapeVO;
import de.uxnr.tsoexpert.model.Landscape;
import de.uxnr.tsoexpert.model.grid.IsoGridPosition;

public class LandscapeRegistry {
	private static LandscapeRegistry instance;

	private LandscapeRegistry() {
	}

	public static LandscapeRegistry getInstance() {
		if (LandscapeRegistry.instance == null) {
			LandscapeRegistry.instance = new LandscapeRegistry();
		}
		return LandscapeRegistry.instance;
	}

	private final Map<IsoGridPosition, Landscape> landscapes = new TreeMap<IsoGridPosition, Landscape>();

	public void add(LandscapeVO landscapeVO) {
		Landscape landscape = new Landscape(landscapeVO);
		this.landscapes.put(landscape.getPosition(), landscape); // TODO: Fire event
	}

	public void addAll(Collection<LandscapeVO> landscapeVOs) {
		for (LandscapeVO landscapeVO : landscapeVOs) {
			this.add(landscapeVO); // TODO: Fire event
		}
	}

	public void remove(LandscapeVO landscapeVO) {
		Landscape landscape = new Landscape(landscapeVO);
		this.landscapes.remove(landscape); // TODO: Fire event
	}

	public void clear() {
		for (Landscape landscape : this.landscapes.values()) {
			this.landscapes.remove(landscape); // TODO: Fire event
		}
	}

	public Map<IsoGridPosition, Landscape> getAll() {
		return this.landscapes;
	}
}
