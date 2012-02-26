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

public class TSOExpert {
	private static GameHandler gameHandler;
	private static StaticHandler staticHandler;

	private static Proxy proxy;
	private static Thread proxyThread;

	private static MainWindow window;

	public static void launchProxy() throws IOException {
		gameHandler = new GameHandler();
		gameHandler.addDataHandler(1014, new PlayerListHandler());

		staticHandler = new StaticHandler();
		staticHandler.addResourceHandler(".*\\.xml", new XMLHandler());

		proxy = new Proxy(8000);
		proxy.addHostHandler("(\\w*)\\.diesiedleronline\\.de", gameHandler);
		proxy.addHostHandler("static(\\d*)\\.cdn\\.ubi\\.com", staticHandler);

		proxyThread = new Thread(proxy);
		proxyThread.start();
	}

	public static void launchWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					window = new MainWindow();
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
