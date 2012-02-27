package de.uxnr.tsoexpert.game.trade;

import de.uxnr.tsoexpert.game.player.Player;

public class TradeRequest implements Comparable<TradeRequest> {
	private final String bid;
	private final int bidsize;
	private final String demand;
	private final int demandsize;
	private final Player player;
	private final boolean aktiv;
	private final long time;

	public TradeRequest(String input, Player player) {
		String[] splits = input.split("|");
		this.bid = splits[1];
		this.bidsize = Integer.parseInt(splits[1]);
		this.demand = splits[2];
		this.demandsize = Integer.parseInt(splits[3]);
		this.player = player;
		this.aktiv = true;
		this.time = System.currentTimeMillis();
	}

	public String getBid() {
		return this.bid;
	}

	public int getBidsize() {
		return this.bidsize;
	}

	public String getDemand() {
		return this.demand;
	}

	public int getDemandsize() {
		return this.demandsize;
	}

	public Player getPlayer() {
		return this.player;
	}

	public boolean isAktiv() {
		return this.aktiv;
	}

	public long getTime() {
		return this.time;
	}

	@Override
	public int compareTo(TradeRequest tr) {
		if (this.equals(tr))
			return 0;

		if (!this.bid.equals(tr.bid))
			return this.bid.compareTo(tr.bid);

		if (!this.demand.equals(tr.demand))
			return this.demand.compareTo(tr.demand);

		if (this.bidsize != tr.bidsize)
			return this.bidsize - tr.bidsize;

		if (this.bidsize != tr.bidsize)
			return this.bidsize - tr.bidsize;

		if (this.player.equals(tr.player))
			return this.player.compareTo(tr.player);

		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof TradeRequest) {
			TradeRequest tr = (TradeRequest) o;
			if (this.aktiv == tr.aktiv && tr.bid.equals(this.bid) && tr.demand.equals(this.demand) && this.bidsize == tr.bidsize && this.demandsize == tr.demandsize)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return new StringBuilder(this.aktiv ? "Aktiv" : "Inaktiv").append(this.player.toString()).append(" bids ").append(this.bidsize).append(" pct. of ").append(this.bid).append(" and demands ").append(this.demandsize).append(" pct. of ").append(this.demand).toString();
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

}
