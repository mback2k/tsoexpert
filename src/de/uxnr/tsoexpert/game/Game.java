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
		Resource r = Resource.getByName("Titanium");

		System.out.println(r.getMaxLimit());
		for (Creation c : r.getCreator()) {
			System.out.println(c);
		}
		for (LimitExpansion lm : r.getLimitExpansions()) {
			System.out.println(lm);
		}

		return null;
	}

	private static void parseResources(Document d, Game game)
			throws InvalidGameSettingsException {
		NodeList resourceDefinitions = d
				.getElementsByTagName("ResourceDefinitions");
		if (resourceDefinitions.getLength() == 1) {
			Resource.parseAll(resourceDefinitions.item(0));
		} else {
			throw new InvalidGameSettingsException(
					"No ResourceDefinitions present");
		}
	}

}
