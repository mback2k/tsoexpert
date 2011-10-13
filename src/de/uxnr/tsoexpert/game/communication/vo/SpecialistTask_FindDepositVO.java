package de.uxnr.tsoexpert.game.communication.vo;

public class SpecialistTask_FindDepositVO extends SpecialistTaskVO {
	private String depositToSearch_string;
	private int exploredDepositResult;
	private DepositVO exploredDepositVO;

	public String getDepositToSearch_string() {
		return this.depositToSearch_string;
	}

	public int getExploredDepositResult() {
		return this.exploredDepositResult;
	}

	public DepositVO getExploredDepositVO() {
		return this.exploredDepositVO;
	}
}
