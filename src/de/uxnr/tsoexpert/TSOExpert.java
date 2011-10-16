package de.uxnr.tsoexpert;

import java.io.IOException;

import de.uxnr.proxy.Proxy;
import de.uxnr.tsoexpert.game.PlayerListHandler;
import de.uxnr.tsoexpert.game.ZoneHandler;
import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.proxy.StaticHandler;
import de.uxnr.tsoexpert.resource.XMLHandler;

public class TSOExpert {
	public static void main(String[] args) throws IOException {
		GameHandler.addDataHandler(1001, new ZoneHandler());
		GameHandler.addDataHandler(1014, new PlayerListHandler());

		StaticHandler.addResourceHandler(".*\\.xml", new XMLHandler());

		Proxy proxy = new Proxy(8000);
		proxy.addHostHandler("(\\w*)\\.diesiedleronline\\.de", new GameHandler());
		proxy.addHostHandler("static(\\d*)\\.cdn\\.ubi\\.com", new StaticHandler());
		proxy.run();
	}
}
