package de.uxnr.tsoexpert.ui.tab;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.ui.map.ZoneMap;
import de.uxnr.tsoexpert.ui.map.ZoneMapFrame;

public class ZoneMapTab extends JSplitPane implements PropertyChangeListener {
  private static final long serialVersionUID = -3193467783589113309L;

  private final ButtonGroup backgroundButtonGroup = new ButtonGroup();
  private final ButtonGroup freeLandscapeButtonGroup = new ButtonGroup();
  private final ButtonGroup landscapeButtonGroup = new ButtonGroup();
  private final ButtonGroup buildingButtonGroup = new ButtonGroup();
  private final ButtonGroup resourceCreationButtonGroup = new ButtonGroup();
  private final ButtonGroup mapValuesButtonGroup = new ButtonGroup();

  private ZoneMapFrame mapFrame;
  private JSplitPane splitPane;
  private JTextPane mapText;
  private JPanel zoneMapPanel;

  private JLabel lblBackground;
  private JRadioButton btnBackgroundShow;
  private JRadioButton btnBackgroundDebug;
  private JLabel lblFreeLandscape;
  private JRadioButton btnFreeLandscapeHide;
  private JRadioButton btnFreeLandscapeShow;
  private JRadioButton btnFreeLandscapeDebug;
  private JLabel lblLandscape;
  private JRadioButton btnLandscapeHide;
  private JRadioButton btnLandscapeShow;
  private JRadioButton btnLandscapeDebug;
  private JLabel lblBuilding;
  private JRadioButton btnBuildingHide;
  private JRadioButton btnBuildingShow;
  private JRadioButton btnBuildingDebug;
  private JLabel lblResourceCreation;
  private JRadioButton btnResourceCreationHide;
  private JRadioButton btnResourceCreationDebug;
  private JLabel lblMapValues;
  private JRadioButton btnMapValuesHide;
  private JRadioButton btnMapValuesDebug;

  public ZoneMapTab() {
    super(JSplitPane.HORIZONTAL_SPLIT);

    this.mapFrame = new ZoneMapFrame();
    this.setRightComponent(this.mapFrame);

    this.splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    this.setLeftComponent(this.splitPane);

    this.zoneMapPanel = new JPanel();
    GridBagLayout gbl_zoneMapPanel = new GridBagLayout();
    gbl_zoneMapPanel.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    gbl_zoneMapPanel.columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};
    this.zoneMapPanel.setLayout(gbl_zoneMapPanel);
    this.splitPane.setLeftComponent(this.zoneMapPanel);

    this.mapText = new JTextPane();
    this.splitPane.setRightComponent(this.mapText);

    this.lblBackground = new JLabel("Background");
    GridBagConstraints gbc_lblBackground = new GridBagConstraints();
    gbc_lblBackground.insets = new Insets(0, 0, 5, 5);
    gbc_lblBackground.gridx = 0;
    gbc_lblBackground.gridy = 0;
    this.zoneMapPanel.add(this.lblBackground, gbc_lblBackground);

    this.btnBackgroundShow = new JRadioButton("Show");
    this.btnBackgroundShow.setSelected(true);
    this.btnBackgroundShow.addPropertyChangeListener(this);
    backgroundButtonGroup.add(this.btnBackgroundShow);
    GridBagConstraints gbc_btnBackgroundShow = new GridBagConstraints();
    gbc_btnBackgroundShow.insets = new Insets(0, 0, 5, 5);
    gbc_btnBackgroundShow.gridx = 2;
    gbc_btnBackgroundShow.gridy = 0;
    this.zoneMapPanel.add(this.btnBackgroundShow, gbc_btnBackgroundShow);

    this.btnBackgroundDebug = new JRadioButton("Debug");
    this.btnBackgroundDebug.addPropertyChangeListener(this);
    backgroundButtonGroup.add(this.btnBackgroundDebug);
    GridBagConstraints gbc_btnBackgroundDebug = new GridBagConstraints();
    gbc_btnBackgroundDebug.insets = new Insets(0, 0, 5, 0);
    gbc_btnBackgroundDebug.gridx = 3;
    gbc_btnBackgroundDebug.gridy = 0;
    this.zoneMapPanel.add(this.btnBackgroundDebug, gbc_btnBackgroundDebug);

