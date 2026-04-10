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
 * Header: "Notification/Alert [N]" + Mark All Read + Filter + Collapse buttons.
 * Body: scrollable list of NotificationRow items.
 */
public class NotificationPanel extends JPanel implements Refreshable {

    private final NotificationController ctrl    = new NotificationController();
    private final Topbar                 topbar  = new Topbar();
    private final JLabel                 cntBadge = new JLabel("0");
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

        // Left: title + count
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 12));
        left.setOpaque(false);
        JLabel title = new JLabel("Notification/Alert");
        title.setFont(UITheme.bold(26f));
        left.add(title);

        cntBadge.setOpaque(false);
        cntBadge.setPreferredSize(new Dimension(28, 28));
        left.add(new BadgeLabel(cntBadge));
        sub.add(left, BorderLayout.WEST);

        // Right: action buttons
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        right.setOpaque(false);

        // Mark all read
        JButton markAll = iconBtn("✓✓");
        markAll.addActionListener(e -> { ctrl.markAllRead(); refresh(); });

        // Filter
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

        // Collapse / back
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
        scroll.getVerticalScrollBar().setUnitIncrement(12);

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
            listPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }
        listPanel.revalidate(); listPanel.repaint();
        cntBadge.setText(String.valueOf(ctrl.getUnreadCount()));
    }

    private JButton iconBtn(String icon) {
        JButton b = new JButton(icon);
        b.setFont(UITheme.bold(17f));
        b.setBackground(Constants.CARD_COLOR);
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

    // Small label wrapper for badge
    private static class BadgeLabel extends JLabel {
        private final JLabel inner;
        BadgeLabel(JLabel inner) {
            this.inner = inner;
            setOpaque(false);
            setPreferredSize(new Dimension(28, 28));
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Constants.RED);
            g2.fillOval(0, 0, getWidth(), getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(UITheme.bold(12f));
            FontMetrics fm = g2.getFontMetrics();
            String t = inner.getText();
            g2.drawString(t, (getWidth()-fm.stringWidth(t))/2, (getHeight()+fm.getAscent()-fm.getDescent())/2);
            g2.dispose();
        }
    }
}
