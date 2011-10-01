package de.uxnr.tsoexpert.resource;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLHandler implements ResourceHandler {
	private Document document = null;

	@Override
	public void handleResource(File file) throws IOException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			this.document = db.parse(file);
		} catch (Exception e) {
			throw new IOException(e);
		}

		System.out.println(file.getName());

		NodeList nodes = this.document.getElementsByTagName("Building");

		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			NamedNodeMap attrs = node.getAttributes();
			System.out.println(attrs.getNamedItem("name"));
		}
	}

	public static void main(String[] args) throws IOException {
		File file = new File("res/GFX/gfx_settings.xml");

		XMLHandler xh = new XMLHandler();
		xh.handleResource(file);
	}
}
