package de.uxnr.tsoexpert;

import java.io.IOException;

import de.uxnr.proxy.Proxy;
import de.uxnr.tsoexpert.game.PlayerListHandler;
import de.uxnr.tsoexpert.game.ZoneHandler;
import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.proxy.StaticHandler;
import de.uxnr.tsoexpert.resource.XMLHandler;

public class TSOExpert {
	private static GameHandler gameHandler;
	private static StaticHandler staticHandler;

	private static Proxy proxy;

	public static void main(String[] args) throws IOException {
		gameHandler = new GameHandler();
		gameHandler.addDataHandler(1001, new ZoneHandler());
		gameHandler.addDataHandler(1014, new PlayerListHandler());

		staticHandler = new StaticHandler();
		staticHandler.addResourceHandler(".*\\.xml", new XMLHandler());

		proxy = new Proxy(8000);
		proxy.addHostHandler("(\\w*)\\.diesiedleronline\\.de", gameHandler);
		proxy.addHostHandler("static(\\d*)\\.cdn\\.ubi\\.com", staticHandler);
		proxy.start();
	}
}
