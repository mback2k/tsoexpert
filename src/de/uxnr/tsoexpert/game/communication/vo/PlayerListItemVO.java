package de.uxnr.tsoexpert.game.communication.vo;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.update.AdventureClientInfoVO;

public class PlayerListItemVO extends AMF3_Object {
	private AdventureClientInfoVO adventureVO;
	private int avatarId;
	private int id;
	private boolean onlineStatus;
	private int playerLevel;
	private String username;

	public AdventureClientInfoVO getAdventureVO() {
		return this.adventureVO;
	}

	public int getAvatarId() {
		return this.avatarId;
	}

	public int getId() {
		return this.id;
	}

	public boolean getOnlineStatus() {
		return this.onlineStatus;
	}

	public int getPlayerLevel() {
		return this.playerLevel;
	}

	public String getUsername() {
		return this.username;
	}
}
