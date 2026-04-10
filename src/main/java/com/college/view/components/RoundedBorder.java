package com.college.view.components;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/** AbstractBorder that draws a rounded rectangle outline — used on JTextField/JPasswordField. */
public class RoundedBorder extends AbstractBorder {

    private final Color color;
    private final int   radius;
    private final int   thickness;

    public RoundedBorder(Color color, int radius, int thickness) {
        this.color = color; this.radius = radius; this.thickness = thickness;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawRoundRect(x + thickness / 2, y + thickness / 2,
                w - thickness, h - thickness, radius, radius);
        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(8, 14, 8, 14);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets i) {
        i.top = i.bottom = 8; i.left = i.right = 14; return i;
    }
}
