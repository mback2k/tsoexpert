package de.uxnr.tsoexpert.ui.zone.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.uxnr.tsoexpert.game.communication.vo.ResourceVO;
import de.uxnr.tsoexpert.ui.zone.ZoneHandler;

public class ResourceTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -6357460357936381670L;
	
	private List<ResourceVO> resources;
	
	public ResourceTableModel() {
		ZoneHandler.setResourceTableModel(this);
	}

	public void populateResources(List<ResourceVO> resources) {
		this.resources = resources;
		this.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		if (this.resources != null) {
			return this.resources.size();
		}
		return 0;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Name";
		case 1:
			return "Amount";
		}
		return null;
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.resources.get(rowIndex).getName_string();
		case 1:
			return this.resources.get(rowIndex).getAmount();
		}
		return null;
	}
}
