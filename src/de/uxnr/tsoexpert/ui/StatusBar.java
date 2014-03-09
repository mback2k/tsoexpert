package de.uxnr.tsoexpert.ui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

public class StatusBar extends JPanel {
  private static final long serialVersionUID = -2959948628247896789L;

  public StatusBar(JFrame parent) {
    super();
    this.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    this.setPreferredSize(new Dimension(parent.getWidth(), 18));
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
  }
}
