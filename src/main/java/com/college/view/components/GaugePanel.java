package com.college.view.components;
 
import com.college.util.Constants;
import com.college.util.UITheme;
import com.formdev.flatlaf.extras.FlatSVGIcon;
 
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
    private String  footer   = "";    // "Performance" or "Attendance"
    private boolean visible  = true;  // eye-toggle visibility
    
    private final FlatSVGIcon eyeShow   = new FlatSVGIcon("icons/eye_show.svg", 25, 20);
    private final FlatSVGIcon eyeHidden = new FlatSVGIcon("icons/eye_hidden.svg", 28, 23);
 
    public GaugePanel(String label, String footer, double maxValue) {
        this.label    = label;
        this.footer   = footer;
        this.maxValue = maxValue;
        setOpaque(false);
        setPreferredSize(new Dimension(180, 190));
 
        // Click anywhere to toggle visibility
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Toggle if click is in top right
                if (e.getX() > getWidth() - 40 && e.getY() < 40) {
                    visible = !visible;
                    repaint();
                }
            }
        });
    }
 
    public void setValue(double v) { this.value = v; repaint(); }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
 
        int w = getWidth(), h = getHeight();
        int cx = w / 2;
        int dia = w - 40;
        int r = dia / 2;
        int ax = cx - r;
        int ay = 30; // Push arc up slightly
 
        // Arc segments (Smooth Red -> Green)
        g2.setStroke(new BasicStroke(16, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        int segments = 90;
        float sweep = 180f;
        float arcPerSeg = sweep / segments;
        
        for (int i = 0; i < segments; i++) {
            float pct = (float) i / segments;
            g2.setColor(interpolateColor(pct));
            int startAngle = (int)(180 - i * arcPerSeg);
            g2.drawArc(ax, ay, dia, dia, startAngle, -(int)Math.ceil(arcPerSeg));
        }
 
        // Indicator Dot
        if (value >= 0) {
            double pct   = Math.min(1.0, value / maxValue);
            double angle = Math.toRadians(180 - 180 * pct);
            int ix = (int)(cx + r * Math.cos(angle));
            int iy = (int)(ay + r - r * Math.sin(angle));
            
            g2.setColor(Color.WHITE);
            g2.fillOval(ix - 10, iy - 10, 20, 20);
            g2.setColor(new Color(0xE0E0E0));
            g2.fillOval(ix - 6, iy - 6, 12, 12);
        }
 
        // Center Value
        g2.setFont(UITheme.bold(26f));
        g2.setColor(Color.BLACK);
        String valText = visible && value >= 0
            ? (maxValue == 100 ? String.format("%.0f", value) : String.format("%.2f", value))
            : "--";
        if ("--".equals(valText)) g2.setFont(UITheme.bold(30f));
        
        FontMetrics fm = g2.getFontMetrics();
        int ty = ay + r -10;
        g2.drawString(valText, cx - fm.stringWidth(valText) / 2, ty);
 
        // Center Sub-label (CGPA/%)
        g2.setFont(UITheme.bold(14f));
        FontMetrics sm = g2.getFontMetrics();
        g2.drawString(label, cx - sm.stringWidth(label) / 2, ty + 18);
 
        // Footer Label (Performance/Attendance)
        g2.setFont(UITheme.bold(22f));
        FontMetrics pm = g2.getFontMetrics();
        g2.drawString(footer, cx - pm.stringWidth(footer) / 2, h - 20);
 
        // Eye Icon
        FlatSVGIcon icon = visible ? eyeShow : eyeHidden;
        icon.paintIcon(this, g2, w - 30, 10);
 
        g2.dispose();
    }
 
    private Color interpolateColor(float t) {
        // Red (0.0) -> Yellow (0.5) -> Green (1.0)
        int r, g, b = 0;
        if (t < 0.5f) {
            r = 255;
            g = (int)(255 * (t / 0.5f));
        } else {
            r = (int)(255 * (1.0f - (t - 0.5f) / 0.5f));
            g = 255;
        }
        return new Color(r, g, b);
    }
}
