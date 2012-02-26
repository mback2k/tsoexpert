package de.uxnr.tsoexpert.ui.table;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.uxnr.tsoexpert.game.IDataHandler;
import de.uxnr.tsoexpert.game.communication.vo.BuildingVO;
import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;

public class BuildingTableModel extends AbstractTableModel implements IDataHandler<ZoneVO> {
	private static final long serialVersionUID = -6357460357936381670L;
	
	private List<BuildingVO> buildings;

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

	@Override
	public void handleData(final ZoneVO zoneVO) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				populateBuildings(zoneVO.getBuildings());
			}
		});
	}
}
