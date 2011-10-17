package de.uxnr.tsoexpert.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BoxLayout;
import de.uxnr.tsoexpert.TSOExpert;
import de.uxnr.tsoexpert.game.PlayerListHandler;
import de.uxnr.tsoexpert.game.ZoneHandler;
import de.uxnr.tsoexpert.game.communication.vo.BuildingVO;
import de.uxnr.tsoexpert.game.communication.vo.DepositVO;
import de.uxnr.tsoexpert.game.communication.vo.ResourceVO;
import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;
import de.uxnr.tsoexpert.map.ZoneMap;
import de.uxnr.tsoexpert.proxy.GameHandler;

public class MainWindow extends ApplicationWindow implements PaintListener, MouseWheelListener, KeyListener, MouseListener, MouseMoveListener, SelectionListener {
	private final WritableList m_buildings = new WritableList(new Vector<BuildingVO>(), BuildingVO.class);
	private final WritableList m_resources = new WritableList(new Vector<ResourceVO>(), ResourceVO.class);
	private final WritableList m_deposits = new WritableList(new Vector<DepositVO>(), DepositVO.class);

	private boolean t_drag;
	private int t_lastX;
	private int t_lastY;

	private ZoneMap m_zonemap;

	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;

	private TabFolder tabFolder;
	private TabItem tbtmMap;
	private TabItem tbtmBuildings;
	private TabItem tbtmResources;
	private Canvas canvasMap;
	private Table tableBuildings;
	private TableViewer tableViewerBuildings;
	private TableColumn tblclmnBuildingName;
	private TableColumn tblclmnBuildingLevel;
	private Table tableResources;
	private TableViewer tableViewerResources;
	private TableColumn tblclmnResourceName;
	private TableColumn tblclmnResourceAmount;
	private Composite compositeBuildings;
	private Composite compositeResources;
	private TabItem tbtmDeposits;
	private Composite compositeDeposits;
	private Table tableDeposits;
	private TableViewer tableViewerDeposits;
	private TableColumn tblclmnDepositName;
	private TableColumn tblclmnDepositAmount;
	private TableColumn tblclmnDepositCapacity;
	private ExpandBar expandBar;
	private ExpandItem xpndtmMap;
	private Composite compositeMapOptions;
	private Composite compositeBackground;
	private Composite compositeFreeLandscape;
	private Label lblBackground;
	private Button btnBackgroundShow;
	private Button btnBackgroundDebug;
	private Label lblFreeLandscape;
	private Button btnFreeLandscapeHide;
	private Button btnFreeLandscapeShow;
	private Button btnFreeLandscapeDebug;
	private Composite compositeLandscape;
	private Label lblLandscape;
	private Button btnLandscapeHide;
	private Button btnLandscapeShow;
	private Button btnLandscapeDebug;
	private Composite compositeBuilding;
	private Label lblBuilding;
	private Button btnBuildingHide;
	private Button btnBuildingShow;
	private Button btnBuildingDebug;
	private Composite compositeMap;
	private Composite compositeResourceCreation;
	private Composite compositeMapValue;
	private Label lblResourceCreation;
	private Label lblMapValues;
	private Button btnResourceCreationHide;
	private Button btnResourceCreationDebug;
	private Button btnMapValueHide;
	private Button btnMapValueDebug;

