package de.uxnr.tsoexpert;

import java.io.IOException;

import de.uxnr.proxy.Proxy;
import de.uxnr.tsoexpert.proxy.GameHandler;
import de.uxnr.tsoexpert.proxy.StaticHandler;

public class TSOExpert {
	public static void main(String[] args) throws IOException, InterruptedException {
		Proxy proxy = new Proxy(8000);
		proxy.addHostHandler("game(\\d*).diesiedleronline.de", new GameHandler());
		proxy.addHostHandler("static(\\d*).cdn.ubi.com", new StaticHandler());
		proxy.run();
	}
}
