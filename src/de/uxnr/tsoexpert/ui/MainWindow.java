package de.uxnr.tsoexpert.ui;

import java.util.Vector;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.uxnr.tsoexpert.game.communication.vo.BuildingVO;
import de.uxnr.tsoexpert.game.communication.vo.DepositVO;
import de.uxnr.tsoexpert.game.communication.vo.ResourceVO;
import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;

public class MainWindow extends ApplicationWindow {
	private final WritableList m_buildings = new WritableList(new Vector<BuildingVO>(), BuildingVO.class);
	private final WritableList m_resources = new WritableList(new Vector<ResourceVO>(), ResourceVO.class);
	private final WritableList m_deposits = new WritableList(new Vector<DepositVO>(), DepositVO.class);

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

		this.canvasMap = new Canvas(this.tabFolder, SWT.NONE);
		this.tbtmMap.setControl(this.canvasMap);
		this.canvasMap.setLayout(new FillLayout(SWT.HORIZONTAL));

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
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.setBlockOnOpen(true);
					window.open();
					Display.getCurrent().dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	public void setZoneVO(ZoneVO zoneVO) {
		this.m_buildings.clear();
		this.m_buildings.addAll(zoneVO.getBuildings());

		this.m_resources.clear();
		this.m_resources.addAll(zoneVO.getResourcesVO().getResources_vector());

		this.m_deposits.clear();
		this.m_deposits.addAll(zoneVO.getDeposits());
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
