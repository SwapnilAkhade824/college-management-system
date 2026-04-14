package com.college.view.components;

import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;

/**
 * Speedometer-style gauge panel.
 * Shows colored arc from red→green, a white dot indicator, and center value text.
 * Matches the CGPA Performance and Attendance widgets in the Dashboard Figma design.
 * The eye icon is clickable to toggle value visibility.
 */
public class GaugePanel extends JPanel {

    private double  value    = -1;    // -1 = unknown ("--")
    private double  maxValue = 10;    // 10 for CGPA, 100 for %
    private String  label    = "";    // "CGPA" or "%"
    private boolean visible  = true;  // eye-toggle visibility

    public GaugePanel(String label, double maxValue) {
        this.label    = label;
        this.maxValue = maxValue;
        setOpaque(false);
        setPreferredSize(new Dimension(200, 180));

        // Click anywhere to toggle visibility
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Only toggle if click is near the eye icon (top-right area)
                int w = getWidth();
                if (e.getX() > w - 50 && e.getY() < 40) {
                    visible = !visible;
                    repaint();
                }
            }
        });
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void setValue(double v) { this.value = v; repaint(); }
    public void setValueVisible(boolean b) { this.visible = b; repaint(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int w = getWidth(), h = getHeight();

        // Reserve bottom 30px for label — arc occupies the top portion
        int arcAreaH = h - 30;
        int dia = Math.min(w - 24, (int)(arcAreaH * 1.6));
        int cx  = w / 2;
        // Center the arc vertically within the arc area, slightly shifted up
        // so the flat side of the semicircle is at the bottom of arcAreaH
        int cy  = arcAreaH - dia / 2 + 10;

        int r   = dia / 2;
        int ax  = cx - r;
        int ay  = cy - r;

        // Background arc (gray thin)
        g2.setStroke(new BasicStroke(9, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(new Color(0xCCCCCC));
        g2.drawArc(ax, ay, dia, dia, 225, -270);

        // Colored arc segments (red → orange → green)
        int segments = 90;
        float arcPerSeg = 270f / segments;
        for (int i = 0; i < segments; i++) {
            float pct = (float) i / segments;
            g2.setColor(interpolateColor(pct));
            int startAngle = (int)(225 - i * arcPerSeg);
            g2.drawArc(ax, ay, dia, dia, startAngle, -(int)Math.ceil(arcPerSeg) - 1);
        }

        // White dot indicator
        if (value >= 0) {
            double pct   = Math.min(1.0, value / maxValue);
            double angle = Math.toRadians(225 - 270 * pct);
            int ix = (int)(cx + (r - 5) * Math.cos(angle));
            int iy = (int)(cy - (r - 5) * Math.sin(angle));
            g2.setStroke(new BasicStroke(1.5f));
            g2.setColor(Color.WHITE);
            g2.fillOval(ix - 7, iy - 7, 14, 14);
            g2.setColor(new Color(0x888888));
            g2.drawOval(ix - 7, iy - 7, 14, 14);
        }

        // Center value text
        g2.setStroke(new BasicStroke(1));
        g2.setFont(UITheme.bold(24f));
        g2.setColor(Constants.BLACK);
        String valText = visible && value >= 0
            ? (maxValue == 100 ? String.format("%.0f", value) : String.format("%.2f", value))
            : "--";
        FontMetrics fm = g2.getFontMetrics();
        // Draw value centered horizontally, vertically centered in the arc area
        int vx = cx - fm.stringWidth(valText) / 2;
        int vy = cy + fm.getAscent() / 2 - 6;
        g2.drawString(valText, vx, vy);

        // Sub-label (CGPA / %)
        g2.setFont(UITheme.font(12f));
        g2.setColor(new Color(0x555555));
        FontMetrics sm = g2.getFontMetrics();
        g2.drawString(label, cx - sm.stringWidth(label) / 2, vy + sm.getHeight());

        // Eye icon — top right, safely positioned
        drawEye(g2, w - 36, 10, visible);

        g2.dispose();
    }

    private void drawEye(Graphics2D g2, int ex, int ey, boolean open) {
        g2.setColor(new Color(0x555555));
        g2.setStroke(new BasicStroke(1.5f));
        if (open) {
            g2.drawOval(ex, ey, 20, 13);
            g2.fillOval(ex + 6, ey + 3, 8, 7);
        } else {
            g2.drawOval(ex, ey, 20, 13);
            g2.setStroke(new BasicStroke(2f));
            g2.drawLine(ex - 2, ey + 15, ex + 22, ey - 2);
        }
    }

    private Color interpolateColor(float t) {
        if (t < 0.5f) {
            float f = t / 0.5f;
            return new Color(255, (int)(165 * f), 0);
        } else {
            float f = (t - 0.5f) / 0.5f;
            return new Color((int)(255 * (1 - f)), (int)(165 + 35 * f), 0);
        }
    }
}
