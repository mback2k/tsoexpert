package de.uxnr.tsoexpert.nlib.data;

import java.util.List;
import java.util.Vector;

public class Indices {
  public List<SubtypeCalculated> subtypeCalculated_vector = new Vector<SubtypeCalculated>();
  public String name_string;

  @Override
  public String toString() {
    return this.name_string + ": " + this.subtypeCalculated_vector.toString();
  }
}
