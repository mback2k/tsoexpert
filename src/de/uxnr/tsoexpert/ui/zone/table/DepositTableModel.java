package de.uxnr.tsoexpert.ui.zone.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.uxnr.tsoexpert.game.communication.vo.DepositVO;
import de.uxnr.tsoexpert.ui.zone.ZoneHandler;

public class DepositTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -6357460357936381670L;
	
	private List<DepositVO> deposits;
	
	public DepositTableModel() {
		ZoneHandler.setDepositTableModel(this);
	}

	public void populateDeposits(List<DepositVO> deposits) {
		this.deposits = deposits;
		this.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		if (this.deposits != null) {
			return this.deposits.size();
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
		case 2:
			return "Capacity";
		}
		return null;
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.deposits.get(rowIndex).getName_string();
		case 1:
			return this.deposits.get(rowIndex).getAmount();
		case 2:
			return this.deposits.get(rowIndex).getMaxAmount();
		}
		return null;
	}
}