    this.lblFreeLandscape = new JLabel("Free Landscape");
    GridBagConstraints gbc_lblFreeLandscape = new GridBagConstraints();
    gbc_lblFreeLandscape.insets = new Insets(0, 0, 5, 5);
    gbc_lblFreeLandscape.gridx = 0;
    gbc_lblFreeLandscape.gridy = 1;
    this.zoneMapPanel.add(this.lblFreeLandscape, gbc_lblFreeLandscape);

    this.btnFreeLandscapeHide = new JRadioButton("Hide");
    this.btnFreeLandscapeHide.addPropertyChangeListener(this);
    freeLandscapeButtonGroup.add(this.btnFreeLandscapeHide);
    GridBagConstraints gbc_btnFreeLandscapeHide = new GridBagConstraints();
    gbc_btnFreeLandscapeHide.insets = new Insets(0, 0, 5, 5);
    gbc_btnFreeLandscapeHide.gridx = 1;
    gbc_btnFreeLandscapeHide.gridy = 1;
    this.zoneMapPanel.add(this.btnFreeLandscapeHide, gbc_btnFreeLandscapeHide);

    this.btnFreeLandscapeShow = new JRadioButton("Show");
    this.btnFreeLandscapeShow.addPropertyChangeListener(this);
    freeLandscapeButtonGroup.add(this.btnFreeLandscapeShow);
    GridBagConstraints gbc_btnFreeLandscapeShow = new GridBagConstraints();
    gbc_btnFreeLandscapeShow.insets = new Insets(0, 0, 5, 5);
    gbc_btnFreeLandscapeShow.gridx = 2;
    gbc_btnFreeLandscapeShow.gridy = 1;
    this.zoneMapPanel.add(this.btnFreeLandscapeShow, gbc_btnFreeLandscapeShow);

    this.btnFreeLandscapeDebug = new JRadioButton("Debug");
    this.btnFreeLandscapeDebug.setSelected(true);
    this.btnFreeLandscapeDebug.addPropertyChangeListener(this);
    freeLandscapeButtonGroup.add(this.btnFreeLandscapeDebug);
    GridBagConstraints gbc_btnFreeLandscapeDebug = new GridBagConstraints();
    gbc_btnFreeLandscapeDebug.insets = new Insets(0, 0, 5, 0);
    gbc_btnFreeLandscapeDebug.gridx = 3;
    gbc_btnFreeLandscapeDebug.gridy = 1;
    this.zoneMapPanel.add(this.btnFreeLandscapeDebug, gbc_btnFreeLandscapeDebug);

    this.lblLandscape = new JLabel("Landscape");
    GridBagConstraints gbc_lblLandscape = new GridBagConstraints();
    gbc_lblLandscape.insets = new Insets(0, 0, 5, 5);
    gbc_lblLandscape.gridx = 0;
    gbc_lblLandscape.gridy = 2;
    this.zoneMapPanel.add(this.lblLandscape, gbc_lblLandscape);

    this.btnLandscapeHide = new JRadioButton("Hide");
    this.btnLandscapeHide.addPropertyChangeListener(this);
    landscapeButtonGroup.add(this.btnLandscapeHide);
    GridBagConstraints gbc_btnLandscapeHide = new GridBagConstraints();
    gbc_btnLandscapeHide.insets = new Insets(0, 0, 5, 5);
    gbc_btnLandscapeHide.gridx = 1;
    gbc_btnLandscapeHide.gridy = 2;
    this.zoneMapPanel.add(this.btnLandscapeHide, gbc_btnLandscapeHide);

