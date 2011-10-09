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

public class XMLHandler implements ResourceHandler {
	private static final Map<String, Document> documents = new HashMap<String, Document>();

	@Override
	public void handleResource(File file) throws IOException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			documents.put(file.getName(), db.parse(file));
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	public static Document getDocument(String filename) {
		return documents.get(filename);
	}

	public static void main(String[] args) throws IOException {
		File file = new File("res/GFX/gfx_settings.xml");

		XMLHandler xh = new XMLHandler();
		xh.handleResource(file);

		NodeList nodes = documents.get("gfx_settings.xml").getElementsByTagName("Building");

		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			NamedNodeMap attrs = node.getAttributes();
			System.out.println(attrs.getNamedItem("name"));
		}
	}
}
