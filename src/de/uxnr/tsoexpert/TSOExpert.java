package de.uxnr.tsoexpert;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.UIManager;

import de.uxnr.proxy.Proxy;
import de.uxnr.tsoexpert.game.PlayerListHandler;
import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.proxy.StaticHandler;
import de.uxnr.tsoexpert.resource.XMLHandler;
import de.uxnr.tsoexpert.ui.MainWindow;
import de.uxnr.tsoexpert.ui.zone.ZoneHandler;

public class TSOExpert {
	private static Thread proxyThread;

	public static void launchProxy() throws IOException {
		GameHandler.addDataHandler(1001, new ZoneHandler());
		GameHandler.addDataHandler(1014, new PlayerListHandler());

		StaticHandler.addResourceHandler(".*\\.xml", new XMLHandler());

		Proxy proxy = new Proxy(8000);
		proxy.addHostHandler("(\\w*)\\.diesiedleronline\\.de", new GameHandler());
		proxy.addHostHandler("static(\\d*)\\.cdn\\.ubi\\.com", new StaticHandler());

		proxyThread = new Thread(proxy);
		proxyThread.start();
	}

	public static void launchWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainWindow window = new MainWindow();
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) throws IOException {
		launchProxy();
		launchWindow();
	}
}
