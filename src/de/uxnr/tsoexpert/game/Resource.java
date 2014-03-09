package de.uxnr.tsoexpert.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Resource implements Parsable {

  private static Map<String, Resource> ResourceMap = new HashMap<String, Resource>();

  private String name;
  private int maxLimit;
  private int id;
  private Vector<Creation> creator;
  private Vector<LimitExpansion> limitExpansions;

  private Resource(String name2) {
    this.name = name2;
    creator = new Vector<Creation>();
    limitExpansions = new Vector<LimitExpansion>();
    ResourceMap.put(this.name, this);
  }

  public static void parseAll(Node resourceDefinitions) throws InvalidGameSettingsException {
    NodeList childNodes = resourceDefinitions.getChildNodes();
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
        Resource r = new Resource(name);
        r.parse(node);
      }
    }
  }

  public static Resource getByName(String name) {
    return ResourceMap.get(name);
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMaxLimit() {
    return this.maxLimit;
  }

  public void setMaxLimit(int maxLimit) {
    this.maxLimit = maxLimit;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Vector<Creation> getCreator() {
    return this.creator;
  }

  public void setCreator(Vector<Creation> creator) {
    this.creator = creator;
  }

  public Vector<LimitExpansion> getLimitExpansions() {
    return this.limitExpansions;
  }

  public void setLimitExpansions(Vector<LimitExpansion> limitExpansions) {
    this.limitExpansions = limitExpansions;
  }

  @Override
  public void parse(Node node) throws InvalidGameSettingsException {
    NamedNodeMap attributes = node.getAttributes();
    for (int x = 0; x < attributes.getLength(); x++) {
      Node attribute = attributes.item(x);
      if (attribute.getNodeName().equalsIgnoreCase("MaxLimit")) {
        this.maxLimit = Integer.parseInt(attribute.getNodeValue());
      }
    }
    NodeList childNodes = node.getChildNodes();
    for (int x = 0; x < childNodes.getLength(); x++) {
      Node child = childNodes.item(x);
      if (child.getNodeName().equalsIgnoreCase("Creation")) {
        Creation c = null;
        for (int y = 0; y < child.getAttributes().getLength(); y++) {
          Node attribute = child.getAttributes().item(y);
          if (attribute.getNodeName().equalsIgnoreCase("CreationId")) {
            c = Creation.getById(Integer.parseInt(attribute.getNodeValue()));
          }
        }
        if (c == null) {
          throw new InvalidGameSettingsException("Invalid Creation Definition.");
        } else {
          this.creator.add(c);
          c.parse(child);
        }
      } else if (child.getNodeName().equalsIgnoreCase("ExpandMaxLimit")) {
        LimitExpansion lm = new LimitExpansion();
        lm.parse(child);
        this.limitExpansions.add(lm);
      }
    }

  }

}
