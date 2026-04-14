package com.college.view.panels;

import com.college.controller.AuthController;
import com.college.core.NavigationManager;
import com.college.util.Constants;
import com.college.util.UITheme;
import com.college.util.Validator;
import com.college.view.components.*;

import javax.swing.*;
import java.awt.*;

/**
 * Login Screen — matches reference design:
 * - Full-width header "College Management System"
 * - Left half: gray rounded card with "Request →" button on top
 * - Center: 2px vertical black divider
 * - Right half: optional error panel, "Login" title, UserID, Password, "Login →" button
 */
public class LoginPanel extends JPanel {

    private final JTextField     userField = new JTextField();
    private final JPasswordField passField = new JPasswordField();
    private final JLabel         msgLabel  = new JLabel(" ");
    private final AuthController auth      = new AuthController();

    public LoginPanel() {
        setBackground(Constants.BG);
        setLayout(new BorderLayout());

        // ── Header ─────────────────────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Constants.BG);
        header.setPreferredSize(new Dimension(0, Constants.HEADER_H));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, Constants.STROKE, 0, Color.BLACK));
        JLabel title = new JLabel("College Management System", SwingConstants.CENTER);
        title.setFont(UITheme.bold(34f));
        title.setForeground(Color.BLACK);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // ── Body: Left | Divider | Right ───────────────────────────────────
        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(Constants.BG);

        body.add(buildLeft(),    BorderLayout.WEST);
        body.add(buildDivider(), BorderLayout.CENTER);
        body.add(buildRight(),   BorderLayout.EAST);

        add(body, BorderLayout.CENTER);
    }

    /** Vertical 2px black center divider. */
    private JPanel buildDivider() {
        JPanel div = new JPanel();
        div.setBackground(Color.BLACK);
        div.setPreferredSize(new Dimension(Constants.STROKE, 0));
        return div;
    }

    private JPanel buildLeft() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Constants.BG);
        wrapper.setPreferredSize(new Dimension(Constants.W / 2, 0));
        wrapper.setBorder(BorderFactory.createEmptyBorder(36, 40, 36, 20));

        RoundedPanel card = new RoundedPanel(Constants.CARD_COLOR, Constants.CARD_ARC);
        card.setLayout(new BorderLayout());

        // "Request" button at top-left
        ArrowButton reqBtn = new ArrowButton("Request");
        reqBtn.setPreferredSize(new Dimension(185, 60));
        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 14));
        topRow.setOpaque(false);
        topRow.add(reqBtn);
        card.add(topRow, BorderLayout.SOUTH);

        // Placeholder center label
        JLabel placeholder = new JLabel("Design Picture", SwingConstants.CENTER);
        placeholder.setFont(UITheme.bold(20f));
        placeholder.setForeground(new Color(0x777777));
        card.add(placeholder, BorderLayout.CENTER);

        reqBtn.addActionListener(e -> NavigationManager.getInstance().navigateTo(Constants.SIGNUP));
        wrapper.add(card, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel buildRight() {
        JPanel wrapper = new JPanel();
        wrapper.setBackground(Constants.BG);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setPreferredSize(new Dimension(Constants.W / 2, 0));
        wrapper.setBorder(BorderFactory.createEmptyBorder(60, 28, 36, 40));

        // Error message panel (red outline, hidden by default)
        RoundedOutlinePanel errPanel = new RoundedOutlinePanel(Constants.RED, 28, Constants.STROKE);
        errPanel.setLayout(new BorderLayout());
        msgLabel.setFont(UITheme.font(16f));
        msgLabel.setForeground(Constants.RED);
        msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        msgLabel.setText(" ");
        errPanel.add(msgLabel, BorderLayout.CENTER);
        errPanel.setMaximumSize(new Dimension(400, 48));
        errPanel.setAlignmentX(CENTER_ALIGNMENT);
        errPanel.setVisible(false);
        wrapper.add(errPanel);
        wrapper.add(Box.createVerticalStrut(30));

        // "Login" title
        JLabel loginLbl = new JLabel("Login", SwingConstants.CENTER);
        loginLbl.setFont(UITheme.bold(30f));
        loginLbl.setForeground(Color.BLACK);
        loginLbl.setAlignmentX(CENTER_ALIGNMENT);
        wrapper.add(loginLbl);
        wrapper.add(Box.createVerticalStrut(36));

        // UserID
        wrapper.add(makeFormRow("UserID:", userField));
        wrapper.add(Box.createVerticalStrut(20));

        // Password
        wrapper.add(makeFormRow("Password:", passField));
        wrapper.add(Box.createVerticalStrut(40));

        // Login button
        ArrowButton loginBtn = new ArrowButton("Login");
        loginBtn.setPreferredSize(new Dimension(185, 60));
        loginBtn.setMaximumSize(new Dimension(185, 60));
        loginBtn.setAlignmentX(CENTER_ALIGNMENT);
        wrapper.add(loginBtn);

        loginBtn.addActionListener(e -> doLogin(errPanel));
        passField.addActionListener(e -> doLogin(errPanel));
        userField.addActionListener(e -> passField.requestFocusInWindow());

        return wrapper;
    }

    private void doLogin(JPanel errPanel) {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword());
        if (!Validator.notEmpty(user) || !Validator.notEmpty(pass)) {
            showError(errPanel, "Please enter UserID and Password");
            return;
        }
        if (auth.login(user, pass)) {
            hideError(errPanel);
            NavigationManager.getInstance().navigateTo(Constants.DASHBOARD);
        } else {
            showError(errPanel, "Invalid credentials. Please try again.");
        }
    }

    private void showError(JPanel errPanel, String msg) {
        msgLabel.setText(msg);
        errPanel.setVisible(true);
        revalidate(); repaint();
    }

    private void hideError(JPanel errPanel) {
        errPanel.setVisible(false);
        revalidate(); repaint();
    }

    private JPanel makeFormRow(String labelText, JTextField field) {
        JPanel row = new JPanel(new GridBagLayout());
        row.setOpaque(false);
        row.setPreferredSize(new Dimension(400, 60));
        row.setMaximumSize(new Dimension(400, 60));
        row.setAlignmentX(CENTER_ALIGNMENT);

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0; gc.fill = GridBagConstraints.BOTH;

        JLabel lbl = new JLabel(labelText, SwingConstants.RIGHT);
        lbl.setFont(UITheme.bold(18f));
        lbl.setForeground(Color.BLACK);
        lbl.setPreferredSize(new Dimension(60, 0));
        gc.gridx = 0; gc.weightx = 0.3;
        row.add(lbl, gc);

        field.setFont(UITheme.font(18f));
        field.setOpaque(false);
        field.setBorder(new RoundedBorder(Color.BLACK, Constants.FIELD_ARC, Constants.STROKE));
        field.setBackground(Constants.BG);
        field.setPreferredSize(new Dimension(0, 50));
        gc.gridx = 1; gc.weightx = 0.7; gc.insets = new Insets(0, 10, 0, 0);
        row.add(field, gc);

        return row;
    }
}
