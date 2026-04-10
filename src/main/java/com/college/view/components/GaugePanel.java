package com.college.view.components;

import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;

/**
 * Speedometer-style gauge panel.
 * Shows colored arc from red→green, a white dot indicator, and center value text.
 * Matches the CGPA Performance and Attendance widgets in the Dashboard Figma design.
 */
public class GaugePanel extends JPanel {

    private double value    = -1;   // -1 = unknown ("--")
    private double maxValue = 10;   // 10 for CGPA, 100 for %
    private String label    = "";   // "CGPA" or "%"
    private boolean visible = true; // eye-toggle visibility

    public GaugePanel(String label, double maxValue) {
        this.label    = label;
        this.maxValue = maxValue;
        setOpaque(false);
        setPreferredSize(new Dimension(200, 180));
    }

    public void setValue(double v)       { this.value = v;       repaint(); }
    public void setValueVisible(boolean b){ this.visible = b;   repaint(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth(), h = getHeight();
        int dia = Math.min(w - 20, (int)(h * 1.3));
        int cx  = w / 2;
        int cy  = h / 2 + 10;
        int r   = dia / 2;

        int ax = cx - r, ay = cy - r;

        // Background arc (gray thin)
        g2.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(new Color(0xCCCCCC));
        g2.drawArc(ax, ay, dia, dia, 225, -270);

        // Colored arc segments (red → orange → green)
        int segments = 80;
        float arcPerSeg = 270f / segments;
        for (int i = 0; i < segments; i++) {
            float pct  = (float) i / segments;
            Color col  = interpolateColor(pct);
            g2.setColor(col);
            int startAngle = (int)(225 - i * arcPerSeg);
            g2.drawArc(ax, ay, dia, dia, startAngle, -(int)Math.ceil(arcPerSeg) - 1);
        }

        // White dot indicator
        if (value >= 0) {
            double pct    = Math.min(1.0, value / maxValue);
            double angle  = Math.toRadians(225 - 270 * pct);
            int ix = (int)(cx + (r - 4) * Math.cos(angle));
            int iy = (int)(cy - (r - 4) * Math.sin(angle));
            g2.setColor(Color.WHITE);
            g2.fillOval(ix - 7, iy - 7, 14, 14);
            g2.setColor(new Color(0x888888));
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawOval(ix - 7, iy - 7, 14, 14);
        }

        // Center value text
        g2.setFont(UITheme.bold(26f));
        g2.setColor(Constants.BLACK);
        String valText = visible && value >= 0
            ? (maxValue == 100 ? String.format("%.0f", value) : String.format("%.2f", value))
            : "--";
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(valText, cx - fm.stringWidth(valText) / 2, cy + fm.getAscent() / 2 - 4);

        // Label below value
        g2.setFont(UITheme.font(13f));
        g2.setColor(new Color(0x555555));
        FontMetrics sm = g2.getFontMetrics();
        String lbl = label;
        g2.drawString(lbl, cx - sm.stringWidth(lbl) / 2, cy + fm.getAscent() / 2 + 16);

        // Eye icon in top-right
        drawEye(g2, w - 32, 12, visible);

        g2.dispose();
    }

    private void drawEye(Graphics2D g2, int ex, int ey, boolean open) {
        g2.setColor(new Color(0x555555));
        g2.setStroke(new BasicStroke(1.5f));
        if (open) {
            g2.drawOval(ex, ey, 18, 12);
            g2.fillOval(ex + 5, ey + 3, 8, 6);
        } else {
            // eye with line through it
            g2.drawOval(ex, ey, 18, 12);
            g2.drawLine(ex - 2, ey + 14, ex + 20, ey - 2);
        }
    }

    private Color interpolateColor(float t) {
        // 0.0 = red, 0.5 = orange/yellow, 1.0 = green
        if (t < 0.5f) {
            float f = t / 0.5f;
            return new Color(255, (int)(165 * f), 0);
        } else {
            float f = (t - 0.5f) / 0.5f;
            return new Color((int)(255 * (1 - f)), (int)(165 + (35) * f), 0);
        }
    }
}
