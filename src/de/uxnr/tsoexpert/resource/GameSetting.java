package de.uxnr.tsoexpert.resource;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GameSetting {
	public static final File game_settings = new File("res/GFX/game_settings.xml");
	public static final File gfx_settings = new File("res/GFX/gfx_settings.xml");

	private static final Map<String, XPathExpression> expressions = new HashMap<String, XPathExpression>();

	public static Node getNode(Document document, String path) {
		try {
			return (Node) parseExpression(path).evaluate(document, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			return null;
		}
	}

	public static NodeList getNodes(Document document, String path) {
		try {
			return (NodeList) parseExpression(path).evaluate(document, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			return null;
		}
	}

	public static String getString(Document document, String path) {
		try {
			return (String) parseExpression(path).evaluate(document, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			return null;
		}
	}

	public static Double getNumber(Document document, String path) {
		try {
			return (Double) parseExpression(path).evaluate(document, XPathConstants.NUMBER);
		} catch (XPathExpressionException e) {
			return null;
		}
	}

	public static Boolean getBoolean(Document document, String path) {
		try {
			return (Boolean) parseExpression(path).evaluate(document, XPathConstants.BOOLEAN);
		} catch (XPathExpressionException e) {
			return null;
		}
	}

	private static XPathExpression parseExpression(String path) throws XPathExpressionException {
		if (expressions.containsKey(path))
			return expressions.get(path);

		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();
		XPathExpression expr = xpath.compile(path);

		expressions.put(path, expr);

		return expr;
	}
}
