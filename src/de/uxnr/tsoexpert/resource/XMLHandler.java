package de.uxnr.tsoexpert.resource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLHandler implements IResourceHandler {
	private static final Map<String, Document> documents = new HashMap<String, Document>();

	@Override
	public void handleResource(File file) throws IOException {
		XMLHandler.loadDocument(file);
	}

	protected static Document loadDocument(File file) throws IOException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);

			documents.put(file.getName(), doc);

			return doc;
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	public static Document getDocument(File file) {
		String filename = file.getName();

		if (documents.containsKey(filename))
			return documents.get(filename);

		try {
			return loadDocument(file);
		} catch (IOException e) {
			return null;
		}
	}

	public static Document getDocument(String filename) {
		return documents.get(filename);
	}

	public static void main(String[] args) throws IOException {
		NodeList nodes = getDocument(GameSetting.gfx_settings).getElementsByTagName("Building");

		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			NamedNodeMap attrs = node.getAttributes();
			System.out.println(attrs.getNamedItem("name"));
		}
	}
}
