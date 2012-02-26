package de.uxnr.tsoexpert.ui.tab;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.ui.table.DepositTableModel;

public class DepositTab extends JScrollPane {
	private static final long serialVersionUID = 3532863161027296654L;

	private DepositTableModel depositTableModel;
	private JTable depositTable;

	public DepositTab() {
		super();
		this.depositTableModel = new DepositTableModel();
		this.depositTable = new JTable();
		this.depositTable.setAutoCreateColumnsFromModel(true);
		this.depositTable.setModel(this.depositTableModel);
		this.setViewportView(this.depositTable);
	}

	public void bind(GameHandler gameHandler) {
		gameHandler.addDataHandler(1001, this.depositTableModel);
	}
}
