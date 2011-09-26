package de.uxnr.dsoexpert;

import java.io.IOException;

import de.uxnr.dsoexpert.http.GameHandler;
import de.uxnr.dsoexpert.http.StaticHandler;
import de.uxnr.proxy.Proxy;

public class DSOExpert {
	public static void main(String[] args) throws IOException, InterruptedException {
		Proxy proxy = new Proxy(8000);
		proxy.addHostHandler("game(\\d*).diesiedleronline.de", new GameHandler());
		proxy.addHostHandler("static(\\d*).cdn.ubi.com", new StaticHandler());
		proxy.run();
	}
}
