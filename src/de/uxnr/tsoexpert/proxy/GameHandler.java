package de.uxnr.tsoexpert.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import de.uxnr.amf.AMF;
import de.uxnr.amf.AMF_Message;
import de.uxnr.amf.AMF_Type;
import de.uxnr.amf.flex.msg.AcknowledgeMessage;
import de.uxnr.amf.v0.type.AVMPlusObject;
import de.uxnr.amf.v3.AMF3_Type;
import de.uxnr.amf.v3.type.Array;
import de.uxnr.amf.v3.type.Object;
import de.uxnr.proxy.HostHandler;
import de.uxnr.tsoexpert.game.communication.vo.BackgroundTileVO;
import de.uxnr.tsoexpert.game.communication.vo.QuestVO;
import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;

public class GameHandler implements HostHandler {
	public static final String ITEM_BODY					= "body";
	public static final String ITEM_DATA					= "data";

	public static final String VO_SERVER_RESPONSE			= "defaultGame.Communication.VO.dServerResponse";
	public static final String VO_SERVER_ACTION_RESULT		= "defaultGame.Communication.VO.dServerActionResult";
	public static final String VO_SERVER_CLIENT_UPDATE		= "defaultGame.Communication.VO.dServerClientUpdateVO";

	public static final String VO_ZONE						= "defaultGame.Communication.VO.dZoneVO";
	public static final String VO_QUEST						= "defaultGame.Communication.VO.dQuestVO";
	public static final String VO_BACKGROUND_TILE			= "defaultGame.Communication.VO.dBackgroundTileVO";

	public static final String FLEX_ACKNOWLEDGE_MESSAGE		= "DSK";
	public static final String FLEX_ARRAY_COLLECTION		= "flex.messaging.io.ArrayCollection";

	static {
		AMF.registerObjectClass(VO_ZONE, ZoneVO.class);
		AMF.registerObjectClass(VO_QUEST, QuestVO.class);
		AMF.registerObjectClass(VO_BACKGROUND_TILE, BackgroundTileVO.class);
	}

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

		for (AMF_Message message : amf.getMessages())
			this.parseMessage(message);
	}

	private void parseMessage(AMF_Message message) throws IOException {
		AMF_Type body = message.getBody();

		if (body instanceof AVMPlusObject) {
			AMF3_Type value = ((AVMPlusObject) body).get();

			if (value instanceof Object) {
				Object object = ((Object) value);

				if (object.getClassName().equals(FLEX_ACKNOWLEDGE_MESSAGE)) {
					value = object.getExternal();

					if (value instanceof AcknowledgeMessage) {
						AcknowledgeMessage msg = (AcknowledgeMessage) value;

						object = (Object) msg.get(GameHandler.ITEM_BODY);
						if (object.getClassName().equals(VO_SERVER_RESPONSE)) {
							this.parseServerResponse(object);
						}
					}
				}
			}
		}
	}

	private void parseServerResponse(Object object) throws IOException {
		object = (Object) object.get(ITEM_DATA);
		if (object.getClassName().equals(VO_SERVER_ACTION_RESULT)) {
			this.parseServerActionResult(object);
		}
	}

	private void parseServerActionResult(Object object) throws IOException {
		AMF3_Type value = object.get(ITEM_DATA);
		if (value instanceof Object) {
			object = (Object) value;
			String className = object.getClassName();
			if (className.equals(FLEX_ARRAY_COLLECTION)) {
				this.parseArrayCollection((Array) object.getExternal());
			}
		} else {
			System.out.println(value.getClass().getName());
		}
	}

	private void parseArrayCollection(Array array) {
		for (AMF3_Type value : array.values()) {
			if (value instanceof Object) {
				Object object = (Object) value;
				if (object.getClassName().equals(VO_SERVER_CLIENT_UPDATE)) {
					this.parseServerClientUpdate(object);
				} else {
					System.out.println(object.getClassName());
				}
			}
		}
	}

	private void parseServerClientUpdate(Object object) {
		System.out.println(object.keySet());
	}

	public static void main(String[] args) throws IOException {
		GameHandler gh = new GameHandler();
		InputStream stream = new FileInputStream(new File("2.amf"));

		long start = System.currentTimeMillis();

		gh.parseAMF(stream);

		long stop = System.currentTimeMillis();

		stream.close();

		System.out.println("parseAMF: " + (stop-start) + "ms");
	}
}