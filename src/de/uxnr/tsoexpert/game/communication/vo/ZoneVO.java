package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.amf.v3.type.Array;
import de.uxnr.amf.v3.type.Object;

public class ZoneVO extends AMF3_Object {
	private QuestVO activeQuest; // TODO dQuestVO
	private Collection<BackgroundTileVO> backgroundTiles;
	private Array buffProduction_vector;
	private Array buildings;
	private Object buildQueue; // TODO dBuildQueueVO
	private Array dataTracking_vector;
	private Array depositGroups;
	private Array depositQualities;
	private Array deposits;
	private Array freeLandscapes;
	private Array gameTickCommands_vector;
	private Integer gameTickRefreshCounter;
	private Array landingFields;
	private Array landscapes;
	private Double lastGameTickRefreshTime;
	private Object map_PlayerID_Army;
	private Array mapValues;
	private Array militaryUnitRecruitments_vector;
	private Array playersOnMap;
	private Object questDefinitionContainer; // TODO dQuestDefinitionContainerVO
	private String questFileName;
	private Array resourceCreations;
	private Object resourcesVO; // TODO dResourcesVO
	private Array sectors;
	private Double serverTime;
	private Array specialists_vector;
	private Array streets;
	private String zoneMapName;
	private Integer zoneOwnerPlayerID;
	private Integer zoneVisitorPlayerID;

	public QuestVO getActiveQuest() {
		return this.activeQuest;
	}

	public Collection<BackgroundTileVO> getBackgroundTiles() {
		return this.backgroundTiles;
	}

	public Array getBuffProduction_vector() {
		return this.buffProduction_vector;
	}

	public Array getBuildings() {
		return this.buildings;
	}

	public Object getBuildQueue() {
		return this.buildQueue;
	}

	public Array getDataTracking_vector() {
		return this.dataTracking_vector;
	}

	public Array getDepositGroups() {
		return this.depositGroups;
	}

	public Array getDepositQualities() {
		return this.depositQualities;
	}

	public Array getDeposits() {
		return this.deposits;
	}

	public Array getFreeLandscapes() {
		return this.freeLandscapes;
	}

	public Array getGameTickCommands_vector() {
		return this.gameTickCommands_vector;
	}

	public Integer getGameTickRefreshCounter() {
		return this.gameTickRefreshCounter;
	}

	public Array getLandingFields() {
		return this.landingFields;
	}

	public Array getLandscapes() {
		return this.landscapes;
	}

	public Double getLastGameTickRefreshTime() {
		return this.lastGameTickRefreshTime;
	}

	public Object getMap_PlayerID_Army() {
		return this.map_PlayerID_Army;
	}

	public Array getMapValues() {
		return this.mapValues;
	}

	public Array getMilitaryUnitRecruitments_vector() {
		return this.militaryUnitRecruitments_vector;
	}

	public Array getPlayersOnMap() {
		return this.playersOnMap;
	}

	public Object getQuestDefinitionContainer() {
		return this.questDefinitionContainer;
	}

	public String getQuestFileName() {
		return this.questFileName;
	}

	public Array getResourceCreations() {
		return this.resourceCreations;
	}

	public Object getResourcesVO() {
		return this.resourcesVO;
	}

	public Array getSectors() {
		return this.sectors;
	}

	public Double getServerTime() {
		return this.serverTime;
	}

	public Array getSpecialists_vector() {
		return this.specialists_vector;
	}

	public Array getStreets() {
		return this.streets;
	}

	public String getZoneMapName() {
		return this.zoneMapName;
	}

	public Integer getZoneOwnerPlayerID() {
		return this.zoneOwnerPlayerID;
	}

	public Integer getZoneVisitorPlayerID() {
		return this.zoneVisitorPlayerID;
	}
}
