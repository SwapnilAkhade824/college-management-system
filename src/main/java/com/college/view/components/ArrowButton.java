package com.college.view.components;

import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Green pill button with a lighter circle on the right containing an arrow "→".
 * Matches the Figma "Login →" and "Request →" buttons exactly.
 * All drawing is proportional to the button's actual dimensions.
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
        setFont(UITheme.bold(20f));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(SwingConstants.LEFT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int w = getWidth(), h = getHeight();

        // Pill background (full width)
        g2.setColor(GREEN);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, h, h));

        // Right circle — proportional: diameter = h - 10, 5px from right edge
        int cs = h - 10;
        int cx = w - cs - 5;
        int cy = 5;
        g2.setColor(CIRCLE);
        g2.fillOval(cx, cy, cs, cs);

        // Text label — left-padded at ~30% of height
        g2.setFont(getFont());
        g2.setColor(Color.WHITE);
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textX = (cx - textWidth) / 2;
        int ty = (h - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(getText(), textX, ty);

        // DEBUG BORDERS
        // g2.setColor(Color.YELLOW); g2.drawRect(0, 0, w-1, h-1);
        // g2.setColor(Color.MAGENTA); g2.drawRect(0, 0, cx, h-1);
        // g2.setColor(Color.RED); g2.drawRect(textX, ty - fm.getAscent(), textWidth, fm.getHeight());

        // Arrow "→" centered inside circle
        Font arrowFont = getFont().deriveFont(getFont().getSize2D() + 2f);
        g2.setFont(arrowFont);
        g2.setColor(GREEN);
        FontMetrics am = g2.getFontMetrics();
        String arrow = "→";
        int ax = cx + (cs - am.stringWidth(arrow)) / 2;
        int ay = (h - am.getHeight()) / 2 + am.getAscent();
        g2.drawString(arrow, ax, ay);

        g2.dispose();
    }
}
