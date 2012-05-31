package de.uxnr.tsoexpert;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import de.uxnr.proxy.Proxy;
import de.uxnr.tsoexpert.proxy.ChatHandler;
import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.proxy.StaticHandler;
import de.uxnr.tsoexpert.resource.SpriteHandler;
import de.uxnr.tsoexpert.resource.XMLHandler;
import de.uxnr.tsoexpert.ui.MainWindow;

public class TSOExpert {
	private static TSORegistry registry = new TSORegistry();

	private static GameHandler gameHandler;
	private static StaticHandler staticHandler;
	private static XMLHandler xmlHandler;
	private static SpriteHandler spriteHandler;
	private static ChatHandler chatHandler;

	private static Proxy proxy;
	private static Thread proxyThread;

	private static MainWindow window;

	public static void launchProxy() throws IOException {
		gameHandler = new GameHandler();
		registry.register(gameHandler);

		xmlHandler = new XMLHandler();
		registry.register(xmlHandler);

		spriteHandler = new SpriteHandler();
		registry.register(spriteHandler);

		staticHandler = new StaticHandler();
		staticHandler.addResourceHandler(".*\\.xml", xmlHandler);
		staticHandler.addResourceHandler(".*\\.(png|jpg|gif|bin)", spriteHandler);
		registry.register(staticHandler);

		chatHandler = new ChatHandler();

		proxy = new Proxy(8000);
		proxy.addHostHandler("(\\w*)\\.diesiedleronline\\.de", gameHandler);
		proxy.addHostHandler("static(\\d*)\\.cdn\\.ubi\\.com", staticHandler);
		proxy.addHostHandler("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})", chatHandler);

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
		try {
			launchProxy();
			launchWindow();
		} catch (Error e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static TSORegistry getRegistry() {
		return registry;
	}

	public static TSOHandler getHandler(String handlerName) {
		return registry.lookup(handlerName);
	}
}
