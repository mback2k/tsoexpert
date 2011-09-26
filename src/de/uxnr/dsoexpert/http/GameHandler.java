package de.uxnr.dsoexpert.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import de.uxnr.amf.AMF;
import de.uxnr.proxy.HostHandler;

public class GameHandler implements HostHandler {
	private static long fileCounter = 1;
	private boolean parseAMF = false;
	
	@Override
	public void handleRequest(String requestMethod, URI requestURI,
			Map<String, String> requestHeaders, InputStream body) throws IOException {
		this.parseAMF = requestHeaders.get("content-type").equalsIgnoreCase("application/x-amf");
	}

	@Override
	public void handleResponse(String requestMethod, URI requestURI,
			Map<String, String> requestHeaders,
			Map<String, String> responseHeaders, InputStream body) throws IOException {
		if (!this.parseAMF)
			return;
		
		ByteArrayOutputStream bufferOutput = new ByteArrayOutputStream();
		FileOutputStream fileOutput = new FileOutputStream((fileCounter++)+".amf");
		
		int length = -1;
		byte[] data = new byte[1024];
		while ((length = body.read(data)) != -1) {
			fileOutput.write(data, 0, length);
			bufferOutput.write(data, 0, length);
		}
		
		fileOutput.close();
		bufferOutput.close();
		
		body = new ByteArrayInputStream(bufferOutput.toByteArray());
		
		AMF amf = new AMF(body);
		
		System.out.println("----------------------");
		System.out.println(amf.getHeaders().size());
		System.out.println(amf.getMessages().size());
	}
}
