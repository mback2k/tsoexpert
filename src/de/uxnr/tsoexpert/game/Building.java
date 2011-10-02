package de.uxnr.tsoexpert.game;

import java.awt.Point;
import java.util.Map;

public class Building {
	private String name;
	private String uiTyp;
	private int playerLevel;
	private int hitpoints;
	private int constructionDuration;
	private int instantBuildCosts;
	private int maxBuildingLimit;
	private Map<Resource, Integer> costs;
	private Map<Integer, Buff> upgrades;
	private Map<Point, BlockingTyp> blocks;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUiTyp() {
		return this.uiTyp;
	}

	public void setUiTyp(String uiTyp) {
		this.uiTyp = uiTyp;
	}

	public int getPlayerLevel() {
		return this.playerLevel;
	}

	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}

	public int getHitpoints() {
		return this.hitpoints;
	}

	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public int getConstructionDuration() {
		return this.constructionDuration;
	}

	public void setConstructionDuration(int constructionDuration) {
		this.constructionDuration = constructionDuration;
	}

	public int getInstantBuildCosts() {
		return this.instantBuildCosts;
	}

	public void setInstantBuildCosts(int instantBuildCosts) {
		this.instantBuildCosts = instantBuildCosts;
	}

	public int getMaxBuildingLimit() {
		return this.maxBuildingLimit;
	}

	public void setMaxBuildingLimit(int maxBuildingLimit) {
		this.maxBuildingLimit = maxBuildingLimit;
	}

	public Map<Resource, Integer> getCosts() {
		return this.costs;
	}

	public void setCosts(Map<Resource, Integer> costs) {
		this.costs = costs;
	}

	public Map<Integer, Buff> getUpgrades() {
		return this.upgrades;
	}

	public void setUpgrades(Map<Integer, Buff> upgrades) {
		this.upgrades = upgrades;
	}

	public Map<Point, BlockingTyp> getBlocks() {
		return this.blocks;
	}

	public void setBlocks(Map<Point, BlockingTyp> blocks) {
		this.blocks = blocks;
	}

}
