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
import de.uxnr.proxy.HostHandler;
import de.uxnr.tsoexpert.resource.IResourceHandler;

@SuppressWarnings("restriction")
public class StaticHandler implements HostHandler {
	public static final String RESOURCE_PREFIX = "SWMMO/";
	public static final String RESOURCE_PATH = "res/";
	public static final String PRIVATE_KEY_FILE = "PRIVATE_KEY";
	
	private static final Map<String, IResourceHandler> resourceHandlers = new HashMap<String, IResourceHandler>();
	
	private String path = null;
	
	private int prefix = 0;
	
	private boolean decryptFile = false;
	
	@Override
	public void handleRequest(String requestMethod, URI requestURI,
			Map<String, String> requestHeaders, InputStream body)
			throws IOException {
		
		this.path = requestURI.getPath();
		
		this.prefix = this.path.indexOf(RESOURCE_PREFIX);
		
		if (this.prefix > 0) {
			this.prefix += RESOURCE_PREFIX.length();
			
			this.path = this.path.substring(this.prefix);
			
			this.decryptFile = this.path.endsWith("_enc");
		}
	}

	@Override
	public void handleResponse(String requestMethod, URI requestURI,
			Map<String, String> requestHeaders,
			Map<String, String> responseHeaders, InputStream body)
			throws IOException {
		
		if (this.prefix > 0 && body.available() > 0)
			this.handleResource(this.saveResource(body));
	}
	
	private File saveResource(InputStream body) throws IOException {
		File file = new File(RESOURCE_PATH + this.path);
		file.getParentFile().mkdirs();
		
		FileOutputStream output = new FileOutputStream(file);
		
		int length = 0;
		byte[] bytearr = new byte[body.available()];
		while ((length = body.read(bytearr)) != -1) {
			output.write(bytearr, 0, length);
		}
		output.close();
		
		if (this.decryptFile)
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
	
	public void handleResource(File file) throws IOException {
		String path = file.getPath();
		
		for (Entry<String, IResourceHandler> handler : resourceHandlers.entrySet()) {
			if (path.matches(handler.getKey())) {
				try {
					handler.getValue().getClass().newInstance().handleResource(file);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
		}
	}
	
	public static void addResourceHandler(String path, IResourceHandler resourceHandler) {
		resourceHandlers.put(path, resourceHandler);
	}
	
	public static void removeResourceHandler(String path) {
		resourceHandlers.remove(path);
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File("res/GFX/game_settings.xml_enc");
		
		StaticHandler sh = new StaticHandler();
		file = sh.decryptFile(file);
	}
}
