package de.uxnr.tsoexpert;

import java.io.IOException;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;

import de.uxnr.proxy.Proxy;
import de.uxnr.tsoexpert.game.PlayerListHandler;
import de.uxnr.tsoexpert.game.ZoneHandler;
import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.proxy.StaticHandler;
import de.uxnr.tsoexpert.resource.XMLHandler;
import de.uxnr.tsoexpert.ui.MainWindow;

public class TSOExpert {
	private static Display defaultDisplay;
	private static Realm defaultRealm;
	private static Thread proxyThread;

	private static void launchProxy() throws IOException {
		GameHandler.addDataHandler(1001, new ZoneHandler());
		GameHandler.addDataHandler(1014, new PlayerListHandler());

		StaticHandler.addResourceHandler(".*\\.xml", new XMLHandler());

		Proxy proxy = new Proxy(8000);
		proxy.addHostHandler("game(\\d*).diesiedleronline.de", new GameHandler());
		proxy.addHostHandler("static(\\d*).cdn.ubi.com", new StaticHandler());

		proxyThread = new Thread(proxy);
		proxyThread.start();
	}

	private static void launchWindow() {
		defaultDisplay = new Display();
		defaultRealm = SWTObservables.getRealm(defaultDisplay);

		Realm.runWithDefault(defaultRealm, new Runnable() {
			@Override
			public void run() {
				MainWindow window = new MainWindow();

				ZoneHandler.setWindow(window);

				window.setBlockOnOpen(true);
				window.open();
			}
		});

		defaultDisplay.dispose();
	}

	public static void run(Runnable runnable) {
		defaultRealm.exec(runnable);
	}

	public static void main(String[] args) throws IOException {
		launchProxy();
		launchWindow();

		System.exit(0);
	}
}
