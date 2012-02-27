package de.uxnr.tsoexpert.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.uxnr.proxy.HostHandler;
import de.uxnr.tsoexpert.game.player.Player;
import de.uxnr.tsoexpert.game.trade.TradeRequest;

public class ChatHandler implements HostHandler {

	private boolean parseChat = false;

	@Override
	public void handleRequest(String requestMethod, URI requestURI, Map<String, String> requestHeaders, InputStream body) throws IOException {

		System.out.println(requestURI.getRawPath());
		if (requestURI.getRawPath().equalsIgnoreCase("/http-bind/")) {
			this.parseChat = true;
		}

	}

	@Override
	public void handleResponse(String requestMethod, URI requestURI, Map<String, String> requestHeaders, Map<String, String> responseHeaders, InputStream body) throws IOException {
		if (this.parseChat) {
			this.parseChat(body);
		}

	}

	private void parseChat(InputStream body) throws IOException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document d = db.parse(body);
			NodeList messages = d.getElementsByTagName("message");
			for (int x = 0; x < messages.getLength(); x++) {
				Node n = messages.item(x);
				if (this.isTradeMessage(n)) {
					TradeRequest tradeRequest = new TradeRequest(n.getTextContent(), this.extractPlayerInformation(n));
					System.out.println(tradeRequest.toString());
				}
				n.getAttributes();
			}
		} catch (Exception e) {
			throw new IOException(e);
		}

	}

	private boolean isTradeMessage(Node n) {
		NamedNodeMap attributes = n.getAttributes();
		boolean groupchat = false;
		boolean fromtrade = false;
		for (int x = 0; x < attributes.getLength(); x++) {
			Node attribute = attributes.item(x);
			if (attribute.getNodeName().equalsIgnoreCase("type") && attribute.getNodeValue().equalsIgnoreCase("groupchat"))
				groupchat = true;
			if (attribute.getNodeName().equalsIgnoreCase("from") && attribute.getNodeValue().contains("trade@"))
				fromtrade = true;
		}
		return groupchat && fromtrade;
	}

	private Player extractPlayerInformation(Node n) {
		NodeList childNodes = n.getChildNodes();
		for (int x = 0; x < childNodes.getLength(); x++) {
			Node n2 = childNodes.item(x);
			if (n2.getNodeName().equalsIgnoreCase("bbmsg")) {
				NamedNodeMap attributes = n2.getAttributes();
				for (int y = 0; y < attributes.getLength(); y++) {
					Node attribute = attributes.item(y);
					if (attribute.getNodeName().equalsIgnoreCase("playername")) {
						return new Player(attribute.getNodeValue());
					}
				}
			}
		}
		return null;
	}
}
