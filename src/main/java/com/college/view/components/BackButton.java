package com.college.view.components;

import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Red pill "← Back" button — appears on all inner screens.
 */
public class BackButton extends JButton {

    public BackButton() {
        super("← Back");
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(UITheme.bold(20f));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(160, 50));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth(), h = getHeight();

        // Left light-red circle
        int cs = h - 8, cy = 4;
        g2.setColor(new Color(0xFF8888));
        g2.fillOval(4, cy, cs, cs);

        // Red pill background (behind circle)
        g2.setColor(Constants.RED);
        g2.fill(new RoundRectangle2D.Float(cs / 2f, 0, w - cs / 2f, h, h, h));

        // Re-draw circle on top (correct layer)
        g2.setColor(new Color(0xFF8888));
        g2.fillOval(4, cy, cs, cs);

        // Arrow
        g2.setColor(Constants.RED);
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        String arr = "←";
        int ax = 4 + (cs - fm.stringWidth(arr)) / 2;
        int ay = (h - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(arr, ax, ay);

        // Text
        g2.setColor(Color.WHITE);
        int tx = cs + 14;
        g2.drawString("Back", tx, ay);

        g2.dispose();
    }
}
