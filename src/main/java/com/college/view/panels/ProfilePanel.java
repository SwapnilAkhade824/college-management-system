package com.college.view.panels;

import com.college.controller.AuthController;
import com.college.controller.StudentController;
import com.college.core.NavigationManager;
import com.college.core.Refreshable;
import com.college.core.SessionManager;
import com.college.model.academic.Student;
import com.college.util.Constants;
import com.college.util.UITheme;
import com.college.util.Validator;
import com.college.view.components.*;
import com.college.view.dialogs.ChangePasswordDialog;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Profile screen: avatar, masked UserID with eye-toggle, display name, Change Password button.
 */
public class ProfilePanel extends JPanel implements Refreshable {

    private final StudentController ctrl   = new StudentController();
    private final Topbar            topbar = new Topbar();
    private final JLabel            avatar = new JLabel("👤", SwingConstants.CENTER);
    private final JLabel            userIdVal;
    private final JTextField        nameField = new JTextField();
    private       boolean           idVisible = false;

    public ProfilePanel() {
        setBackground(Constants.BG);
        setLayout(new BorderLayout());
        add(topbar, BorderLayout.NORTH);
        userIdVal = new JLabel("****", SwingConstants.LEFT);
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
        JLabel title = new JLabel("Profile:");
        title.setFont(UITheme.bold(28f));
        title.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));
        sub.add(title, BorderLayout.WEST);
        BackButton back = new BackButton();
        back.addActionListener(e -> NavigationManager.getInstance().navigateBack());
        JPanel rightSub = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 8));
        rightSub.setOpaque(false);
        rightSub.add(back);
        sub.add(rightSub, BorderLayout.EAST);
        outer.add(sub, BorderLayout.NORTH);

        // Content
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Constants.BG);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(12, 60, 12, 60);

        // Avatar
        avatar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 2; gc.anchor = GridBagConstraints.CENTER;
        content.add(avatar, gc);

        // UserID row
        gc.gridy = 1; gc.gridwidth = 1; gc.gridx = 0; gc.weightx = 0;
        JLabel idLbl = new JLabel("UserID:", SwingConstants.RIGHT);
        idLbl.setFont(UITheme.bold(18f));
        content.add(idLbl, gc);

        JPanel idRow = new JPanel(new BorderLayout(8, 0));
        idRow.setOpaque(false);
        userIdVal.setFont(UITheme.bold(18f));
        userIdVal.setForeground(Color.BLACK);
        idRow.add(userIdVal, BorderLayout.CENTER);
        JButton eye = new JButton("👁");
        eye.setFont(UITheme.font(16f)); eye.setFocusPainted(false);
        eye.setContentAreaFilled(false); eye.setBorderPainted(false);
        eye.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        idRow.add(eye, BorderLayout.EAST);
        gc.gridx = 1; gc.weightx = 1;
        content.add(idRow, gc);

        // Display name
        gc.gridy = 2; gc.gridx = 0; gc.weightx = 0;
        JLabel nameLbl = new JLabel("Name:", SwingConstants.RIGHT);
        nameLbl.setFont(UITheme.bold(18f));
        content.add(nameLbl, gc);
        nameField.setFont(UITheme.font(17f));
        nameField.setOpaque(false);
        nameField.setBorder(new RoundedBorder(Color.BLACK, Constants.FIELD_ARC, Constants.STROKE));
        gc.gridx = 1; gc.weightx = 1;
        content.add(nameField, gc);

        // Change Password button
        JButton chgPwd = new JButton("Change Password");
        chgPwd.setFont(UITheme.bold(18f));
        chgPwd.setBackground(Constants.RED);
        chgPwd.setForeground(Color.WHITE);
        chgPwd.setFocusPainted(false);
        chgPwd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chgPwd.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        gc.gridy = 3; gc.gridx = 0; gc.gridwidth = 2; gc.weightx = 0.5;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(28, 60, 12, 60);
        content.add(chgPwd, gc);

        // Event handlers
        eye.addActionListener(e -> {
            idVisible = !idVisible;
            Student s = ctrl.getStudent();
            if (s != null)
                userIdVal.setText(idVisible ? s.getRollNo() : "****");
        });

        chgPwd.addActionListener(e -> {
            ChangePasswordDialog dlg = new ChangePasswordDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(ProfilePanel.this));
            dlg.setVisible(true);
        });

        outer.add(content, BorderLayout.CENTER);
        return outer;
    }

    @Override
    public void refresh() {
        topbar.setName(new com.college.controller.DashboardController().getWelcomeName());
        Student s = ctrl.getStudent();
        if (s != null) {
            userIdVal.setText(idVisible ? s.getRollNo() : "****");
            nameField.setText(s.getFullName());
        }
    }
}
