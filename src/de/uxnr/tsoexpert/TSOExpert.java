package de.uxnr.tsoexpert;

import java.io.IOException;

import de.uxnr.proxy.Proxy;
import de.uxnr.tsoexpert.proxy.ChatHandler;
import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.proxy.StaticHandler;
import de.uxnr.tsoexpert.resource.XMLHandler;

public class TSOExpert {
	public static void main(String[] args) throws IOException, InterruptedException {
		StaticHandler.addResourceHandler(".*\\.xml", new XMLHandler());

		Proxy proxy = new Proxy(8000);
		proxy.addHostHandler("game(\\d*).diesiedleronline.de", new GameHandler());
		proxy.addHostHandler("static(\\d*).cdn.ubi.com", new StaticHandler());
		proxy.addHostHandler("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})", new ChatHandler());
		proxy.run();
	}
}
