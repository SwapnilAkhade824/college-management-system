package com.college.view.components;

import javax.swing.*;
import java.awt.*;

/** A JPanel with a rounded outline border only (no fill). */
public class RoundedOutlinePanel extends JPanel {

    private Color borderColor;
    private int   arc;
    private int   stroke;

    public RoundedOutlinePanel(Color borderColor, int arc, int stroke) {
        this.borderColor = borderColor; this.arc = arc; this.stroke = stroke;
        setOpaque(false);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(stroke));
        g2.drawRoundRect(stroke / 2, stroke / 2,
                getWidth() - stroke, getHeight() - stroke, arc, arc);
        g2.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
