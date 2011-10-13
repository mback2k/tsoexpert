package de.uxnr.tsoexpert;

import org.eclipse.swt.widgets.Display;

import de.uxnr.proxy.Proxy;
import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.proxy.StaticHandler;
import de.uxnr.tsoexpert.resource.XMLHandler;
import de.uxnr.tsoexpert.ui.MainWindow;

public class TSOExpert {
	public static void main(String[] args) throws IOException, InterruptedException {
		StaticHandler.addResourceHandler(".*\\.xml", new XMLHandler());

		Proxy proxy = new Proxy(8000);
		proxy.addHostHandler("game(\\d*).diesiedleronline.de", new GameHandler());
		proxy.addHostHandler("static(\\d*).cdn.ubi.com", new StaticHandler());

		Thread proxyThread = new Thread(proxy);
		proxyThread.start();

		MainWindow window = new MainWindow();
		window.setBlockOnOpen(true);
		window.open();
		Display.getCurrent().dispose();
	}
}
