package de.uxnr.tsoexpert.game;

import org.w3c.dom.Node;

public interface Parsable {
  public void parse(Node node) throws InvalidGameSettingsException;

}
