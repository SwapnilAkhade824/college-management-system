package com.college.view.components;

import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Circular magenta avatar button — top-right of all screens.
 * Draws an anonymous mask icon using Graphics2D.
 * Attach ActionListener to handle dropdown menu.
 */
public class AvatarButton extends JButton {

    private static final Color BG   = Constants.MAGENTA;
    private static final Color FG   = Color.WHITE;
    private static final int   SIZE = 60;

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
        g2.fillOval(0, 0, w, h);

        // Draw simple anonymous mask icon (shield + eyes)
        g2.setColor(FG);
        int pad = w / 5;
        int sw  = w - 2 * pad;
        int sh  = h - 2 * pad;
        // Shield body (rounded rectangle as base)
        g2.fillRoundRect(pad, pad, sw, sh, sw / 3, sw / 3);

        // Cutouts (eyes/mask slits) — dark color
        g2.setColor(BG);
        int ew = sw / 4;
        int eh = sh / 5;
        int ey = pad + sh / 3;
        g2.fillOval(pad + sw / 4 - ew / 2, ey, ew, eh);           // left eye
        g2.fillOval(pad + 3 * sw / 4 - ew / 2, ey, ew, eh);       // right eye
        // Mouth arc
        g2.setStroke(new BasicStroke(2));
        g2.setColor(BG);
        g2.drawArc(pad + sw / 4, ey + eh + 2, sw / 2, sh / 6, 0, -180);

        g2.dispose();
    }
}