    this.btnLandscapeShow = new JRadioButton("Show");
    this.btnLandscapeShow.addPropertyChangeListener(this);
    landscapeButtonGroup.add(this.btnLandscapeShow);
    GridBagConstraints gbc_btnLandscapeShow = new GridBagConstraints();
    gbc_btnLandscapeShow.insets = new Insets(0, 0, 5, 5);
    gbc_btnLandscapeShow.gridx = 2;
    gbc_btnLandscapeShow.gridy = 2;
    this.zoneMapPanel.add(this.btnLandscapeShow, gbc_btnLandscapeShow);

    this.btnLandscapeDebug = new JRadioButton("Debug");
    this.btnLandscapeDebug.setSelected(true);
    this.btnLandscapeDebug.addPropertyChangeListener(this);
    landscapeButtonGroup.add(this.btnLandscapeDebug);
    GridBagConstraints gbc_btnLandscapeDebug = new GridBagConstraints();
    gbc_btnLandscapeDebug.insets = new Insets(0, 0, 5, 0);
    gbc_btnLandscapeDebug.gridx = 3;
    gbc_btnLandscapeDebug.gridy = 2;
    this.zoneMapPanel.add(this.btnLandscapeDebug, gbc_btnLandscapeDebug);

    this.lblBuilding = new JLabel("Building");
    GridBagConstraints gbc_lblBuilding = new GridBagConstraints();
    gbc_lblBuilding.insets = new Insets(0, 0, 5, 5);
    gbc_lblBuilding.gridx = 0;
    gbc_lblBuilding.gridy = 3;
    this.zoneMapPanel.add(this.lblBuilding, gbc_lblBuilding);

    this.btnBuildingHide = new JRadioButton("Hide");
    this.btnBuildingHide.addPropertyChangeListener(this);
    buildingButtonGroup.add(this.btnBuildingHide);
    GridBagConstraints gbc_btnBuildingHide = new GridBagConstraints();
    gbc_btnBuildingHide.insets = new Insets(0, 0, 5, 5);
    gbc_btnBuildingHide.gridx = 1;
    gbc_btnBuildingHide.gridy = 3;
    this.zoneMapPanel.add(this.btnBuildingHide, gbc_btnBuildingHide);

    this.btnBuildingShow = new JRadioButton("Show");
    this.btnBuildingShow.addPropertyChangeListener(this);
    buildingButtonGroup.add(this.btnBuildingShow);
    GridBagConstraints gbc_btnBuildingShow = new GridBagConstraints();
    gbc_btnBuildingShow.insets = new Insets(0, 0, 5, 5);
    gbc_btnBuildingShow.gridx = 2;
    gbc_btnBuildingShow.gridy = 3;
    this.zoneMapPanel.add(this.btnBuildingShow, gbc_btnBuildingShow);

    this.btnBuildingDebug = new JRadioButton("Debug");
    this.btnBuildingDebug.setSelected(true);
    this.btnBuildingDebug.addPropertyChangeListener(this);
    buildingButtonGroup.add(this.btnBuildingDebug);
    GridBagConstraints gbc_btnBuildingDebug = new GridBagConstraints();
    gbc_btnBuildingDebug.insets = new Insets(0, 0, 5, 0);
    gbc_btnBuildingDebug.gridx = 3;
    gbc_btnBuildingDebug.gridy = 3;
    this.zoneMapPanel.add(this.btnBuildingDebug, gbc_btnBuildingDebug);

    this.lblResourceCreation = new JLabel("Resource Creation");
    GridBagConstraints gbc_lblResourceCreation = new GridBagConstraints();
    gbc_lblResourceCreation.insets = new Insets(0, 0, 5, 5);
    gbc_lblResourceCreation.gridx = 0;
    gbc_lblResourceCreation.gridy = 4;
    this.zoneMapPanel.add(this.lblResourceCreation, gbc_lblResourceCreation);

