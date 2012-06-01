package de.uxnr.tsoexpert.resource;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
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
	private static final Map<DocumentExpression, Object> objects = new HashMap<DocumentExpression, Object>();

	public static Node getNode(Document document, String path) {
		try {
			return (Node) evaluateExpression(document, XPathConstants.NODE, parseExpression(path));
		} catch (XPathExpressionException e) {
			return null;
		}
	}

	public static NodeList getNodes(Document document, String path) {
		try {
			return (NodeList) evaluateExpression(document, XPathConstants.NODESET, parseExpression(path));
		} catch (XPathExpressionException e) {
			return null;
		}
	}

	public static String getString(Document document, String path) {
		try {
			return (String) evaluateExpression(document, XPathConstants.STRING, parseExpression(path));
		} catch (XPathExpressionException e) {
			return null;
		}
	}

	public static Double getNumber(Document document, String path) {
		try {
			return (Double) evaluateExpression(document, XPathConstants.NUMBER, parseExpression(path));
		} catch (XPathExpressionException e) {
			return null;
		}
	}

	public static Boolean getBoolean(Document document, String path) {
		try {
			return (Boolean) evaluateExpression(document, XPathConstants.BOOLEAN, parseExpression(path));
		} catch (XPathExpressionException e) {
			return null;
		}
	}

	private static Object evaluateExpression(Document document, QName returnType, XPathExpression expression) throws XPathExpressionException {
		DocumentExpression documentExpression = new DocumentExpression(document, expression);

		if (objects.containsKey(documentExpression))
			return objects.get(documentExpression);

		Object obj = expression.evaluate(document, returnType);

		objects.put(documentExpression, obj);

		return obj;
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

	private static class DocumentExpression {
		private final Document document;
		private final XPathExpression expression;
		private final int hashCode;

		public DocumentExpression(Document document, XPathExpression expression) {
			this.document = document;
			this.expression = expression;
			this.hashCode = this.document.hashCode() ^ this.expression.hashCode();
		}

		public int hashCode() {
			return this.hashCode;
		}
	}
}
