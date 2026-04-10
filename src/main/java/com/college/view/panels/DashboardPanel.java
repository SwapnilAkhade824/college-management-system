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
    private final GaugePanel          cgpa   = new GaugePanel("CGPA",   10);
    private final GaugePanel          att    = new GaugePanel("% Att.", 100);
    private final JPanel              notifList; // mini notification area
    private final JLabel              notifCount;
    private final DashboardController ctrl   = new DashboardController();

    public DashboardPanel() {
        setBackground(Constants.BG);
        setLayout(new BorderLayout());

        // Topbar
        add(topbar, BorderLayout.NORTH);

        // Body
        JPanel body = new JPanel(new GridBagLayout());
        body.setBackground(Constants.BG);
        body.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill    = GridBagConstraints.BOTH;
        gc.insets  = new Insets(6, 6, 6, 6);

        // ── Row 0: CGPA | Attendance | Notification mini panel ──────────
        // CGPA card
        gc.gridx = 0; gc.gridy = 0; gc.weightx = 0.22; gc.weighty = 0.45;
        body.add(wrapGauge(cgpa, "Performance"), gc);

        // Attendance card
        gc.gridx = 1; gc.weightx = 0.22;
        body.add(wrapGauge(att, "Attendance"), gc);

        // Mini Notification card
        gc.gridx = 2; gc.weightx = 0.56;
        notifList  = new JPanel();
        notifList.setOpaque(false);
        notifList.setLayout(new BoxLayout(notifList, BoxLayout.Y_AXIS));
        notifCount = new JLabel("0");
        body.add(buildMiniNotif(), gc);

        // ── Row 1: Detail | Attendance nav cards ────────────────────────
        gc.gridy = 1; gc.weighty = 0.28;

        gc.gridx = 0; gc.weightx = 0.5; gc.gridwidth = 3;
        JPanel row1 = new JPanel(new GridLayout(1, 2, 12, 0));
        row1.setOpaque(false);
        row1.add(navCard("Details",    Constants.DETAILS));
        row1.add(navCard("Attendance", Constants.ATTENDANCE));
        body.add(row1, gc);

        // ── Row 2: Payments | Timetable ──────────────────────────────
        gc.gridy = 2; gc.gridx = 0; gc.gridwidth = 3;
        JPanel row2 = new JPanel(new GridLayout(1, 2, 12, 0));
        row2.setOpaque(false);
        row2.add(navCard("Payments",  Constants.PAYMENT));
        row2.add(navCard("Timetable", Constants.TIMETABLE));
        body.add(row2, gc);

        add(body, BorderLayout.CENTER);
    }

    private JPanel wrapGauge(GaugePanel gauge, String title) {
        RoundedPanel card = new RoundedPanel(Constants.CARD_COLOR, Constants.CARD_ARC);
        card.setLayout(new BorderLayout(0, 4));
        card.setBorder(BorderFactory.createEmptyBorder(12, 8, 12, 8));

        JLabel lbl = new JLabel(title, SwingConstants.CENTER);
        lbl.setFont(UITheme.bold(15f));
        card.add(lbl, BorderLayout.NORTH);
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
        JLabel title = new JLabel("Notification/Alert");
        title.setFont(UITheme.bold(17f));
        left.add(title);

        // Red count badge
        JLabel badge = new JLabel("0") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Constants.RED);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.setColor(Color.WHITE);
                g2.setFont(UITheme.bold(12f));
                FontMetrics fm = g2.getFontMetrics();
                String t = getText();
                g2.drawString(t, (getWidth()-fm.stringWidth(t))/2, (getHeight()+fm.getAscent()-fm.getDescent())/2);
                g2.dispose();
            }
        };
        badge.setPreferredSize(new Dimension(24, 24));
        badge.setOpaque(false);
        left.add(badge);
        notifCount.setText("0"); // keep ref

        hdr.add(left, BorderLayout.WEST);

        // Expand button
        JButton expand = new JButton("⬌");
        expand.setFont(UITheme.bold(16f)); expand.setFocusPainted(false);
        expand.setContentAreaFilled(false); expand.setBorderPainted(false);
        expand.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        expand.addActionListener(e -> NavigationManager.getInstance().navigateTo(Constants.NOTIFICATION));
        hdr.add(expand, BorderLayout.EAST);

        card.add(hdr, BorderLayout.NORTH);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(0xAAAAAA));
        card.add(sep, BorderLayout.CENTER);

        // Notification rows container
        notifList.removeAll();
        card.add(notifList, BorderLayout.SOUTH);

        badge.setText("");
        return card;
    }

    private JPanel navCard(String label, String card) {
        RoundedPanel p = new RoundedPanel(Constants.CARD_COLOR, Constants.CARD_ARC);
        p.setLayout(new BorderLayout());
        p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel lbl = new JLabel(label, SwingConstants.CENTER);
        lbl.setFont(UITheme.bold(26f));
        lbl.setForeground(Color.BLACK);
        p.add(lbl, BorderLayout.CENTER);

        // Click handler
        p.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                NavigationManager.getInstance().navigateTo(card);
            }
            @Override public void mouseEntered(java.awt.event.MouseEvent e) {
                p.setBgColor(new Color(0xB8B8BA));
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

        // Mini notification list
        notifList.removeAll();
        List<Notification> recent = ctrl.getRecentNotifications(3);
        for (Notification n : recent) {
            JPanel row = new JPanel(new BorderLayout(8, 0));
            row.setOpaque(false);
            row.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
            JLabel icon = new JLabel("ALERT".equals(n.getType()) ? "⚠" : "📢");
            icon.setFont(UITheme.font(16f));
            JLabel msg  = new JLabel(n.getMessage().length() > 55
                    ? n.getMessage().substring(0, 52) + "..." : n.getMessage());
            msg.setFont(UITheme.font(13f));
            row.add(icon, BorderLayout.WEST);
            row.add(msg,  BorderLayout.CENTER);
            notifList.add(row);
        }
        notifList.revalidate(); notifList.repaint();
        revalidate(); repaint();
    }
}
