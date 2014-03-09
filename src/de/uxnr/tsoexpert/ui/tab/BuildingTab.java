package de.uxnr.tsoexpert.ui.tab;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.ui.table.BuildingTableModel;

public class BuildingTab extends JScrollPane {
  private static final long serialVersionUID = 3532863161027296654L;

  private BuildingTableModel buildingTableModel;
  private JTable buildingTable;

  public BuildingTab() {
    super();
    this.buildingTableModel = new BuildingTableModel();
    this.buildingTable = new JTable();
    this.buildingTable.setAutoCreateColumnsFromModel(true);
    this.buildingTable.setModel(this.buildingTableModel);
    this.setViewportView(this.buildingTable);
  }

  public void bind(GameHandler gameHandler) {
    gameHandler.addDataHandler(1001, this.buildingTableModel);
  }
}
