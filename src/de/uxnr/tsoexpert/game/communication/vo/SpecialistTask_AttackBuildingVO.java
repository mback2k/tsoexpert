package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.tsoexpert.game.communication.vo.update.BattleResultVO;

public class SpecialistTask_AttackBuildingVO extends SpecialistTaskVO {
	private int armyDestinationBuildingGridPos;
	private int attackBuildingMode;
	private String battleReport_string;
	private BattleResultVO battleResultVO;
	private String battleScript_string;
	private double lastRound;
	private int pathPos;
	private int startingArmySize;
	private int targetBuildingGridPos;

	public int getArmyDestinationBuildingGridPos() {
		return this.armyDestinationBuildingGridPos;
	}

	public int getAttackBuildingMode() {
		return this.attackBuildingMode;
	}

	public String getBattleReport_string() {
		return this.battleReport_string;
	}

	public BattleResultVO getBattleResultVO() {
		return this.battleResultVO;
	}

	public String getBattleScript_string() {
		return this.battleScript_string;
	}

	public double getLastRound() {
		return this.lastRound;
	}

	public int getPathPos() {
		return this.pathPos;
	}

	public int getStartingArmySize() {
		return this.startingArmySize;
	}

	public int getTargetBuildingGridPos() {
		return this.targetBuildingGridPos;
	}
}
