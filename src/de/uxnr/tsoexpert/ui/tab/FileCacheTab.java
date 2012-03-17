package de.uxnr.tsoexpert.ui.tab;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.uxnr.tsoexpert.proxy.StaticHandler;
import de.uxnr.tsoexpert.ui.table.FileCacheTableModel;

public class FileCacheTab extends JScrollPane {
	private static final long serialVersionUID = -1069072898499627314L;

	private FileCacheTableModel fileCacheTableModel;
	private JTable fileCacheTable;

	public FileCacheTab() {
		super();
		this.fileCacheTableModel = new FileCacheTableModel();
		this.fileCacheTable = new JTable();
		this.fileCacheTable.setAutoCreateColumnsFromModel(true);
		this.fileCacheTable.setModel(this.fileCacheTableModel);
		this.setViewportView(this.fileCacheTable);
	}

	public void bind(StaticHandler staticHandler) {
		this.fileCacheTableModel.bindStaticHandler(staticHandler);
	}
}
