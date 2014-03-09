package de.uxnr.tsoexpert.ui.tab;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.ui.table.ResourceTableModel;

public class ResourceTab extends JScrollPane {
  private static final long serialVersionUID = 3532863161027296654L;

  private ResourceTableModel resourceTableModel;
  private JTable resourceTable;

  public ResourceTab() {
    super();
    this.resourceTableModel = new ResourceTableModel();
    this.resourceTable = new JTable();
    this.resourceTable.setAutoCreateColumnsFromModel(true);
    this.resourceTable.setModel(this.resourceTableModel);
    this.setViewportView(this.resourceTable);
  }

  public void bind(GameHandler gameHandler) {
    gameHandler.addDataHandler(1001, this.resourceTableModel);
  }
}
