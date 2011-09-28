package de.uxnr.tsoexpert.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import de.uxnr.amf.AMF;
import de.uxnr.proxy.HostHandler;

public class GameHandler implements HostHandler {
	private boolean parseAMF = false;
	
	@Override
	public void handleRequest(String requestMethod, URI requestURI,
			Map<String, String> requestHeaders, InputStream body) throws IOException {

		String contentType = requestHeaders.get("content-type");
		
		if (contentType != null)
			this.parseAMF = contentType.equalsIgnoreCase("application/x-amf");
	}

	@Override
	public void handleResponse(String requestMethod, URI requestURI,
			Map<String, String> requestHeaders,
			Map<String, String> responseHeaders, InputStream body) throws IOException {
		
		if (this.parseAMF)
			this.parseAMF(body);
	}
	
	private void parseAMF(InputStream body) throws IOException {
		AMF amf = new AMF(body);
		
		System.out.println("----------------------");
		System.out.println(amf.getHeaders().size());
		System.out.println(amf.getMessages().size());
	}
}
