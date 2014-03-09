package de.uxnr.tsoexpert.nlib;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import de.uxnr.tsoexpert.nlib.data.FrameCalculated;
import de.uxnr.tsoexpert.nlib.data.Indices;
import de.uxnr.tsoexpert.nlib.data.Main;
import de.uxnr.tsoexpert.nlib.data.SubtypeCalculated;

public class SpriteLibContainer {
  public Main loadSpriteLibFromBinaryData(InputStream stream) throws IOException {
    BinaryStream binarystream = new BinaryStream(stream);
    Main main = new Main();

    int length = binarystream.readInt();
    int[] vector2 = new int[length];

    for (int i = 0; i < length; i++) {
      Indices indices1 = new Indices();
      indices1.name_string = binarystream.readCStringAtPos_string(binarystream.readInt());
      main.spriteIndices_vector.add(indices1);

      vector2[i] = binarystream.readInt();
    }

    for (int i = 0; i < length; i++) {
      Indices indices2 = main.spriteIndices_vector.get(i);
      binarystream.setOffset(vector2[i]);

      int size = binarystream.readInt();
      int[] vector1 = new int[size];

      for (int j = 0; j < size; j++) {
        int offset = binarystream.readInt();
        vector1[j] = offset;
      }

      for (int j = 0; j < size; j++) {
        SubtypeCalculated subtypes = new SubtypeCalculated();

        int offset = vector1[j];
        binarystream.setOffset(offset);

        binarystream.readShort();

        int seqFootX = binarystream.readShort();
        int seqFootY = binarystream.readShort();

        binarystream.readShort();
        binarystream.readShort();

        int numFrames = binarystream.readShort();

        subtypes.seqFootX = seqFootX;
        subtypes.seqFootY = seqFootY;
        subtypes.numFrames = numFrames;

        int marker = binarystream.getOffset();
        int frameOffsX = 32767;
        int frameOffsY = 32767;

        for (int h = 0; h < numFrames; h++) {
          binarystream.setOffset(binarystream.getOffset() + 16);

          int offsetX = binarystream.readShort();
          int offsetY = binarystream.readShort();

          frameOffsX = Math.min(frameOffsX, offsetX);
          frameOffsY = Math.min(frameOffsY, offsetY);
        }

        binarystream.setOffset(marker);

        for (int h = 0; h < numFrames; h++) {
          binarystream.readShort();
          binarystream.readShort();
          binarystream.readShort();
          binarystream.readShort();

          int x = binarystream.readShort();
          int y = binarystream.readShort();
          int width = binarystream.readShort();
          int height = binarystream.readShort();
          int offsetX = binarystream.readShort();
          int offsetY = binarystream.readShort();
          int diffOffsetX = (offsetX - frameOffsX);
          int diffOffsetY = (offsetY - frameOffsY);

          FrameCalculated frames = new FrameCalculated();
          frames.size_u = (width + diffOffsetX);
          frames.size_v = (height + diffOffsetY);
          frames.frameOffsX = frameOffsX;
          frames.frameOffsY = frameOffsY;
          frames.calculateRect = new Rectangle(x, y, (width - 1), (height - 1));
          frames.calculatePoint = new Point(diffOffsetX, diffOffsetY);

          subtypes.frameList_vector.add(frames);
        }

        indices2.subtypeCalculated_vector.add(subtypes);
      }
    }

    return main;
  }

  public static void main(String[] args) throws IOException {
    File file = new File("res/GFX/landscape_lib/water_twinkle_2.bin");
    FileInputStream stream = new FileInputStream(file);

    SpriteLibContainer lib = new SpriteLibContainer();
    System.out.println(lib.loadSpriteLibFromBinaryData(stream));
  }
}
