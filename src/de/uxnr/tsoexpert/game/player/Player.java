package de.uxnr.tsoexpert.game.player;

public class Player implements Comparable<Player> {
	private String nickname;

	public Player(String nick) {
		this.nickname = nick;
	}

	public String getNickname() {
		return this.nickname;
	}

	@Override
	public int compareTo(Player p) {
		if (this.equals(p))
			return 0;
		return this.nickname.compareTo(p.nickname);
	}

	public boolean equals(Object o) {
		if (o != null && o instanceof Player) {
			return this.nickname.equals(((Player) o).nickname);
		}
		return false;
	}

	public String toString() {
		return nickname;
	}

	public int hashCode() {
		return nickname.hashCode();
	}

}
