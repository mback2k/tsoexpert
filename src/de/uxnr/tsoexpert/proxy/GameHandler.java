package de.uxnr.tsoexpert.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.uxnr.amf.AMF;
import de.uxnr.amf.AMF_Message;
import de.uxnr.amf.AMF_Type;
import de.uxnr.amf.flex.type.AcknowledgeMessage;
import de.uxnr.amf.v0.type.AVMPlusObject;
import de.uxnr.amf.v3.AMF3_Type;
import de.uxnr.amf.v3.type.Object;
import de.uxnr.proxy.HostHandler;
import de.uxnr.tsoexpert.game.IDataHandler;
import de.uxnr.tsoexpert.game.PlayerListHandler;
import de.uxnr.tsoexpert.game.ZoneHandler;
import de.uxnr.tsoexpert.game.communication.Communication;
import de.uxnr.tsoexpert.game.communication.vo.ServerActionResult;
import de.uxnr.tsoexpert.game.communication.vo.ServerResponse;

public class GameHandler implements HostHandler {
	static {
		Communication.register();
	}

	private static final Map<Integer, IDataHandler<? extends AMF_Type>> dataHandlers = new HashMap<Integer, IDataHandler<? extends AMF_Type>>();

	@Override
	public void handleRequest(String requestMethod, URI requestURI,
			Map<String, String> requestHeaders, InputStream body) throws IOException {

		String contentType = requestHeaders.get("content-type");
		if (contentType != null) {
			if (contentType.equalsIgnoreCase("application/x-amf")) {
				this.parseAMF(body);
			}
		}
	}

	@Override
	public void handleResponse(String requestMethod, URI requestURI,
			Map<String, String> requestHeaders,
			Map<String, String> responseHeaders, InputStream body) throws IOException {

		String contentType = responseHeaders.get("content-type");
		if (contentType != null) {
			if (contentType.equalsIgnoreCase("application/x-amf")) {
				this.parseAMF(body);
			}
		}
	}

	private void parseAMF(InputStream body) throws IOException {
		AMF amf = new AMF(body);

		for (AMF_Message message : amf.getMessages())
			this.parseMessage(message);
	}

	private void parseMessage(AMF_Message message) throws IOException {
		AMF_Type body = message.getBody();

		if (body instanceof AVMPlusObject) {
			this.parseAVMPlusObject(body);
		}
	}

	private void parseAVMPlusObject(AMF_Type type) throws IOException {
		AMF3_Type value = ((AVMPlusObject) type).get();

		if (value instanceof Object) {
			Object object = ((Object) value);

			if (object.isExternalizable()) {
				this.parseExternalizable(object);
			}
		}
	}

	private void parseExternalizable(Object object) throws IOException {
		AMF3_Type value = object.getExternalized();

		if (value instanceof AcknowledgeMessage) {
			this.parseAcknowledgeMessage((AcknowledgeMessage) value);
		}
	}

	private void parseAcknowledgeMessage(AcknowledgeMessage acknowledgeMessage) throws IOException {
		AMF3_Type value = acknowledgeMessage.getBody();

		if (value instanceof ServerResponse) {
			this.parseServerResponse((ServerResponse) value);
		}
	}

	private void parseServerResponse(ServerResponse serverResponse) throws IOException {
		Integer type = serverResponse.getType();
		AMF3_Type value = serverResponse.getData();

		if (value instanceof ServerActionResult) {
			this.parseServerActionResult(type, (ServerActionResult) value);
		}
	}

	@SuppressWarnings("unchecked")
	private void parseServerActionResult(Integer type, ServerActionResult serverActionResult) throws IOException {
		AMF3_Type value = serverActionResult.getData();

		for (Entry<Integer, IDataHandler<? extends AMF_Type>> handler : dataHandlers.entrySet()) {
			if (type.equals(handler.getKey())) {
				try {
					handler.getValue().getClass().newInstance().handleData(value);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
		}
	}

	public static void addDataHandler(Integer type, IDataHandler<? extends AMF_Type> dataHandler) {
		dataHandlers.put(type, dataHandler);
	}

	public static void removeDataHandler(Integer type) {
		dataHandlers.remove(type);
	}

	public static void main(String[] args) throws IOException {
		GameHandler.addDataHandler(1001, new ZoneHandler());
		GameHandler.addDataHandler(1014, new PlayerListHandler());

		GameHandler gh = new GameHandler();
		InputStream stream = new FileInputStream(new File("2.amf"));

		long start = System.currentTimeMillis();

		gh.parseAMF(stream);

		long stop = System.currentTimeMillis();

		stream.close();

		System.out.println("parseAMF: " + (stop-start) + "ms");
	}
}
