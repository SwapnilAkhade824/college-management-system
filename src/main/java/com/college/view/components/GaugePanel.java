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
    private boolean visible  = true;  // eye-toggle visibility
    
    private final FlatSVGIcon eyeShow   = new FlatSVGIcon("icons/eye_show.svg", 32, 24);
    private final FlatSVGIcon eyeHidden = new FlatSVGIcon("icons/eye_hidden.svg", 32, 24);
 
    public GaugePanel(String label, double maxValue) {
        this.label    = label;
        this.maxValue = maxValue;
        setOpaque(false);
        setPreferredSize(new Dimension(220, 200));
 
        // Click anywhere to toggle visibility
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Only toggle if click is near the eye icon (top-right area)
                int w = getWidth();
                if (e.getX() > w - 50 && e.getY() < 50) {
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
 
        // Balanced offsets for a spacious feel
        int cx = w / 2;
        int cy = h / 2;
        int dia = w - 46;
        int r = dia / 2;
        int ax = cx - r;
        int ay = cy - r;
 
        // Elegant colored arc segments (red → orange → green)
        g2.setStroke(new BasicStroke(14, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        int segments = 120;
        float sweep = 180f;
        float arcPerSeg = sweep / segments;
        
        for (int i = 0; i <= segments; i++) {
            float pct = (float) i / segments;
            g2.setColor(interpolateColor(pct));
            int startAngle = (int)(180 - i * arcPerSeg);
            g2.drawArc(ax, ay, dia, dia, startAngle, -(int)Math.ceil(arcPerSeg) - 1);
        }
 
        // Refined white dot indicator
        if (value >= 0) {
            double pct   = Math.min(1.0, value / maxValue);
            double angle = Math.toRadians(180 - 180 * pct);
            int ix = (int)(cx + (r) * Math.cos(angle));
            int iy = (int)(cy - (r) * Math.sin(angle));
            
            g2.setColor(Color.WHITE);
            g2.fillOval(ix - 10, iy - 10, 20, 20);
            g2.setColor(new Color(0xE0E0E0));
            g2.fillOval(ix - 6, iy - 6, 12, 12);
        }
 
        // Center value text
        g2.setFont(UITheme.bold(24f));
        g2.setColor(Color.BLACK);
        String valText = visible && value >= 0
            ? (maxValue == 100 ? String.format("%.0f", value) : String.format("%.2f", value))
            : "--";
        FontMetrics fm = g2.getFontMetrics();
        int vx = cx - fm.stringWidth(valText) / 2;
        int vy = cy - 14; 
        g2.drawString(valText, vx, vy);
 
        // Sub-label (CGPA)
        g2.setFont(UITheme.bold(16f));
        g2.setColor(Color.BLACK);
        FontMetrics sm = g2.getFontMetrics();
        g2.drawString(label, cx - sm.stringWidth(label) / 2, vy + 24);
 
        // Performance label - positioned with enough breathing room
        g2.setFont(UITheme.bold(24f));
        g2.setColor(Color.BLACK);
        FontMetrics pm = g2.getFontMetrics();
        g2.drawString("Performance", cx - pm.stringWidth("Performance") / 2, h - 35);
 
        // Eye icon — top right
        FlatSVGIcon icon = visible ? eyeShow : eyeHidden;
        icon.paintIcon(this, g2, w - 36, 16);
 
        // DEBUG BORDERS
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.YELLOW); g2.drawRect(0, 0, w-1, h-1); // Panel
        g2.setColor(Color.MAGENTA); g2.drawRect(ax, ay, dia, dia); // Arc Box
        g2.setColor(Color.RED);
        g2.drawRect(vx, vy - fm.getAscent(), fm.stringWidth(valText), fm.getHeight()); // Value
        g2.drawRect(cx - sm.stringWidth(label)/2, vy + 24 - sm.getAscent(), sm.stringWidth(label), sm.getHeight()); // CGPA
        g2.drawRect(cx - pm.stringWidth("Performance")/2, h - 35 - pm.getAscent(), pm.stringWidth("Performance"), pm.getHeight()); // Performance
        g2.setColor(Color.CYAN); 
        g2.drawRect(w - 50, 0, 50, 50); // Icon Area
 
        g2.dispose();
    }
 
    private Color interpolateColor(float t) {
        // Red (0.0) -> Orange (0.4) -> Yellow (0.6) -> Green (1.0)
        // Refined for a more vibrant, design-authentic look
        if (t < 0.4f) {
            float f = t / 0.4f;
            return new Color(255, (int)(140 * f), 30);
        } else if (t < 0.7f) {
            float f = (t - 0.4f) / 0.3f;
            return new Color((int)(255 - 100 * f), (int)(140 + 115 * f), 0);
        } else {
            float f = (t - 0.7f) / 0.3f;
            return new Color((int)(155 * (1 - f)), 255, (int)(80 * f));
        }
    }
}
