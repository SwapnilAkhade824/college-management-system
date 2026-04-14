package com.college.view.panels;
 
import com.college.controller.DashboardController;
import com.college.core.NavigationManager;
import com.college.core.Refreshable;
import com.college.model.system.Notification;
import com.college.util.Constants;
import com.college.util.UITheme;
import com.college.view.components.*;
 
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;
 
/**
 * Dashboard — main hub screen:
 * Topbar → CGPA gauge | Attendance gauge | Mini Notification panel
 *        → 4 nav buttons: Details, Attendance, Payments, Timetable
 */
public class DashboardPanel extends JPanel implements Refreshable {
 
    private final Topbar              topbar = new Topbar();
    private final GaugePanel          cgpa   = new GaugePanel("CGPA", "Performance", 10);
    private final GaugePanel          att    = new GaugePanel("%",    "Attendance", 100);
    private final JPanel              notifList;
    private final JLabel              notifBadge;
    private final DashboardController ctrl   = new DashboardController();
 
    public DashboardPanel() {
        setBackground(Constants.BG);
        setLayout(new BorderLayout());
 
        add(topbar, BorderLayout.NORTH);
 
        JPanel body = new JPanel(new GridBagLayout());
        body.setBackground(Constants.BG);
        body.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
 
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill   = GridBagConstraints.BOTH;
        gc.insets = new Insets(10, 10, 10, 10);
 
        // ── Row 0: CGPA | Attendance | Notification mini panel ──────────
        gc.gridy = 0; gc.weighty = 0.40;
        gc.insets = new Insets(40, 8, 10, 8); // Extra top gap from header line
 
        gc.gridx = 0; gc.weightx = 0.25;
        body.add(wrapGauge(cgpa), gc);
 
        gc.gridx = 1; gc.weightx = 0.25;
        body.add(wrapGauge(att), gc);
 
        notifList  = new JPanel();
        notifList.setOpaque(false);
        notifList.setLayout(new BoxLayout(notifList, BoxLayout.Y_AXIS));
        notifBadge = new JLabel("0");
 
        gc.gridx = 2; gc.weightx = 0.50;
        body.add(buildMiniNotif(), gc);
 
        // ── Row 1: Details | Attendance ────────────────────────────────
        gc.gridy = 1; gc.weighty = 0.30;
        gc.gridx = 0; gc.weightx = 1.0; gc.gridwidth = 3;
        gc.insets = new Insets(35, 8, 10, 8); // Large gap between widgets and buttons
        JPanel row1 = new JPanel(new GridLayout(1, 2, 20, 0));
        row1.setOpaque(false);
        row1.add(navCard("Details",    Constants.DETAILS));
        row1.add(navCard("Attendance", Constants.ATTENDANCE));
        body.add(row1, gc);
 
        // ── Row 2: Payments | Timetable ──────────────────────────────
        gc.gridy = 2; gc.gridx = 0; gc.gridwidth = 3;
        gc.insets = new Insets(10, 8, 10, 8);
        JPanel row2 = new JPanel(new GridLayout(1, 2, 20, 0));
        row2.setOpaque(false);
        row2.add(navCard("Payments",  Constants.PAYMENT));
        row2.add(navCard("Timetable", Constants.TIMETABLE));
        body.add(row2, gc);
 
        add(body, BorderLayout.CENTER);
    }
 
    private JPanel wrapGauge(GaugePanel gauge) {
        RoundedPanel card = new RoundedPanel(Constants.CARD_COLOR, Constants.CARD_ARC);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        card.add(gauge, BorderLayout.CENTER);
        return card;
    }
 
