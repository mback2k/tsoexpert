package de.uxnr.tsoexpert.game;

public class Buff {
	private int level;
	private boolean tradeable;
	private Bufftype type;
	private int productionTime;
	private int hitPointsAmount;
	private int recruitingTimePercent;

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isTradeable() {
		return this.tradeable;
	}

	public void setTradeable(boolean tradeable) {
		this.tradeable = tradeable;
	}

	public Bufftype getType() {
		return this.type;
	}

	public void setType(Bufftype type) {
		this.type = type;
	}

	public int getProductionTime() {
		return this.productionTime;
	}

	public void setProductionTime(int productionTime) {
		this.productionTime = productionTime;
	}

	public int getHitPointsAmount() {
		return this.hitPointsAmount;
	}

	public void setHitPointsAmount(int hitPointsAmount) {
		this.hitPointsAmount = hitPointsAmount;
	}

	public int getRecruitingTimePercent() {
		return this.recruitingTimePercent;
	}

	public void setRecruitingTimePercent(int recruitingTimePercent) {
		this.recruitingTimePercent = recruitingTimePercent;
	}

}
