package com.college.view.panels;

import com.college.controller.PaymentController;
import com.college.core.NavigationManager;
import com.college.core.Refreshable;
import com.college.util.Constants;
import com.college.util.UITheme;
import com.college.view.components.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Payments screen: Pending/Completed filter, Select Type dropdown, BackButton, StyledTable.
 */
public class PaymentPanel extends JPanel implements Refreshable {

    private final PaymentController ctrl   = new PaymentController();
    private final Topbar            topbar = new Topbar();
    private       StyledTable       table;
    private String filterStatus = null, filterMode = null;

    public PaymentPanel() {
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

        JLabel title = new JLabel("Payments:");
        title.setFont(UITheme.bold(28f));
        title.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));
        sub.add(title, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        right.setOpaque(false);

        JButton filterBtn = new JButton("⊞ Filter");
        filterBtn.setFont(UITheme.bold(15f));
        filterBtn.setBackground(Constants.CARD_COLOR);
        filterBtn.setBorder(BorderFactory.createLineBorder(new Color(0x999999), 1, true));
        filterBtn.setFocusPainted(false);
        filterBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BackButton back = new BackButton();
        back.addActionListener(e -> NavigationManager.getInstance().navigateBack());

        right.add(filterBtn);
        right.add(back);
        sub.add(right, BorderLayout.EAST);
        outer.add(sub, BorderLayout.NORTH);

        // Table
        table = new StyledTable(ctrl.getColumns(), ctrl.getPaymentData(null, null));
        table.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));
        outer.add(table, BorderLayout.CENTER);

        // Filter popup
        FilterPopup filterPopup = new FilterPopup(
            "PENDING", "PAID", ctrl.getPaymentModes(),
            (status, mode) -> {
                filterStatus = status;
                filterMode   = mode;
                table.setData(ctrl.getColumns(), ctrl.getPaymentData(filterStatus, filterMode));
            });

        filterBtn.addActionListener(e -> {
            JPopupMenu pm = new JPopupMenu();
            pm.setLayout(new BorderLayout());
            pm.add(filterPopup, BorderLayout.CENTER);
            pm.show(filterBtn, 0, filterBtn.getHeight());
        });

        return outer;
    }

    @Override
    public void refresh() {
        topbar.setName(new com.college.controller.DashboardController().getWelcomeName());
        table.setData(ctrl.getColumns(), ctrl.getPaymentData(filterStatus, filterMode));
    }
}