    private JPanel buildMiniNotif() {
        RoundedPanel card = new RoundedPanel(Constants.CARD_COLOR, Constants.CARD_ARC);
        card.setLayout(new BorderLayout(0, 6));
        card.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
 
        // Header row: title + count badge + expand button
        JPanel hdr = new JPanel(new BorderLayout());
        hdr.setOpaque(false);
 
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        left.setOpaque(false);
        JLabel titleLbl = new JLabel("Notification/Alert");
        titleLbl.setFont(UITheme.bold(16f));
        titleLbl.setForeground(Color.BLACK);
        left.add(titleLbl);
 
        // Red count badge
        JLabel badge = new JLabel("0") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Constants.RED);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.setColor(Color.WHITE);
                g2.setFont(UITheme.bold(11f));
                FontMetrics fm = g2.getFontMetrics();
                String t = getText();
                g2.drawString(t, (getWidth()-fm.stringWidth(t))/2, (getHeight()+fm.getAscent()-fm.getDescent())/2);
                g2.dispose();
            }
        };
        badge.setPreferredSize(new Dimension(22, 22));
        badge.setOpaque(false);
        left.add(badge);
 
        hdr.add(left, BorderLayout.WEST);
 
        // Expand button
        JButton expand = new JButton(new com.formdev.flatlaf.extras.FlatSVGIcon("icons/expand.svg", 20, 20));
        expand.setFont(UITheme.bold(20f));
        expand.setFocusPainted(false);
        expand.setContentAreaFilled(false);
        expand.setBorderPainted(false);
        expand.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        expand.setForeground(Color.BLACK);
        expand.addActionListener(e -> NavigationManager.getInstance().navigateTo(Constants.NOTIFICATION));
        hdr.add(expand, BorderLayout.EAST);
 
        card.add(hdr, BorderLayout.NORTH);
 
        JPanel sep = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), 3);
            }
        };
        sep.setPreferredSize(new Dimension(0, 3));
        sep.setOpaque(false);
        card.add(sep, BorderLayout.CENTER);
 
        notifList.removeAll();
        card.add(notifList, BorderLayout.SOUTH);
 
        // Sync internal badge reference
        notifBadge.setText("0");
        // Keep badge in sync on refresh via stash
        badge.setText(notifBadge.getText());
 
        return card;
    }
 
    private JPanel navCard(String label, String card) {
        RoundedPanel p = new RoundedPanel(Constants.CARD_COLOR, Constants.CARD_ARC);
        p.setPreferredSize(new Dimension(0, 100)); // Ensure tall buttons
        p.setLayout(new BorderLayout());
        p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
 
        JLabel lbl = new JLabel(label, SwingConstants.CENTER);
        lbl.setFont(UITheme.bold(26f));
        lbl.setForeground(Color.BLACK);
        p.add(lbl, BorderLayout.CENTER);
 
        p.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                NavigationManager.getInstance().navigateTo(card);
            }
            @Override public void mouseEntered(java.awt.event.MouseEvent e) {
                p.setBgColor(new Color(0xB5B5B7));
            }
            @Override public void mouseExited(java.awt.event.MouseEvent e) {
                p.setBgColor(Constants.CARD_COLOR);
            }
        });
        return p;
    }
 
    @Override
    public void refresh() {
        topbar.setName(ctrl.getWelcomeName());
        cgpa.setValue(ctrl.getCGPA());
        att.setValue(ctrl.getAttendancePct());
 
        notifList.removeAll();
        List<Notification> recent = ctrl.getRecentNotifications(3);
        int i = 0;
        for (Notification n : recent) {
            JPanel item = new JPanel(new BorderLayout(12, 0));
            item.setOpaque(false);
            item.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
            
            // Icon: alert.svg for alerts, notification_date.svg for others
            String iconPath = "ALERT".equals(n.getType()) ? "icons/alert.svg" : "icons/notification_date.svg";
            JLabel iconLabel = new JLabel(new com.formdev.flatlaf.extras.FlatSVGIcon(iconPath, 36, 36));
            item.add(iconLabel, BorderLayout.WEST);
 
            // Message: real text with truncation
            String rawMsg = n.getMessage();
            String truncated = rawMsg.length() > 55 ? rawMsg.substring(0, 52) + "..." : rawMsg;
            JLabel msgLbl = new JLabel(truncated);
            msgLbl.setFont(UITheme.font(14f));
            msgLbl.setForeground(Color.BLACK);
            item.add(msgLbl, BorderLayout.CENTER);
 
            notifList.add(item);
 
            // Dashed Separator
            final boolean isLast = (++i == recent.size());
            if (!isLast) {
                JPanel dashedSep = new JPanel() {
                    @Override protected void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(Color.BLACK);
                        float[] dash = {4f, 4f};
                        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash, 0));
                        g2.drawLine(0, 0, getWidth(), 0);
                        g2.dispose();
                    }
                };
                dashedSep.setPreferredSize(new Dimension(0, 1));
                dashedSep.setOpaque(false);
                notifList.add(dashedSep);
            }
        }
        notifList.revalidate();
        notifList.repaint();
        revalidate();
        repaint();
    }
}
