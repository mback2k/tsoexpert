package de.uxnr.tsoexpert.ui.table;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import de.uxnr.tsoexpert.file.FileData;
import de.uxnr.tsoexpert.proxy.StaticHandler;
import de.uxnr.tsoexpert.resource.IResourceHandler;

public class FileCacheTableModel extends AbstractTableModel implements IResourceHandler {
	private static final long serialVersionUID = 380685821634495899L;

	private final List<String> fileNames = new Vector<String>();

	private StaticHandler staticHandler;

	public void bindStaticHandler(StaticHandler staticHandler) {
		this.staticHandler = staticHandler;
		this.staticHandler.addResourceHandler(".*", this);
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		if (this.staticHandler == null)
			return 0;

		return this.staticHandler.getTotalFileCount();
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Path";
		case 1:
			return "Size";
		}
		return null;
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (this.staticHandler == null)
			return null;

		String path = this.fileNames.get(rowIndex);
		FileData file = this.staticHandler.getFile(path);

		switch (columnIndex) {
		case 0:
			return file.getPath();
		case 1:
			return file.length();
		}
		return null;
	}

	@Override
	public void handleResource(FileData fd) throws IOException {
		if (this.staticHandler == null)
			return;

		this.fileNames.add(fd.getPath());

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				fireTableDataChanged();
			}
		});
	}
}
