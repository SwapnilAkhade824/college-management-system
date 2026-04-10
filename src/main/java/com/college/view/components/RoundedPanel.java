package com.college.view.components;

import javax.swing.*;
import java.awt.*;

/** A JPanel with a filled rounded rectangle background. */
public class RoundedPanel extends JPanel {

    private Color bg;
    private int   arc;

    public RoundedPanel(Color bg, int arc) {
        this.bg = bg; this.arc = arc;
        setOpaque(false);
    }

    public RoundedPanel(int arc) { this(new java.awt.Color(0xE9E9E9), arc); }

    public void setBgColor(Color c) { this.bg = c; repaint(); }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(bg);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        g2.dispose();
        super.paintComponent(g);
    }
}
