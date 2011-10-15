package de.uxnr.tsoexpert.game.communication.vo;

import java.util.List;
import java.util.Map;

import de.uxnr.amf.v3.AMF3_Object;

public class ZoneVO extends AMF3_Object {
	private QuestVO activeQuest;
	private List<BackgroundTileVO> backgroundTiles;
	private List<Object> buffProduction_vector; // TODO
	private List<BuildingVO> buildings;
	private BuildQueueVO buildQueue;
	private List<DataTrackingVO> dataTracking_vector;
	private List<DepositGroupVO> depositGroups;
	private List<DepositQualityVO> depositQualities;
	private List<DepositVO> deposits;
	private List<FreeLandscapeVO> freeLandscapes;
	private List<GameTickCommandVO> gameTickCommands_vector;
	private int gameTickRefreshCounter;
	private List<LandingFieldVO> landingFields;
	private List<LandscapeVO> landscapes;
	private double lastGameTickRefreshTime;
	private Map<String, ArmyVO> map_PlayerID_Army;
	private List<MapValueItemVO> mapValues;
	private List<Object> militaryUnitRecruitments_vector; // TODO
	private List<PlayerVO> playersOnMap;
	private QuestDefinitionContainerVO questDefinitionContainer;
	private String questFileName;
	private List<ResourceCreationVO> resourceCreations;
	private ResourcesVO resourcesVO;
	private List<SectorVO> sectors;
	private double serverTime;
	private List<SpecialistVO> specialists_vector;
	private List<StreetVO> streets;
	private String zoneMapName;
	private int zoneOwnerPlayerID;
	private int zoneVisitorPlayerID;

	public QuestVO getActiveQuest() {
		return this.activeQuest;
	}

	public List<BackgroundTileVO> getBackgroundTiles() {
		return this.backgroundTiles;
	}

	public List<Object> getBuffProduction_vector() {
		return this.buffProduction_vector;
	}

	public List<BuildingVO> getBuildings() {
		return this.buildings;
	}

	public BuildQueueVO getBuildQueue() {
		return this.buildQueue;
	}

	public List<DataTrackingVO> getDataTracking_vector() {
		return this.dataTracking_vector;
	}

	public List<DepositGroupVO> getDepositGroups() {
		return this.depositGroups;
	}

	public List<DepositQualityVO> getDepositQualities() {
		return this.depositQualities;
	}

	public List<DepositVO> getDeposits() {
		return this.deposits;
	}

	public List<FreeLandscapeVO> getFreeLandscapes() {
		return this.freeLandscapes;
	}

	public List<GameTickCommandVO> getGameTickCommands_vector() {
		return this.gameTickCommands_vector;
	}

	public int getGameTickRefreshCounter() {
		return this.gameTickRefreshCounter;
	}

	public List<LandingFieldVO> getLandingFields() {
		return this.landingFields;
	}

	public List<LandscapeVO> getLandscapes() {
		return this.landscapes;
	}

	public double getLastGameTickRefreshTime() {
		return this.lastGameTickRefreshTime;
	}

	public Map<String, ArmyVO> getMap_PlayerID_Army() {
		return this.map_PlayerID_Army;
	}

	public List<MapValueItemVO> getMapValues() {
		return this.mapValues;
	}

	public List<Object> getMilitaryUnitRecruitments_vector() {
		return this.militaryUnitRecruitments_vector;
	}

	public List<PlayerVO> getPlayersOnMap() {
		return this.playersOnMap;
	}

	public QuestDefinitionContainerVO getQuestDefinitionContainer() {
		return this.questDefinitionContainer;
	}

	public String getQuestFileName() {
		return this.questFileName;
	}

	public List<ResourceCreationVO> getResourceCreations() {
		return this.resourceCreations;
	}

	public ResourcesVO getResourcesVO() {
		return this.resourcesVO;
	}

	public List<SectorVO> getSectors() {
		return this.sectors;
	}

	public double getServerTime() {
		return this.serverTime;
	}

	public List<SpecialistVO> getSpecialists_vector() {
		return this.specialists_vector;
	}

	public List<StreetVO> getStreets() {
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
