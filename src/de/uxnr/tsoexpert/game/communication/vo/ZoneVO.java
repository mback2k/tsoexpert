package de.uxnr.tsoexpert.game.communication.vo;

import java.util.Collection;
import java.util.Map;

import de.uxnr.amf.v3.AMF3_Object;

public class ZoneVO extends AMF3_Object {
	private QuestVO activeQuest;
	private Collection<BackgroundTileVO> backgroundTiles;
	private Collection<Object> buffProduction_vector; // TODO
	private Collection<BuildingVO> buildings;
	private BuildQueueVO buildQueue;
	private Collection<DataTrackingVO> dataTracking_vector;
	private Collection<DepositGroupVO> depositGroups;
	private Collection<DepositQualityVO> depositQualities;
	private Collection<DepositVO> deposits;
	private Collection<FreeLandscapeVO> freeLandscapes;
	private Collection<GameTickCommandVO> gameTickCommands_vector;
	private int gameTickRefreshCounter;
	private Collection<LandingFieldVO> landingFields;
	private Collection<LandscapeVO> landscapes;
	private double lastGameTickRefreshTime;
	private Map<String, ArmyVO> map_PlayerID_Army;
	private Collection<MapValueItemVO> mapValues;
	private Collection<Object> militaryUnitRecruitments_vector; // TODO
	private Collection<PlayerVO> playersOnMap;
	private QuestDefinitionContainerVO questDefinitionContainer;
	private String questFileName;
	private Collection<ResourceCreationVO> resourceCreations;
	private ResourcesVO resourcesVO;
	private Collection<SectorVO> sectors;
	private double serverTime;
	private Collection<SpecialistVO> specialists_vector;
	private Collection<StreetVO> streets;
	private String zoneMapName;
	private int zoneOwnerPlayerID;
	private int zoneVisitorPlayerID;

	public QuestVO getActiveQuest() {
		return this.activeQuest;
	}

	public Collection<BackgroundTileVO> getBackgroundTiles() {
		return this.backgroundTiles;
	}

	public Collection<Object> getBuffProduction_vector() {
		return this.buffProduction_vector;
	}

	public Collection<BuildingVO> getBuildings() {
		return this.buildings;
	}

	public BuildQueueVO getBuildQueue() {
		return this.buildQueue;
	}

	public Collection<DataTrackingVO> getDataTracking_vector() {
		return this.dataTracking_vector;
	}

	public Collection<DepositGroupVO> getDepositGroups() {
		return this.depositGroups;
	}

	public Collection<DepositQualityVO> getDepositQualities() {
		return this.depositQualities;
	}

	public Collection<DepositVO> getDeposits() {
		return this.deposits;
	}

	public Collection<FreeLandscapeVO> getFreeLandscapes() {
		return this.freeLandscapes;
	}

	public Collection<GameTickCommandVO> getGameTickCommands_vector() {
		return this.gameTickCommands_vector;
	}

	public int getGameTickRefreshCounter() {
		return this.gameTickRefreshCounter;
	}

	public Collection<LandingFieldVO> getLandingFields() {
		return this.landingFields;
	}

	public Collection<LandscapeVO> getLandscapes() {
		return this.landscapes;
	}

	public double getLastGameTickRefreshTime() {
		return this.lastGameTickRefreshTime;
	}

	public Map<String, ArmyVO> getMap_PlayerID_Army() {
		return this.map_PlayerID_Army;
	}

	public Collection<MapValueItemVO> getMapValues() {
		return this.mapValues;
	}

	public Collection<Object> getMilitaryUnitRecruitments_vector() {
		return this.militaryUnitRecruitments_vector;
	}

	public Collection<PlayerVO> getPlayersOnMap() {
		return this.playersOnMap;
	}

	public QuestDefinitionContainerVO getQuestDefinitionContainer() {
		return this.questDefinitionContainer;
	}

	public String getQuestFileName() {
		return this.questFileName;
	}

	public Collection<ResourceCreationVO> getResourceCreations() {
		return this.resourceCreations;
	}

	public ResourcesVO getResourcesVO() {
		return this.resourcesVO;
	}

	public Collection<SectorVO> getSectors() {
		return this.sectors;
	}

	public double getServerTime() {
		return this.serverTime;
	}

	public Collection<SpecialistVO> getSpecialists_vector() {
		return this.specialists_vector;
	}

	public Collection<StreetVO> getStreets() {
		return this.streets;
	}

	public String getZoneMapName() {
		return this.zoneMapName;
	}

	public int getZoneOwnerPlayerID() {
		return this.zoneOwnerPlayerID;
	}

	public int getZoneVisitorPlayerID() {
		return this.zoneVisitorPlayerID;
	}
}
