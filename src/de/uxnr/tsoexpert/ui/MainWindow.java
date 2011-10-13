package de.uxnr.tsoexpert.ui;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
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

public class MainWindow extends ApplicationWindow {
	private TabFolder tabFolder;
	private TabItem tbtmMap;
	private TabItem tbtmBuildings;
	private TabItem tbtmResources;
	private Canvas canvasMap;
	private Table tableBuildings;
	private TableViewer tableViewerBuildings;
	private Table tableResources;
	private TableViewer tableViewerResources;
	private TableColumn tblclmnName;
	private TableViewerColumn tableViewerColumn;
	private TableColumn tblclmnAmount;
	private TableViewerColumn tableViewerColumn_1;

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

		this.tableViewerBuildings = new TableViewer(this.tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		this.tableBuildings = this.tableViewerBuildings.getTable();
		this.tbtmBuildings.setControl(this.tableBuildings);

		this.tbtmResources = new TabItem(this.tabFolder, SWT.NONE);
		this.tbtmResources.setText("Resources");

		this.tableViewerResources = new TableViewer(this.tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		this.tableResources = this.tableViewerResources.getTable();
		this.tbtmResources.setControl(this.tableResources);

		this.tableViewerColumn = new TableViewerColumn(this.tableViewerResources, SWT.NONE);
		this.tblclmnName = this.tableViewerColumn.getColumn();
		this.tblclmnName.setWidth(100);
		this.tblclmnName.setText("Name");

		this.tableViewerColumn_1 = new TableViewerColumn(this.tableViewerResources, SWT.NONE);
		this.tblclmnAmount = this.tableViewerColumn_1.getColumn();
		this.tblclmnAmount.setWidth(100);
		this.tblclmnAmount.setText("Amount");

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
		try {
			MainWindow window = new MainWindow();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(600, 400);
	}
}
