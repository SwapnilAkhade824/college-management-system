package com.college.view.panels;

import com.college.controller.TimetableController;
import com.college.core.NavigationManager;
import com.college.core.Refreshable;
import com.college.util.Constants;
import com.college.util.DateUtil;
import com.college.util.UITheme;
import com.college.view.components.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Timetable screen: Today/Full toggle button, StyledTable.
 */
public class TimetablePanel extends JPanel implements Refreshable {

    private final TimetableController ctrl    = new TimetableController();
    private final Topbar              topbar  = new Topbar();
    private       StyledTable         table;
    private       boolean             todayMode = true;
    private       JButton             toggleBtn;

    public TimetablePanel() {
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

        JLabel title = new JLabel("Timetable:");
        title.setFont(UITheme.bold(28f));
        title.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));
        sub.add(title, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        right.setOpaque(false);

        // Toggle: Today / Full
        toggleBtn = new JButton("Today");
        toggleBtn.setFont(UITheme.bold(15f));
        toggleBtn.setBackground(Constants.CARD_COLOR);
        toggleBtn.setBorder(BorderFactory.createLineBorder(new Color(0x999999), 1, true));
        toggleBtn.setFocusPainted(false);
        toggleBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BackButton back = new BackButton();
        back.addActionListener(e -> NavigationManager.getInstance().navigateBack());

        right.add(toggleBtn);
        right.add(back);
        sub.add(right, BorderLayout.EAST);
        outer.add(sub, BorderLayout.NORTH);

        // Table
        table = new StyledTable(ctrl.getColumns(), ctrl.getTodayTimetable());
        table.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));
        outer.add(table, BorderLayout.CENTER);

        toggleBtn.addActionListener(e -> {
            todayMode = !todayMode;
            toggleBtn.setText(todayMode ? "Today" : "Full");
            if (todayMode) {
                table.setData(ctrl.getColumns(), ctrl.getTodayTimetable());
            } else {
                table.setData(ctrl.getColumns(), ctrl.getFullTimetable());
            }
        });

        return outer;
    }

    @Override
    public void refresh() {
        topbar.setName(new com.college.controller.DashboardController().getWelcomeName());
        todayMode = true;
        toggleBtn.setText("Today");
        table.setData(ctrl.getColumns(), ctrl.getTodayTimetable());
    }
}
