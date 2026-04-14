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
    private static final Color FG   = Color.WHITE;
    static final int SIZE = 56;

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

        // Mask icon — shield shape (rounded rect)
        g2.setColor(FG);
        int pad = (int)(w * 0.18);
        int sw  = w - 2 * pad;
        int sh  = h - 2 * pad;
        int arcR = sw / 3;
        g2.fillRoundRect(pad, pad, sw, sh, arcR, arcR);

        // Eye slit cutouts (magenta, so they become "holes")
        g2.setColor(BG);
        int ew = sw / 5;
        int eh = sh / 6;
        int eyY = pad + sh * 2 / 5;
        // Left eye
        g2.fillOval(pad + sw / 4 - ew / 2, eyY, ew, eh);
        // Right eye
        g2.fillOval(pad + 3 * sw / 4 - ew / 2, eyY, ew, eh);

        // Mouth arc (open downward like a wink)
        g2.setStroke(new BasicStroke(2.5f));
        int mouthY = eyY + eh + (int)(sh * 0.10);
        int mouthW = sw / 2;
        int mouthH = sh / 7;
        g2.drawArc(pad + sw / 4, mouthY, mouthW, mouthH, 0, -180);

        g2.dispose();
    }
}
