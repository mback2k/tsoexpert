package de.uxnr.tsoexpert.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map.Entry;

import de.uxnr.amf.AMF;
import de.uxnr.amf.AMF_Message;
import de.uxnr.amf.AMF_Type;
import de.uxnr.amf.flex.type.AcknowledgeMessage;
import de.uxnr.amf.flex.type.CommandMessage;
import de.uxnr.amf.flex.type.RemotingMessage;
import de.uxnr.amf.v0.type.AVMPlusObject;
import de.uxnr.amf.v0.type.StrictArray;
import de.uxnr.amf.v3.AMF3_Type;
import de.uxnr.amf.v3.type.Array;
import de.uxnr.amf.v3.type.Object;
import de.uxnr.proxy.Headers;
import de.uxnr.proxy.HostHandler;
import de.uxnr.tools.ListMap;
import de.uxnr.tsoexpert.TSOHandler;
import de.uxnr.tsoexpert.game.IDataHandler;
import de.uxnr.tsoexpert.game.communication.Communication;
import de.uxnr.tsoexpert.game.communication.vo.ServerActionResult;
import de.uxnr.tsoexpert.game.communication.vo.ServerCall;
import de.uxnr.tsoexpert.game.communication.vo.ServerResponse;

public class GameHandler implements TSOHandler, HostHandler {
	static {
		Communication.register();
	}

	private final ListMap<Integer, IDataHandler<? extends AMF_Type>> dataHandlers = new ListMap<Integer, IDataHandler<? extends AMF_Type>>();

	@Override
	public void handleRequest(String requestMethod, URI requestURI,
			Headers requestHeaders, InputStream body) throws IOException {

		String contentType = requestHeaders.getFirst("content-type");
		if (contentType != null) {
			if (contentType.equalsIgnoreCase("application/x-amf")) {
				this.parseAMF(body);
			}
		}
	}

	@Override
	public void handleResponse(String requestMethod, URI requestURI,
			Headers requestHeaders,
			Headers responseHeaders, InputStream body) throws IOException {

		String contentType = responseHeaders.getFirst("content-type");
		if (contentType != null) {
			if (contentType.equalsIgnoreCase("application/x-amf")) {
				this.parseAMF(body);
			}
		}
	}

	public void parseAMF(InputStream body) throws IOException {
		AMF amf = new AMF(body);

		for (AMF_Message message : amf.getMessages())
			this.parseMessage(message);
	}

	private void parseMessage(AMF_Message message) throws IOException {
		AMF_Type body = message.getBody();

		if (body instanceof AVMPlusObject) {
			this.parseAVMPlusObject((AVMPlusObject) body);
		} else if (body instanceof StrictArray) {
			this.parseStrictArray((StrictArray) body);
		}
	}

	private void parseStrictArray(StrictArray strictArray) throws IOException {
		for (AMF_Type value : strictArray.values()) {
			if (value instanceof AVMPlusObject) {
				this.parseAVMPlusObject((AVMPlusObject) value);
			}
		}
	}

	private void parseAVMPlusObject(AVMPlusObject plusObject) throws IOException {
		AMF3_Type value = plusObject.get();

		if (value instanceof Object) {
			Object object = ((Object) value);

			if (object instanceof AcknowledgeMessage) {
				this.parseAcknowledgeMessage((AcknowledgeMessage) object);

			} else if (object instanceof CommandMessage) {
				this.parseCommandMessage((CommandMessage) object);

			} else if (object instanceof RemotingMessage) {
				this.parseRemotingMessage((RemotingMessage) object);

			} else {
				System.err.println(object.getClassName());
			}
		}
	}

	private void parseAcknowledgeMessage(AcknowledgeMessage acknowledgeMessage) throws IOException {
		AMF3_Type value = acknowledgeMessage.getBody();

		if (value instanceof ServerResponse) {
			this.parseServerResponse((ServerResponse) value);

		} else {
			System.err.println(value.toString());
		}
	}

	private void parseCommandMessage(CommandMessage commandMessage) throws IOException {
//		System.out.println("CommandMessage:");
//		System.out.println(commandMessage.getOperation());
	}

	private void parseRemotingMessage(RemotingMessage remotingMessage) throws IOException {
		AMF3_Type value = remotingMessage.getBody();

		if (value instanceof Array) {
			this.parseArray((Array) value);

		} else {
			System.err.println(value.toString());
		}
	}

	private void parseArray(Array array) throws IOException {
		for (AMF3_Type value : array.values()) {
			if (value instanceof ServerCall) {
				this.parseServerCall((ServerCall) value);

			} else {
				System.err.println(value.toString());
			}
		}
	}

	private void parseServerCall(ServerCall serverCall) {
//		System.out.println("ServerCall:");
//		System.out.println(serverCall.getType());
//		System.out.println(serverCall.getZoneID());
	}

	private void parseServerResponse(ServerResponse serverResponse) throws IOException {
		Integer type = serverResponse.getType();
		AMF3_Type value = serverResponse.getData();

//		System.out.println("ServerResponse:");
//		System.out.println(type);

		if (value instanceof ServerActionResult) {
			this.parseServerActionResult(type, (ServerActionResult) value);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private synchronized void parseServerActionResult(Integer type, ServerActionResult serverActionResult) throws IOException {
		AMF_Type value = serverActionResult.getData();

		for (Entry<Integer, List<IDataHandler<? extends AMF_Type>>> handler : this.dataHandlers.entrySet()) {
			if (type.equals(handler.getKey())) {
				for (IDataHandler listener : handler.getValue()) {
					try {
						listener.handleData(value);
					} catch (Exception e) {
						throw new IOException(e);
					}
				}
			}
		}
	}

	public synchronized void addDataHandler(Integer type, IDataHandler<? extends AMF_Type> dataHandler) {
		this.dataHandlers.add(type, dataHandler);
	}

	public synchronized void removeDataHandler(Integer type, IDataHandler<? extends AMF_Type> dataHandler) {
		this.dataHandlers.remove(type, dataHandler);
	}

	public synchronized void removeDataHandler(Integer type) {
		this.dataHandlers.remove(type);
	}

	public static void main(String[] args) throws IOException {
		GameHandler gameHandler = new GameHandler();

		InputStream stream = new FileInputStream(new File("2.amf"));
		long start = System.currentTimeMillis();
		gameHandler.parseAMF(stream);
		long stop = System.currentTimeMillis();
		stream.close();

		System.out.println("parseAMF: " + (stop-start) + "ms");
	}
}
