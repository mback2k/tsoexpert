package de.uxnr.dsoexpert.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import de.uxnr.proxy.HostHandler;

public class StaticHandler implements HostHandler {
	@Override
	public void handleRequest(String requestMethod, URI requestURI,
			Map<String, String> requestHeaders, InputStream body)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleResponse(String requestMethod, URI requestURI,
			Map<String, String> requestHeaders,
			Map<String, String> responseHeaders, InputStream body)
			throws IOException {
		// TODO Auto-generated method stub

	}
}
