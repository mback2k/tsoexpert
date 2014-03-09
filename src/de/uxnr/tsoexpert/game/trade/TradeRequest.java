package de.uxnr.tsoexpert.game.trade;

import de.uxnr.tsoexpert.game.player.Player;

public class TradeRequest implements Comparable<TradeRequest> {
  private final String bid;
  private final int bidsize;
  private final String demand;
  private final int demandsize;
  private final Player player;
  private final boolean active;
  private final long time;

  public TradeRequest(String input, Player player) {
    String[] splits = input.split("\\|");
    if (splits.length < 4)
      throw new IllegalArgumentException("Incomplete trade request");
    this.bid = splits[0];
    this.bidsize = Integer.parseInt(splits[1]);
    this.demand = splits[2];
    this.demandsize = Integer.parseInt(splits[3]);
    this.player = player;
    this.active = true;
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
    return this.active;
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
    if (o == null)
      return false;

    if (!(o instanceof TradeRequest))
      return false;

    TradeRequest tr = (TradeRequest) o;
    if (this.active != tr.active)
      return false;

    if (this.bidsize != tr.bidsize)
      return false;

    if (this.demandsize != tr.demandsize)
      return false;

    if (!tr.bid.equals(this.bid))
      return false;

    if (!tr.demand.equals(this.demand))
      return false;

    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.active ? "Active: " : "Inactive: ");
    sb.append(this.player.toString());
    sb.append(" bids ");
    sb.append(this.bidsize);
    sb.append(" of ");
    sb.append(this.bid);
    sb.append(" and demands ");
    sb.append(this.demandsize);
    sb.append(" of ");
    sb.append(this.demand);
    return sb.toString();
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
}
