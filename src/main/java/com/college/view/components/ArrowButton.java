package com.college.view.components;

import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Green pill button with a lighter circle on the right containing an arrow "→".
 * Matches the Figma "Login →" and "Request →" buttons exactly.
 */
public class ArrowButton extends JButton {

    private final Color GREEN  = Constants.GREEN;
    private final Color CIRCLE = Constants.GREEN_LIGHT;

    public ArrowButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(UITheme.bold(22f));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(SwingConstants.LEFT);
        setMargin(new Insets(0, 28, 0, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth(), h = getHeight();

        // Pill background
        g2.setColor(GREEN);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, h, h));

        // Right circle
        int cs = h - 8, cx = w - cs - 4, cy = 4;
        g2.setColor(CIRCLE);
        g2.fillOval(cx, cy, cs, cs);

        // Text
        g2.setFont(getFont());
        g2.setColor(Color.WHITE);
        FontMetrics fm = g2.getFontMetrics();
        int ty = (h - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(getText(), 28, ty);

        // Arrow in circle
        g2.setColor(GREEN);
        g2.setFont(getFont().deriveFont(getFont().getSize2D() + 4f));
        FontMetrics am = g2.getFontMetrics();
        String arrow = "→";
        int ax = cx + (cs - am.stringWidth(arrow)) / 2;
        int ay = (h - am.getHeight()) / 2 + am.getAscent();
        g2.drawString(arrow, ax, ay);

        g2.dispose();
    }
}
