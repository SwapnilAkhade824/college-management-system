package com.college.view.panels;

import com.college.core.NavigationManager;
import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;

/**
 * Loading screen: gray background + custom tick-spinner centered.
 * Auto-transitions to Login after 2.5s.
 * Uses a custom-painted spinner matching the reference design.
 */
public class LoadingPanel extends JPanel {

    public LoadingPanel() {
        setBackground(Constants.BG);
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0; gc.anchor = GridBagConstraints.CENTER;

        // Custom spinner
        SpinnerWidget spinner = new SpinnerWidget();
        gc.gridy = 0;
        add(spinner, gc);

        // Subtitle label
        JLabel lbl = new JLabel("College Management System");
        lbl.setFont(UITheme.font(14f));
        lbl.setForeground(new Color(0x777777));
        gc.gridy = 1; gc.insets = new Insets(20, 0, 0, 0);
        add(lbl, gc);

        // Auto-navigate after 2.5s
        Timer nav = new Timer(2500, e -> NavigationManager.getInstance().navigateTo(Constants.LOGIN));
        nav.setRepeats(false);
        nav.start();

        // Spin animation
        spinner.startSpin();
    }

    /** Custom circular tick-based spinner — matches reference screenshot. */
    private static class SpinnerWidget extends JPanel {
        private int angle = 0;
        private Timer spinTimer;

        SpinnerWidget() {
            setOpaque(false);
            setPreferredSize(new Dimension(80, 80));
        }

        void startSpin() {
            spinTimer = new Timer(40, e -> {
                angle = (angle + 12) % 360;
                repaint();
            });
            spinTimer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int cx = w / 2, cy = h / 2;
            int outerR = Math.min(w, h) / 2 - 4;
            int innerR = outerR - 9;
            int ticks  = 24;

            for (int i = 0; i < ticks; i++) {
                // Compute fade: the "angle" tick is brightest
                int diff = (i - angle / (360 / ticks) + ticks) % ticks;
                float alpha = 1.0f - (diff / (float) ticks);
                if (alpha < 0.12f) alpha = 0.12f;

                g2.setColor(new Color(0.53f, 0.53f, 0.53f, alpha));
                g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                double rad = Math.toRadians(i * (360.0 / ticks));
                int x1 = (int)(cx + innerR * Math.cos(rad));
                int y1 = (int)(cy + innerR * Math.sin(rad));
                int x2 = (int)(cx + outerR * Math.cos(rad));
                int y2 = (int)(cy + outerR * Math.sin(rad));
                g2.drawLine(x1, y1, x2, y2);
            }
            g2.dispose();
        }
    }
}
