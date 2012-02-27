package de.uxnr.tsoexpert.game;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Game {

	public static void main(String args[]) throws Exception {
		File file = new File("res/GFX/game_settings.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(file);
		parse(document);
	}

	public static Game parse(Document d) throws InvalidGameSettingsException {
		Game game = new Game();
		parseResources(d, game);

		return null;
	}

	private static void parseResources(Document d, Game game) throws InvalidGameSettingsException {
		NodeList resourceDefinitions = d.getElementsByTagName("ResourceDefinitions");
		if (resourceDefinitions.getLength() == 1) {
			NodeList childNodes = resourceDefinitions.item(0).getChildNodes();
			for (int x = 0; x < childNodes.getLength(); x++) {
				Node node = childNodes.item(x);
				if (node.getNodeName().equalsIgnoreCase("ResourceDefinition")) {
					NamedNodeMap attributes = node.getAttributes();
					String name = null;
					for (int y = 0; y < attributes.getLength(); y++) {
						Node attribute = attributes.item(y);
						if (attribute.getNodeName().equalsIgnoreCase("name")) {
							name = attribute.getNodeValue();
						}
					}
					Resource r = Resource.getByName(name);
					r.parse(node);
				}
			}
		} else {
			throw new InvalidGameSettingsException("No ResourceDefinitions present");
		}
	}

}
