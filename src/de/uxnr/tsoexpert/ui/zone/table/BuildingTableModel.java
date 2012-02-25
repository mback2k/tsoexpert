package de.uxnr.tsoexpert.ui.zone.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.uxnr.tsoexpert.game.communication.vo.BuildingVO;
import de.uxnr.tsoexpert.ui.zone.ZoneHandler;

public class BuildingTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -6357460357936381670L;
	
	private List<BuildingVO> buildings;
	
	public BuildingTableModel() {
		ZoneHandler.setBuildingTableModel(this);
	}

	public void populateBuildings(List<BuildingVO> buildings) {
		this.buildings = buildings;
		this.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		if (this.buildings != null) {
			return this.buildings.size();
		}
		return 0;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Name";
		case 1:
			return "Level";
		}
		return null;
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.buildings.get(rowIndex).getBuildingName_string();
		case 1:
			return this.buildings.get(rowIndex).getUpgradeLevel();
		}
		return null;
	}
}
