package de.uxnr.tsoexpert.nlib.data;

import java.util.List;
import java.util.Vector;

public class SubtypeCalculated {
  public List<FrameCalculated> frameList_vector = new Vector<FrameCalculated>();
  public int numFrames;
  public double seqFootX;
  public double seqFootXScaled;
  public double seqFootY;
  public double seqFootYScaled;

  @Override
  public String toString() {
    return "seqFootXScaled: " + this.seqFootXScaled + ", seqFootYScaled: " + this.seqFootYScaled
        + ", seqFootX: " + this.seqFootX + ", seqFootY: " + this.seqFootY + ", frameList_vector: "
        + this.frameList_vector.toString() + ", numFrames: " + this.numFrames;
  }
}
