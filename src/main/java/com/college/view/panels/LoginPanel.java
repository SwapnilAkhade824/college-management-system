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
 * - Right half: optional error panel, "Login" title, UserID, Password, "Login →" button
 */
public class LoginPanel extends JPanel {

    private final JTextField     userField = new JTextField();
    private final JPasswordField passField = new JPasswordField();
    private final JLabel         msgLabel  = new JLabel(" ");
    private final JPanel         msgPanel;
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
        title.setFont(UITheme.bold(36f));
        title.setForeground(Color.BLACK);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // ── Body ───────────────────────────────────────────────────────────
        JPanel body = new JPanel(new GridLayout(1, 2));
        body.setBackground(Constants.BG);
        body.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // LEFT: gray card with "Request" button
        body.add(buildLeft());

        // RIGHT: login form
        body.add(buildRight());

        add(body, BorderLayout.CENTER);
        msgPanel = null; // initialized in buildRight
    }

    private JPanel buildLeft() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Constants.BG);
        wrapper.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 20));

        RoundedPanel card = new RoundedPanel(Constants.CARD_COLOR, Constants.CARD_ARC);
        card.setLayout(new BorderLayout());

        // "Request" button at top
        ArrowButton reqBtn = new ArrowButton("Request");
        reqBtn.setPreferredSize(new Dimension(220, 60));
        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 16));
        topRow.setOpaque(false);
        topRow.add(reqBtn);
        card.add(topRow, BorderLayout.NORTH);

        // Placeholder image area
        JLabel placeholder = new JLabel("Design Picture", SwingConstants.CENTER);
        placeholder.setFont(UITheme.bold(20f));
        placeholder.setForeground(new Color(0x888888));
        card.add(placeholder, BorderLayout.CENTER);

        reqBtn.addActionListener(e -> NavigationManager.getInstance().navigateTo(Constants.SIGNUP));

        wrapper.add(card, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel buildRight() {
        JPanel wrapper = new JPanel();
        wrapper.setBackground(Constants.BG);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(36, 20, 40, 40));

        // Error message panel (red outline, hidden by default)
        JPanel errWrap = new JPanel(new BorderLayout());
        errWrap.setBackground(Constants.BG);
        errWrap.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        RoundedOutlinePanel errPanel = new RoundedOutlinePanel(Constants.RED, 30, Constants.STROKE);
        errPanel.setLayout(new BorderLayout());
        msgLabel.setFont(UITheme.font(15f));
        msgLabel.setForeground(Constants.RED);
        msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errPanel.add(msgLabel, BorderLayout.CENTER);
        errPanel.setPreferredSize(new Dimension(0, 50));
        errPanel.setVisible(false);

        errWrap.add(errPanel, BorderLayout.CENTER);
        wrapper.add(errWrap);
        wrapper.add(Box.createVerticalStrut(16));

        // "Login" title
        JLabel loginLbl = makeLabel("Login", UITheme.bold(32f), SwingConstants.CENTER);
        loginLbl.setAlignmentX(CENTER_ALIGNMENT);
        wrapper.add(loginLbl);
        wrapper.add(Box.createVerticalStrut(28));

        // UserID
        wrapper.add(makeFormRow("UserID:", userField));
        wrapper.add(Box.createVerticalStrut(16));

        // Password
        wrapper.add(makeFormRow("Password:", passField));
        wrapper.add(Box.createVerticalStrut(32));

        // Login button
        ArrowButton loginBtn = new ArrowButton("Login");
        loginBtn.setPreferredSize(new Dimension(220, 60));
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        loginBtn.setAlignmentX(CENTER_ALIGNMENT);
        wrapper.add(loginBtn);

        loginBtn.addActionListener(e -> doLogin(errPanel));

        // Enter key
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
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 54));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0; gc.fill = GridBagConstraints.BOTH;

        JLabel lbl = makeLabel(labelText, UITheme.bold(18f), SwingConstants.RIGHT);
        gc.gridx = 0; gc.weightx = 0.35;
        row.add(lbl, gc);

        field.setFont(UITheme.font(16f));
        field.setOpaque(false);
        field.setBorder(new RoundedBorder(Color.BLACK, Constants.FIELD_ARC, Constants.STROKE));
        field.setBackground(Constants.BG);
        gc.gridx = 1; gc.weightx = 0.65; gc.insets = new Insets(0, 12, 0, 0);
        row.add(field, gc);

        return row;
    }

    private JLabel makeLabel(String text, Font font, int align) {
        JLabel l = new JLabel(text, align);
        l.setFont(font); l.setForeground(Color.BLACK);
        return l;
    }
}