    this.btnResourceCreationHide = new JRadioButton("Hide");
    this.btnResourceCreationHide.setSelected(true);
    this.btnResourceCreationHide.addPropertyChangeListener(this);
    resourceCreationButtonGroup.add(this.btnResourceCreationHide);
    GridBagConstraints gbc_btnResourceCreationHide = new GridBagConstraints();
    gbc_btnResourceCreationHide.insets = new Insets(0, 0, 5, 5);
    gbc_btnResourceCreationHide.gridx = 2;
    gbc_btnResourceCreationHide.gridy = 4;
    this.zoneMapPanel.add(this.btnResourceCreationHide, gbc_btnResourceCreationHide);

    this.btnResourceCreationDebug = new JRadioButton("Debug");
    this.btnResourceCreationDebug.addPropertyChangeListener(this);
    resourceCreationButtonGroup.add(this.btnResourceCreationDebug);
    GridBagConstraints gbc_btnResourceCreationDebug = new GridBagConstraints();
    gbc_btnResourceCreationDebug.insets = new Insets(0, 0, 5, 0);
    gbc_btnResourceCreationDebug.gridx = 3;
    gbc_btnResourceCreationDebug.gridy = 4;
    this.zoneMapPanel.add(this.btnResourceCreationDebug, gbc_btnResourceCreationDebug);

    this.lblMapValues = new JLabel("Map Values");
    GridBagConstraints gbc_lblMapValues = new GridBagConstraints();
    gbc_lblMapValues.insets = new Insets(0, 0, 5, 5);
    gbc_lblMapValues.gridx = 0;
    gbc_lblMapValues.gridy = 5;
    this.zoneMapPanel.add(this.lblMapValues, gbc_lblMapValues);

    this.btnMapValuesHide = new JRadioButton("Hide");
    this.btnMapValuesHide.setSelected(true);
    this.btnMapValuesHide.addPropertyChangeListener(this);
    mapValuesButtonGroup.add(this.btnMapValuesHide);
    GridBagConstraints gbc_btnMapValuesHide = new GridBagConstraints();
    gbc_btnMapValuesHide.insets = new Insets(0, 0, 5, 5);
    gbc_btnMapValuesHide.gridx = 2;
    gbc_btnMapValuesHide.gridy = 5;
    this.zoneMapPanel.add(this.btnMapValuesHide, gbc_btnMapValuesHide);

    this.btnMapValuesDebug = new JRadioButton("Debug");
    this.btnMapValuesDebug.addPropertyChangeListener(this);
    mapValuesButtonGroup.add(this.btnMapValuesDebug);
    GridBagConstraints gbc_btnMapValuesDebug = new GridBagConstraints();
    gbc_btnMapValuesDebug.insets = new Insets(0, 0, 5, 0);
    gbc_btnMapValuesDebug.gridx = 3;
    gbc_btnMapValuesDebug.gridy = 5;
    this.zoneMapPanel.add(this.btnMapValuesDebug, gbc_btnMapValuesDebug);
  }

  public void bind(GameHandler gameHandler) {
    gameHandler.addDataHandler(1001, this.mapFrame);
  }

  public void propertyChange(PropertyChangeEvent event) {
    ZoneMap zoneMap = this.mapFrame.getZoneMap();
    if (zoneMap != null) {
      zoneMap.setShowBackground(this.btnBackgroundShow.isSelected());
      zoneMap.setShowFreeLandscape(this.btnFreeLandscapeShow.isSelected());
      zoneMap.setShowLandscape(this.btnLandscapeShow.isSelected());
      zoneMap.setShowBuilding(this.btnBuildingShow.isSelected());
      zoneMap.setDebugBackground(this.btnBackgroundDebug.isSelected());
      zoneMap.setDebugFreeLandscape(this.btnFreeLandscapeDebug.isSelected());
      zoneMap.setDebugLandscape(this.btnLandscapeDebug.isSelected());
      zoneMap.setDebugBuilding(this.btnBuildingDebug.isSelected());
      zoneMap.setDebugResourceCreations(this.btnResourceCreationDebug.isSelected());
      zoneMap.setDebugMapValues(this.btnMapValuesDebug.isSelected());
      this.mapFrame.repaint();
    }
  }
}
