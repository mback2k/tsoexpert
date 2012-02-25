package de.uxnr.tsoexpert.ui.zone;

import java.awt.EventQueue;
import java.io.IOException;

import de.uxnr.tsoexpert.game.IDataHandler;
import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;
import de.uxnr.tsoexpert.ui.zone.table.BuildingTableModel;
import de.uxnr.tsoexpert.ui.zone.table.DepositTableModel;
import de.uxnr.tsoexpert.ui.zone.table.ResourceTableModel;

public class ZoneHandler implements IDataHandler<ZoneVO> {
	private static ZoneMapFrame zoneMapFrame;
	private static BuildingTableModel buildingTableModel;
	private static ResourceTableModel resourceTableModel;
	private static DepositTableModel depositTableModel;

	public static void setZoneMapFrame(ZoneMapFrame zoneMapFrame) {
		ZoneHandler.zoneMapFrame = zoneMapFrame;
	}
	
	public static void setBuildingTableModel(BuildingTableModel buildingTableModel) {
		ZoneHandler.buildingTableModel = buildingTableModel;
	}
	
	public static void setResourceTableModel(ResourceTableModel resourceTableModel) {
		ZoneHandler.resourceTableModel = resourceTableModel;
	}
	
	public static void setDepositTableModel(DepositTableModel depositTableModel) {
		ZoneHandler.depositTableModel = depositTableModel;
	}

	@Override
	public void handleData(final ZoneVO zoneVO) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (ZoneHandler.zoneMapFrame != null) {
					ZoneHandler.zoneMapFrame.renderZoneVO(zoneVO);
				}
				if (ZoneHandler.buildingTableModel != null) {
					ZoneHandler.buildingTableModel.populateBuildings(zoneVO.getBuildings());
				}
				if (ZoneHandler.resourceTableModel != null) {
					ZoneHandler.resourceTableModel.populateResources(zoneVO.getResourcesVO().getResources_vector());
				}
				if (ZoneHandler.depositTableModel != null) {
					ZoneHandler.depositTableModel.populateDeposits(zoneVO.getDeposits());
				}
			}
		});
	}
}
