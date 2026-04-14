package com.college.view.panels;

import com.college.controller.NotificationController;
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
 * Full Notification / Alert screen — expands from Dashboard mini panel.
 * Header: "Notification/Alert [N]" + Mark All Read + Filter + Back buttons.
 * Body: scrollable list of NotificationRow items.
 */
public class NotificationPanel extends JPanel implements Refreshable {

    private final NotificationController ctrl    = new NotificationController();
    private final Topbar                 topbar  = new Topbar();
    private final BadgeLabel             badge   = new BadgeLabel();
    private final JPanel                 listPanel = new JPanel();
    private boolean showNotif  = true;
    private boolean showAlerts = true;

    public NotificationPanel() {
        setBackground(Constants.BG);
        setLayout(new BorderLayout());
        add(topbar, BorderLayout.NORTH);
        add(buildBody(), BorderLayout.CENTER);
    }

    private JPanel buildBody() {
        JPanel outer = new JPanel(new BorderLayout());
        outer.setBackground(Constants.BG);

        // Sub-header
        JPanel sub = new JPanel(new BorderLayout());
        sub.setBackground(Constants.BG);
        sub.setPreferredSize(new Dimension(0, Constants.SUB_H));
        sub.setBorder(new MatteBorder(0, 0, Constants.STROKE, 0, Color.BLACK));

        // Left: title + badge
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 14));
        left.setOpaque(false);
        JLabel title = new JLabel("Notification/Alert");
        title.setFont(UITheme.bold(24f));
        title.setForeground(Color.BLACK);
        left.add(title);
        left.add(badge);
        sub.add(left, BorderLayout.WEST);

        // Right: action buttons
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 11));
        right.setOpaque(false);

        JButton markAll = iconBtn("✓✓");
        markAll.addActionListener(e -> { ctrl.markAllRead(); refresh(); });

        JButton filterBtn = iconBtn("⊞");
        FilterPopup fp = new FilterPopup("Notifications", "Alerts", null,
            (opt, combo) -> {
                if (opt == null) { showNotif = showAlerts = true; }
                else { showNotif = opt.contains("Notif"); showAlerts = !showNotif; }
                loadList();
            });
        filterBtn.addActionListener(e -> {
            JPopupMenu pm = new JPopupMenu();
            pm.setLayout(new BorderLayout());
            pm.add(fp, BorderLayout.CENTER);
            pm.show(filterBtn, 0, filterBtn.getHeight());
        });

        BackButton back = new BackButton();
        back.addActionListener(e -> NavigationManager.getInstance().navigateBack());

        right.add(markAll);
        right.add(filterBtn);
        right.add(back);
        sub.add(right, BorderLayout.EAST);
        outer.add(sub, BorderLayout.NORTH);

        // List
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Constants.BG);

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        scroll.getViewport().setBackground(Constants.BG);
        scroll.getVerticalScrollBar().setUnitIncrement(14);
        outer.add(scroll, BorderLayout.CENTER);
        return outer;
    }

    private void loadList() {
        listPanel.removeAll();
        List<Notification> all = ctrl.getAll();
        for (Notification n : all) {
            if (!showNotif && "NOTIFICATION".equals(n.getType())) continue;
            if (!showAlerts && "ALERT".equals(n.getType()))        continue;
            listPanel.add(new NotificationRow(n));
            JSeparator sep = new JSeparator();
            sep.setForeground(new Color(0xBBBBBB));
            listPanel.add(sep);
        }
        listPanel.revalidate();
        listPanel.repaint();
        badge.setCount(ctrl.getUnreadCount());
    }

    private JButton iconBtn(String icon) {
        JButton b = new JButton(icon);
        b.setFont(UITheme.bold(16f));
        b.setBackground(Constants.CARD_COLOR);
        b.setForeground(Color.BLACK);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(new Color(0x999999), 1, true));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(44, 44));
        return b;
    }

    @Override
    public void refresh() {
        topbar.setName(new com.college.controller.DashboardController().getWelcomeName());
        showNotif = showAlerts = true;
        loadList();
    }

    /** Self-contained red circular badge label. */
    static class BadgeLabel extends JLabel {
        private int count = 0;

        BadgeLabel() {
            setOpaque(false);
            setPreferredSize(new Dimension(26, 26));
        }

        void setCount(int n) {
            this.count = n;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Constants.RED);
            g2.fillOval(0, 0, getWidth(), getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(UITheme.bold(11f));
            FontMetrics fm = g2.getFontMetrics();
            String t = String.valueOf(count);
            int tx = (getWidth()  - fm.stringWidth(t)) / 2;
            int ty = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(t, tx, ty);
            g2.dispose();
        }
    }
}