	/**
	 * Create the application window.
	 */
	public MainWindow() {
		super(null);
		this.createActions();
		this.addToolBar(SWT.FLAT | SWT.WRAP);
		this.addMenuBar();
		this.addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		this.tabFolder = new TabFolder(container, SWT.NONE);

		this.tbtmMap = new TabItem(this.tabFolder, SWT.NONE);
		this.tbtmMap.setText("Map");

		this.compositeMap = new Composite(this.tabFolder, SWT.NONE);
		this.tbtmMap.setControl(this.compositeMap);
		this.compositeMap.setLayout(new GridLayout(2, false));

		this.expandBar = new ExpandBar(this.compositeMap, SWT.NONE);
		GridData gd_expandBar = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
		gd_expandBar.widthHint = 287;
		this.expandBar.setLayoutData(gd_expandBar);
		this.expandBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		this.xpndtmMap = new ExpandItem(this.expandBar, SWT.NONE);
		this.xpndtmMap.setExpanded(true);
		this.xpndtmMap.setText("Map");

		this.compositeMapOptions = new Composite(this.expandBar, SWT.NONE);
		this.xpndtmMap.setControl(this.compositeMapOptions);
		this.compositeMapOptions.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

		this.compositeBackground = new Composite(this.compositeMapOptions, SWT.NONE);
		this.compositeBackground.setLayout(new GridLayout(3, false));

		this.lblBackground = new Label(this.compositeBackground, SWT.NONE);
		GridData gd_lblBackground = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblBackground.widthHint = 151;
		this.lblBackground.setLayoutData(gd_lblBackground);
		this.lblBackground.setText("Background");

		this.btnBackgroundShow = new Button(this.compositeBackground, SWT.RADIO);
		this.btnBackgroundShow.addSelectionListener(this);
		this.btnBackgroundShow.setSelection(true);
		this.btnBackgroundShow.setText("Show");

		this.btnBackgroundDebug = new Button(this.compositeBackground, SWT.RADIO);
		this.btnBackgroundDebug.addSelectionListener(this);
		this.btnBackgroundDebug.setText("Debug");

		this.compositeFreeLandscape = new Composite(this.compositeMapOptions, SWT.NONE);
		this.compositeFreeLandscape.setLayout(new GridLayout(4, false));

		this.lblFreeLandscape = new Label(this.compositeFreeLandscape, SWT.NONE);
		GridData gd_lblFreeLandscape = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblFreeLandscape.widthHint = 100;
		this.lblFreeLandscape.setLayoutData(gd_lblFreeLandscape);
		this.lblFreeLandscape.setText("Free Landscape");

		this.btnFreeLandscapeHide = new Button(this.compositeFreeLandscape, SWT.RADIO);
		this.btnFreeLandscapeHide.addSelectionListener(this);
		this.btnFreeLandscapeHide.setText("Hide");

		this.btnFreeLandscapeShow = new Button(this.compositeFreeLandscape, SWT.RADIO);
		this.btnFreeLandscapeShow.addSelectionListener(this);
		this.btnFreeLandscapeShow.setText("Show");

		this.btnFreeLandscapeDebug = new Button(this.compositeFreeLandscape, SWT.RADIO);
		this.btnFreeLandscapeDebug.addSelectionListener(this);
		this.btnFreeLandscapeDebug.setSelection(true);
		this.btnFreeLandscapeDebug.setText("Debug");

		this.compositeLandscape = new Composite(this.compositeMapOptions, SWT.NONE);
		this.compositeLandscape.setLayout(new GridLayout(4, false));

		this.lblLandscape = new Label(this.compositeLandscape, SWT.NONE);
		GridData gd_lblLandscape = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblLandscape.widthHint = 100;
		this.lblLandscape.setLayoutData(gd_lblLandscape);
		this.lblLandscape.setText("Landscape");

		this.btnLandscapeHide = new Button(this.compositeLandscape, SWT.RADIO);
		this.btnLandscapeHide.addSelectionListener(this);
		this.btnLandscapeHide.setText("Hide");

		this.btnLandscapeShow = new Button(this.compositeLandscape, SWT.RADIO);
		this.btnLandscapeShow.addSelectionListener(this);
		this.btnLandscapeShow.setText("Show");

		this.btnLandscapeDebug = new Button(this.compositeLandscape, SWT.RADIO);
		this.btnLandscapeDebug.addSelectionListener(this);
		this.btnLandscapeDebug.setSelection(true);
		this.btnLandscapeDebug.setText("Debug");

		this.compositeBuilding = new Composite(this.compositeMapOptions, SWT.NONE);
		this.compositeBuilding.setLayout(new GridLayout(4, false));

		this.lblBuilding = new Label(this.compositeBuilding, SWT.NONE);
		GridData gd_lblBuilding = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblBuilding.widthHint = 100;
		this.lblBuilding.setLayoutData(gd_lblBuilding);
		this.lblBuilding.setText("Building");

		this.btnBuildingHide = new Button(this.compositeBuilding, SWT.RADIO);
		this.btnBuildingHide.addSelectionListener(this);
		this.btnBuildingHide.setText("Hide");

		this.btnBuildingShow = new Button(this.compositeBuilding, SWT.RADIO);
		this.btnBuildingShow.addSelectionListener(this);
		this.btnBuildingShow.setText("Show");

		this.btnBuildingDebug = new Button(this.compositeBuilding, SWT.RADIO);
		this.btnBuildingDebug.addSelectionListener(this);
		this.btnBuildingDebug.setSelection(true);
		this.btnBuildingDebug.setText("Debug");

		this.compositeResourceCreation = new Composite(this.compositeMapOptions, SWT.NONE);
		this.compositeResourceCreation.setLayout(new GridLayout(3, false));

		this.lblResourceCreation = new Label(this.compositeResourceCreation, SWT.NONE);
		GridData gd_lblResourceCreation = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblResourceCreation.widthHint = 155;
		this.lblResourceCreation.setLayoutData(gd_lblResourceCreation);
		this.lblResourceCreation.setBounds(0, 0, 55, 15);
		this.lblResourceCreation.setText("Resource Creation");

		this.btnResourceCreationHide = new Button(this.compositeResourceCreation, SWT.RADIO);
		this.btnResourceCreationHide.setSelection(true);
		this.btnResourceCreationHide.addSelectionListener(this);
		this.btnResourceCreationHide.setText("Hide");

		this.btnResourceCreationDebug = new Button(this.compositeResourceCreation, SWT.RADIO);
		this.btnResourceCreationDebug.addSelectionListener(this);
		this.btnResourceCreationDebug.setText("Debug");

		this.compositeMapValue = new Composite(this.compositeMapOptions, SWT.NONE);
		this.compositeMapValue.setLayout(new GridLayout(3, false));

		this.lblMapValues = new Label(this.compositeMapValue, SWT.NONE);
		GridData gd_lblMapValues = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblMapValues.widthHint = 155;
		this.lblMapValues.setLayoutData(gd_lblMapValues);
		this.lblMapValues.setText("Map Values");

		this.btnMapValueHide = new Button(this.compositeMapValue, SWT.RADIO);
		this.btnMapValueHide.setSelection(true);
		this.btnMapValueHide.addSelectionListener(this);
		this.btnMapValueHide.setText("Hide");

		this.btnMapValueDebug = new Button(this.compositeMapValue, SWT.RADIO);
		this.btnMapValueDebug.addSelectionListener(this);
		this.btnMapValueDebug.setText("Debug");
		this.xpndtmMap.setHeight(200);

		this.canvasMap = new Canvas(this.compositeMap, SWT.BORDER | SWT.NO_BACKGROUND);
		this.canvasMap.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.canvasMap.addMouseMoveListener(this);
		this.canvasMap.addMouseListener(this);
		this.canvasMap.addKeyListener(this);
		this.canvasMap.addMouseWheelListener(this);
		this.canvasMap.addPaintListener(this);
		this.canvasMap.setLayout(new FillLayout(SWT.HORIZONTAL));
		this.canvasMap.getShell().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_HAND));

		this.tbtmBuildings = new TabItem(this.tabFolder, SWT.NONE);
		this.tbtmBuildings.setText("Buildings");

		this.compositeBuildings = new Composite(this.tabFolder, SWT.NONE);
		this.tbtmBuildings.setControl(this.compositeBuildings);
		FillLayout fl_compositeBuildings = new FillLayout(SWT.HORIZONTAL);
		fl_compositeBuildings.marginHeight = 2;
		this.compositeBuildings.setLayout(fl_compositeBuildings);

		this.tableViewerBuildings = new TableViewer(this.compositeBuildings, SWT.BORDER | SWT.FULL_SELECTION);
		this.tableBuildings = this.tableViewerBuildings.getTable();
		this.tableBuildings.setLinesVisible(true);
		this.tableBuildings.setHeaderVisible(true);

		this.tblclmnBuildingName = new TableColumn(this.tableBuildings, SWT.NONE);
		this.tblclmnBuildingName.setWidth(200);
		this.tblclmnBuildingName.setText("Name");

		this.tblclmnBuildingLevel = new TableColumn(this.tableBuildings, SWT.NONE);
		this.tblclmnBuildingLevel.setWidth(100);
		this.tblclmnBuildingLevel.setText("Level");

		this.tbtmResources = new TabItem(this.tabFolder, SWT.NONE);
		this.tbtmResources.setText("Resources");

		this.compositeResources = new Composite(this.tabFolder, SWT.NONE);
		this.tbtmResources.setControl(this.compositeResources);
		FillLayout fl_compositeResources = new FillLayout(SWT.HORIZONTAL);
		fl_compositeResources.marginHeight = 2;
		this.compositeResources.setLayout(fl_compositeResources);

		this.tableViewerResources = new TableViewer(this.compositeResources, SWT.BORDER | SWT.FULL_SELECTION);
		this.tableResources = this.tableViewerResources.getTable();
		this.tableResources.setLinesVisible(true);
		this.tableResources.setHeaderVisible(true);

		this.tblclmnResourceName = new TableColumn(this.tableResources, SWT.NONE);
		this.tblclmnResourceName.setWidth(200);
		this.tblclmnResourceName.setText("Name");

		this.tblclmnResourceAmount = new TableColumn(this.tableResources, SWT.NONE);
		this.tblclmnResourceAmount.setWidth(100);
		this.tblclmnResourceAmount.setText("Amount");

		this.tbtmDeposits = new TabItem(this.tabFolder, SWT.NONE);
		this.tbtmDeposits.setText("Deposits");

		this.compositeDeposits = new Composite(this.tabFolder, SWT.NONE);
		this.tbtmDeposits.setControl(this.compositeDeposits);
		FillLayout fl_compositeDeposits = new FillLayout(SWT.HORIZONTAL);
		fl_compositeDeposits.marginHeight = 2;
		this.compositeDeposits.setLayout(fl_compositeDeposits);

		this.tableViewerDeposits = new TableViewer(this.compositeDeposits, SWT.BORDER | SWT.FULL_SELECTION);
		this.tableDeposits = this.tableViewerDeposits.getTable();
		this.tableDeposits.setLinesVisible(true);
		this.tableDeposits.setHeaderVisible(true);

		this.tblclmnDepositName = new TableColumn(this.tableDeposits, SWT.NONE);
		this.tblclmnDepositName.setWidth(100);
		this.tblclmnDepositName.setText("Name");

		this.tblclmnDepositAmount = new TableColumn(this.tableDeposits, SWT.NONE);
		this.tblclmnDepositAmount.setWidth(100);
		this.tblclmnDepositAmount.setText("Amount");

		this.tblclmnDepositCapacity = new TableColumn(this.tableDeposits, SWT.NONE);
		this.tblclmnDepositCapacity.setWidth(100);
		this.tblclmnDepositCapacity.setText("Capacity");

		this.m_bindingContext = this.initDataBindings();

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		final Thread proxy = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					GameHandler.addDataHandler(1001, new ZoneHandler());
					GameHandler.addDataHandler(1014, new PlayerListHandler());

					GameHandler gh = new GameHandler();
					InputStream stream = new FileInputStream(new File("2.amf"));

					gh.parseAMF(stream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		proxy.start();
		TSOExpert.launchWindow();
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("TSO Expert");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(600, 400);
	}

	public void initZoneMap() {
		if (this.m_zonemap != null) {
			this.m_zonemap.setShowBackground(this.btnBackgroundShow.getSelection());
			this.m_zonemap.setShowFreeLandscape(this.btnFreeLandscapeShow.getSelection());
			this.m_zonemap.setShowLandscape(this.btnLandscapeShow.getSelection());
			this.m_zonemap.setShowBuilding(this.btnBuildingShow.getSelection());
			this.m_zonemap.setDebugBackground(this.btnBackgroundDebug.getSelection());
			this.m_zonemap.setDebugFreeLandscape(this.btnFreeLandscapeDebug.getSelection());
			this.m_zonemap.setDebugLandscape(this.btnLandscapeDebug.getSelection());
			this.m_zonemap.setDebugBuilding(this.btnBuildingDebug.getSelection());
			this.m_zonemap.setDebugResourceCreations(this.btnResourceCreationDebug.getSelection());
			this.m_zonemap.setDebugMapValues(this.btnMapValueDebug.getSelection());
		}
	}

	public void setZoneVO(ZoneVO zoneVO) {
		this.m_buildings.clear();
		this.m_buildings.addAll(zoneVO.getBuildings());

		this.m_resources.clear();
		this.m_resources.addAll(zoneVO.getResourcesVO().getResources_vector());

		this.m_deposits.clear();
		this.m_deposits.addAll(zoneVO.getDeposits());

		this.m_zonemap = new ZoneMap(zoneVO);
		this.initZoneMap();
		this.canvasMap.redraw();
	}

	@Override
	public void paintControl(PaintEvent e) {
		if (this.m_zonemap != null) {
			this.m_zonemap.draw(this.canvasMap, e.gc);

		} else {
			Color white = this.canvasMap.getDisplay().getSystemColor(SWT.COLOR_WHITE);

			e.gc.setBackground(white);
			e.gc.setForeground(white);
			e.gc.fillRectangle(e.gc.getClipping());
		}
	}

	@Override
	public void mouseScrolled(MouseEvent e) {
		if (this.m_zonemap != null) {
			this.m_zonemap.updateZoomFactor(e.count);
			this.canvasMap.redraw();
		}
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
		this.t_drag = true;
		this.t_lastX = e.x;
		this.t_lastY = e.y;
	}

	@Override
	public void mouseUp(MouseEvent e) {
		this.t_drag = false;
	}

	@Override
	public void mouseMove(MouseEvent e) {
		if (this.t_drag && this.m_zonemap != null) {
			this.m_zonemap.updateOffsetX(this.t_lastX - e.x);
			this.m_zonemap.updateOffsetY(this.t_lastY - e.y);
			this.t_lastX = e.x;
			this.t_lastY = e.y;
			this.canvasMap.redraw();
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		this.initZoneMap();
		this.canvasMap.redraw();
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		this.tableViewerBuildings.setContentProvider(listContentProvider);
		//
		IObservableMap[] observeMaps = BeansObservables.observeMaps(listContentProvider.getKnownElements(), BuildingVO.class, new String[]{"buildingName_string", "upgradeLevel"});
		this.tableViewerBuildings.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		//
		this.tableViewerBuildings.setInput(this.m_buildings);
		//
		ObservableListContentProvider listContentProvider_1 = new ObservableListContentProvider();
		this.tableViewerResources.setContentProvider(listContentProvider_1);
		//
		IObservableMap[] observeMaps_1 = BeansObservables.observeMaps(listContentProvider_1.getKnownElements(), ResourceVO.class, new String[]{"name_string", "amount"});
		this.tableViewerResources.setLabelProvider(new ObservableMapLabelProvider(observeMaps_1));
		//
		this.tableViewerResources.setInput(this.m_resources);
		//
		ObservableListContentProvider listContentProvider_2 = new ObservableListContentProvider();
		this.tableViewerDeposits.setContentProvider(listContentProvider_2);
		//
		IObservableMap[] observeMaps_2 = BeansObservables.observeMaps(listContentProvider_2.getKnownElements(), DepositVO.class, new String[]{"name_string", "amount", "maxAmount"});
		this.tableViewerDeposits.setLabelProvider(new ObservableMapLabelProvider(observeMaps_2));
		//
		this.tableViewerDeposits.setInput(this.m_deposits);
		//
		return bindingContext;
	}
}
