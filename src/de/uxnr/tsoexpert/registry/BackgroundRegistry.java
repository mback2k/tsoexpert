package de.uxnr.tsoexpert.registry;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import de.uxnr.tsoexpert.game.communication.vo.BackgroundTileVO;
import de.uxnr.tsoexpert.model.Background;
import de.uxnr.tsoexpert.model.grid.BackgroundGridPosition;

public class BackgroundRegistry {
	private static BackgroundRegistry instance;

	private BackgroundRegistry() {
	}

	public static BackgroundRegistry getInstance() {
		if (BackgroundRegistry.instance == null) {
			BackgroundRegistry.instance = new BackgroundRegistry();
		}
		return BackgroundRegistry.instance;
	}

	private final Map<BackgroundGridPosition, Background> backgrounds = new TreeMap<BackgroundGridPosition, Background>();

	public void add(BackgroundTileVO backgroundTileVO, int index) {
		Background background = new Background(backgroundTileVO, index);
		this.backgrounds.put(background.getPosition(), background); // TODO: Fire event
	}

	public void addAll(Collection<BackgroundTileVO> backgroundTileVOs) {
		int index = 0;
		for (BackgroundTileVO backgroundTileVO : backgroundTileVOs) {
			this.add(backgroundTileVO, index++); // TODO: Fire event
		}
	}

	public void remove(BackgroundTileVO backgroundTileVO, int index) {
		Background background = new Background(backgroundTileVO, index);
		this.backgrounds.remove(background); // TODO: Fire event
	}

	public void clear() {
		for (Background background : this.backgrounds.values()) {
			this.backgrounds.remove(background); // TODO: Fire event
		}
	}

	public Map<BackgroundGridPosition, Background> getAll() {
		return this.backgrounds;
	}
}
