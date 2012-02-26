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

import de.uxnr.tsoexpert.TSOHandler;
import de.uxnr.tsoexpert.file.FileData;

public class XMLHandler implements TSOHandler, IResourceHandler {
	private final Map<String, Document> documents = new HashMap<String, Document>();

	@Override
	public void handleResource(FileData fd) throws IOException {
		this.loadDocument(fd);
	}

	private Document loadDocument(File file) throws IOException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = null;

			if (file instanceof FileData) {
				doc = db.parse(((FileData) file).getInputStream());
			} else {
				doc = db.parse(file);
			}

			this.documents.put(file.getName(), doc);

			return doc;
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	public Document getDocument(File file) {
		String name = file.getName();

		if (this.documents.containsKey(name))
			return this.documents.get(name);

		try {
			return loadDocument(file);
		} catch (IOException e) {
			return null;
		}
	}

	public Document getDocument(String name) {
		return this.documents.get(name);
	}

	public static void main(String[] args) throws IOException {
		XMLHandler xmlHandler = new XMLHandler();
		NodeList nodes = xmlHandler.getDocument(GameSetting.gfx_settings).getElementsByTagName("Building");

		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			NamedNodeMap attrs = node.getAttributes();
			System.out.println(attrs.getNamedItem("name"));
		}
	}
}
