package com.college.view.components;

import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Red pill "← Back" button — appears on all inner screens.
 * Clean draw order: pill first, then light-red circle on the left, then arrow text.
 */
public class BackButton extends JButton {

    private static final Color RED_LIGHT = new Color(0xFF7777);

    public BackButton() {
        super("← Back");
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(UITheme.bold(18f));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(160, 48));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int w = getWidth(), h = getHeight();
        int cs = h - 10;  // circle diameter
        int cy = 5;       // circle top y

        // Step 1: Draw the full red pill background
        g2.setColor(Constants.RED);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, h, h));

        // Step 2: Draw the white circle on the left (on top of pill)
        g2.setColor(Color.WHITE);
        g2.fillOval(5, cy, cs, cs);

        // Step 3: Arrow "←" centered inside the circle
        g2.setColor(Constants.RED);
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        String arrow = "←";
        int ax = 5 + (cs - fm.stringWidth(arrow)) / 2;
        int ay = (h - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(arrow, ax, ay);

        // Step 4: "Back" text to the right of the circle
        g2.setColor(Color.WHITE);
        int tx = 5 + cs + 14; // added more spacing
        g2.drawString("Back", tx, ay);

        g2.dispose();
    }
}
