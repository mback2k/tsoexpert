package de.uxnr.tsoexpert.game.trade;

import de.uxnr.tsoexpert.game.player.Player;

public class TradeRequest implements Comparable<TradeRequest> {
	private String bid;
	private int bidsize;
	private String demand;
	private int demandsize;
	private Player player;
	private boolean aktiv;

	public TradeRequest(String input, Player player) {
		String[] splits = input.split("|");
		this.bidsize = Integer.parseInt(splits[1]);
		this.demand = splits[2];
		this.demandsize = Integer.parseInt(splits[3]);
		this.player = player;
		this.aktiv = true;
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

	public boolean isaktiv() {
		return this.aktiv;
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

	public boolean equals(Object o) {
		if (o != null && o instanceof TradeRequest) {
			TradeRequest tr = (TradeRequest) o;
			if (this.aktiv == tr.aktiv && tr.bid.equals(this.bid) && tr.demand.equals(this.demand) && this.bidsize == tr.bidsize && this.demandsize == tr.demandsize)
				return true;
		}
		return false;
	}

	public String toString() {
		return new StringBuilder(aktiv ? "Aktiv" : "Inaktiv").append(player.toString()).append(" bids ").append(bidsize).append(" pct. of ").append(bid).append(" and demands ").append(demandsize).append(" pct. of ").append(demand).toString();
	}

	public int hashCode() {
		return this.toString().hashCode();
	}

}
