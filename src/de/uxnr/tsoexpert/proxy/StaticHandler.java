package de.uxnr.tsoexpert.proxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import de.uxnr.proxy.Headers;
import de.uxnr.proxy.HostHandler;
import de.uxnr.tsoexpert.TSOHandler;
import de.uxnr.tsoexpert.resource.IResourceHandler;

@SuppressWarnings("restriction")
public class StaticHandler implements TSOHandler, HostHandler {
	public static final String RESOURCE_PREFIX = "SWMMO/";
	public static final String RESOURCE_PATH = "res/";
	public static final String PRIVATE_KEY_FILE = "PRIVATE_KEY";
	
	private final Map<String, IResourceHandler> resourceHandlers = new HashMap<String, IResourceHandler>();

	@Override
	public void handleRequest(String requestMethod, URI requestURI,
			Headers requestHeaders, InputStream body)
			throws IOException {
		// Nothing to do here
	}

	@Override
	public void handleResponse(String requestMethod, URI requestURI,
			Headers requestHeaders,
			Headers responseHeaders, InputStream body)
			throws IOException {

		String path = requestURI.getPath();
		int prefix = path.indexOf(RESOURCE_PREFIX);

		if (prefix > 0) {
			prefix += RESOURCE_PREFIX.length();
			path = path.substring(prefix);
		}

		if (body.available() > 0) {
			this.handleResource(this.saveResource(path, body, path.endsWith("_enc")));
		}
	}
	
	private File saveResource(String path, InputStream body, boolean decrypt) throws IOException {
		File file = new File(RESOURCE_PATH + path);
		file.getParentFile().mkdirs();
		
		FileOutputStream output = new FileOutputStream(file);
		
		int length = 0;
		byte[] bytearr = new byte[body.available()];
		while ((length = body.read(bytearr)) != -1) {
			output.write(bytearr, 0, length);
		}
		output.close();
		
		if (decrypt)
			file = this.decryptFile(file);
		
		return file;
	}
	
	private byte[] readPrivateKey() throws IOException {
		File keyFile = new File(PRIVATE_KEY_FILE);
		
		FileInputStream input = new FileInputStream(keyFile);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int length = -1;
		byte[] bytearr = new byte[1024];
		while ((length = input.read(bytearr)) != -1) {
			output.write(bytearr, 0, length);
		}
		output.close();
		
		String key = new String(output.toByteArray());
		return key.trim().getBytes("UTF8");
	}
	
	private File decryptFile(File encrypted) throws IOException {
		File decrypted = new File(encrypted.getAbsolutePath().replaceAll("\\.(.*)_enc", "\\.$1"));
		
		FileInputStream input = new FileInputStream(encrypted);
		FileOutputStream output = new FileOutputStream(decrypted);
		
		BASE64Decoder base64 = new BASE64Decoder();
		byte[] bytearr = base64.decodeBuffer(input);
		
		Key key = new SecretKeySpec(this.readPrivateKey(), "Blowfish");
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		} catch (Exception e) {
			throw new IOException(e);
		}
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
		} catch (Exception e) {
			throw new IOException(e);
		}
		
		CipherInputStream stream = new CipherInputStream(new ByteArrayInputStream(bytearr), cipher);
		
		int length = -1;
		while ((length = stream.read(bytearr)) != -1) {
			output.write(bytearr, 0, length);
		}
		output.close();
		
		return decrypted;
	}

	public synchronized void handleResource(File file) throws IOException {
		String path = file.getPath();

		for (Entry<String, IResourceHandler> handler : this.resourceHandlers.entrySet()) {
			if (path.matches(handler.getKey())) {
				try {
					handler.getValue().handleResource(file);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
		}
	}

	public synchronized void addResourceHandler(String path, IResourceHandler resourceHandler) {
		this.resourceHandlers.put(path, resourceHandler);
	}

	public synchronized void removeResourceHandler(String path) {
		this.resourceHandlers.remove(path);
	}

	public static void main(String[] args) throws IOException {
		File file = new File("res/GFX/game_settings.xml_enc");
		
		StaticHandler staticHandler = new StaticHandler();
		file = staticHandler.decryptFile(file);
	}
}
