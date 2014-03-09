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

  private static final Map<String, XPathExpression> expressions =
      new HashMap<String, XPathExpression>();
  private static final Map<DocumentPath, Object> objects = new HashMap<DocumentPath, Object>();

  public static Node getNode(Document document, String path) {
    try {
      return (Node) evaluateExpression(document, XPathConstants.NODE, path);
    } catch (XPathExpressionException e) {
      return null;
    }
  }

  public static NodeList getNodes(Document document, String path) {
    try {
      return (NodeList) evaluateExpression(document, XPathConstants.NODESET, path);
    } catch (XPathExpressionException e) {
      return null;
    }
  }

  public static String getString(Document document, String path) {
    try {
      return (String) evaluateExpression(document, XPathConstants.STRING, path);
    } catch (XPathExpressionException e) {
      return null;
    }
  }

  public static Double getNumber(Document document, String path) {
    try {
      return (Double) evaluateExpression(document, XPathConstants.NUMBER, path);
    } catch (XPathExpressionException e) {
      return null;
    }
  }

  public static Boolean getBoolean(Document document, String path) {
    try {
      return (Boolean) evaluateExpression(document, XPathConstants.BOOLEAN, path);
    } catch (XPathExpressionException e) {
      return null;
    }
  }

  private static Object evaluateExpression(Document document, QName returnType, String path)
      throws XPathExpressionException {
    DocumentPath documentPath = new DocumentPath(document, path);

    if (objects.containsKey(documentPath))
      return objects.get(documentPath);

    XPathExpression expr = parseExpression(path);
    Object obj = expr.evaluate(document, returnType);

    objects.put(documentPath, obj);

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

  private static class DocumentPath {
    private final Document document;
    private final String path;
    private final int hashCode;

    public DocumentPath(Document document, String path) {
      this.document = document;
      this.path = path;
      this.hashCode = this.document.hashCode() ^ this.path.hashCode();
    }

    public int hashCode() {
      return this.hashCode;
    }
  }
}
