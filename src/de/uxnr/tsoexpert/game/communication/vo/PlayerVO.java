package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.guild.GuildVO;

public class PlayerVO extends AMF3_Object {
	private Collection<BuffVO> availableBuffs_vector;
	private int avatarId;
	private boolean canCheat;
	private int cityLevel;
	private int currentMaximumBuildingsCountAll;
	private Collection<SectorDiscoveryVO> discoveredSectors;
	private int explorersAmount;
	private int generalsAmount;
	private int geologistsAmount;
	private GuildVO guildVO;
	private int playerLevel;
	private Collection<PurchasedShopItemVO> purchasedShopItems_vector;
	private Collection<ResourceVO> resources;
	private UniqueID uniqueID;
	private int userID;
	private String username_string;
	private int xp;
	private int zoneID;

	public Collection<BuffVO> getAvailableBuffs_vector() {
		return this.availableBuffs_vector;
	}

	public int getAvatarId() {
		return this.avatarId;
	}

	public int getCityLevel() {
		return this.cityLevel;
	}

	public int getCurrentMaximumBuildingsCountAll() {
		return this.currentMaximumBuildingsCountAll;
	}

	public Collection<SectorDiscoveryVO> getDiscoveredSectors() {
		return this.discoveredSectors;
	}

	public int getExplorersAmount() {
		return this.explorersAmount;
	}

	public int getGeneralsAmount() {
		return this.generalsAmount;
	}

	public int getGeologistsAmount() {
		return this.geologistsAmount;
	}

	public GuildVO getGuildVO() {
		return this.guildVO;
	}

	public int getPlayerLevel() {
		return this.playerLevel;
	}

	public Collection<PurchasedShopItemVO> getPurchasedShopItems_vector() {
		return this.purchasedShopItems_vector;
	}

	public Collection<ResourceVO> getResources() {
		return this.resources;
	}

	public UniqueID getUniqueID() {
		return this.uniqueID;
	}

	public int getUserID() {
		return this.userID;
	}

	public String getUsername_string() {
		return this.username_string;
	}

	public int getXp() {
		return this.xp;
	}

	public int getZoneID() {
		return this.zoneID;
	}

	public boolean isCanCheat() {
		return this.canCheat;
	}
}
