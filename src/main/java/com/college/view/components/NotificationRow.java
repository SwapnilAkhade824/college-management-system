package com.college.view.components;

import com.college.model.system.Notification;
import com.college.util.Constants;
import com.college.util.DateUtil;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;

/**
 * A single row in the notification list.
 * Left: date badge (blue date/month) OR red alert icon.
 * Right: gray rounded message area.
 */
public class NotificationRow extends JPanel {

    public NotificationRow(Notification n) {
        setOpaque(false);
        setLayout(new BorderLayout(10, 0));
        setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        // Left badge
        JPanel badge = makeBadge(n);
        badge.setPreferredSize(new Dimension(56, 56));
        add(badge, BorderLayout.WEST);

        // Message area
        RoundedPanel msgPanel = new RoundedPanel(new Color(0xCCCCCC), 14);
        msgPanel.setLayout(new BorderLayout());
        msgPanel.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));

        JLabel msg = new JLabel("<html><body style='width:100%;'>" + n.getMessage() + "</body></html>");
        msg.setFont(UITheme.font(13f));
        msg.setForeground(n.isRead() ? new Color(0x555555) : Color.BLACK);
        msgPanel.add(msg, BorderLayout.CENTER);
        add(msgPanel, BorderLayout.CENTER);
    }

    private JPanel makeBadge(Notification n) {
        JPanel badge = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if ("ALERT".equals(n.getType())) {
                    // Red circle with !
                    g2.setColor(new Color(0xFF3333));
                    g2.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
                    g2.setColor(Color.WHITE);
                    g2.setFont(UITheme.bold(22f));
                    FontMetrics fm = g2.getFontMetrics();
                    g2.drawString("!", (getWidth() - fm.stringWidth("!")) / 2,
                            (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
                } else {
                    // White rounded square with blue date
                    g2.setColor(Color.WHITE);
                    g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 12, 12);
                    // Day number
                    g2.setColor(Constants.BLUE);
                    g2.setFont(UITheme.bold(17f));
                    String day = DateUtil.badgeDay(n.getCreatedAt());
                    FontMetrics fm = g2.getFontMetrics();
                    g2.drawString(day, (getWidth() - fm.stringWidth(day)) / 2, getHeight() / 2 + 1);
                    // Month label
                    g2.setFont(UITheme.font(11f));
                    String mon = DateUtil.badgeMonth(n.getCreatedAt());
                    FontMetrics sm = g2.getFontMetrics();
                    g2.drawString(mon, (getWidth() - sm.stringWidth(mon)) / 2,
                            getHeight() / 2 + 15);
                }
                g2.dispose();
                super.paintComponent(g);
            }
        };
        badge.setOpaque(false);
        return badge;
    }
}
