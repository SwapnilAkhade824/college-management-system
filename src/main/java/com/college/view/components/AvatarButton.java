package com.college.view.components;

import com.college.util.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Circular magenta avatar button — top-right of all screens.
 * Draws an anonymous mask icon using Graphics2D.
 * Attach ActionListener to handle dropdown menu.
 */
public class AvatarButton extends JButton {

    private static final Color BG   = Constants.MAGENTA;
    private final com.formdev.flatlaf.extras.FlatSVGIcon icon = new com.formdev.flatlaf.extras.FlatSVGIcon("icons/avatar.svg", 40, 45);
    static final int SIZE = 70;

    public AvatarButton() {
        setPreferredSize(new Dimension(SIZE, SIZE));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth(), h = getHeight();

        // Magenta circle background
        g2.setColor(BG);
        g2.fillOval(0, 0, w - 1, h - 1);

        // SVG Avatar icon centered
        int ix = (w - icon.getIconWidth()) / 2;
        int iy = (h - icon.getIconHeight()) / 2;
        icon.paintIcon(this, g2, ix, iy);

        g2.dispose();
    }
}
