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
 * Sign-Up / Account Request screen — matches Figma Signup Screen:
 * Left: message panel, "SignUp" title, Name/UserID/Type/Password fields, "Request →" button.
 * Right: gray card with "Login →" button.
 */
public class SignupPanel extends JPanel {

    private final JTextField     nameField  = new JTextField();
    private final JTextField     userField  = new JTextField();
    private final JPasswordField passField  = new JPasswordField();
    private final JComboBox<String> typeBox = new JComboBox<>(
        new String[]{"Student", "Faculty"});
    private final JLabel         msgLabel   = new JLabel(" ");
    private final AuthController auth       = new AuthController();

    public SignupPanel() {
        setBackground(Constants.BG);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Constants.BG);
        header.setPreferredSize(new Dimension(0, Constants.HEADER_H));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, Constants.STROKE, 0, Color.BLACK));
        JLabel title = new JLabel("College Management System", SwingConstants.CENTER);
        title.setFont(UITheme.bold(36f));
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        JPanel body = new JPanel(new GridLayout(1, 2));
        body.setBackground(Constants.BG);
        body.add(buildLeft());
        body.add(buildRight());
        add(body, BorderLayout.CENTER);
    }

    private JPanel buildLeft() {
        JPanel wrapper = new JPanel();
        wrapper.setBackground(Constants.BG);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(24, 40, 40, 20));

        // Error / message panel
        RoundedOutlinePanel msgPanel = new RoundedOutlinePanel(Constants.RED, 30, Constants.STROKE);
        msgPanel.setLayout(new BorderLayout());
        msgLabel.setFont(UITheme.font(15f)); msgLabel.setForeground(Constants.RED);
        msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        msgPanel.add(msgLabel, BorderLayout.CENTER);
        msgPanel.setPreferredSize(new Dimension(0, 50));
        msgPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        msgPanel.setVisible(false);
        wrapper.add(msgPanel);
        wrapper.add(Box.createVerticalStrut(10));

        // Title
        JLabel signupLbl = new JLabel("SignUp", SwingConstants.CENTER);
        signupLbl.setFont(UITheme.bold(30f)); signupLbl.setAlignmentX(CENTER_ALIGNMENT);
        wrapper.add(signupLbl);
        wrapper.add(Box.createVerticalStrut(20));

        // Form fields
        wrapper.add(makeRow("Name:",     nameField));
        wrapper.add(Box.createVerticalStrut(12));
        wrapper.add(makeRow("UserID:",   userField));
        wrapper.add(Box.createVerticalStrut(12));
        wrapper.add(makeComboRow("Type:", typeBox));
        wrapper.add(Box.createVerticalStrut(12));
        wrapper.add(makeRow("Password:", passField));
        wrapper.add(Box.createVerticalStrut(24));

        // Request button
        ArrowButton reqBtn = new ArrowButton("Request");
        reqBtn.setPreferredSize(new Dimension(220, 60));
        reqBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        reqBtn.setAlignmentX(CENTER_ALIGNMENT);
        wrapper.add(reqBtn);

        reqBtn.addActionListener(e -> doRequest(msgPanel));
        return wrapper;
    }

    private JPanel buildRight() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Constants.BG);
        wrapper.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 40));

        RoundedPanel card = new RoundedPanel(Constants.CARD_COLOR, Constants.CARD_ARC);
        card.setLayout(new BorderLayout());

        ArrowButton loginBtn = new ArrowButton("Login");
        loginBtn.setPreferredSize(new Dimension(200, 60));
        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 16));
        topRow.setOpaque(false);
        topRow.add(loginBtn);
        card.add(topRow, BorderLayout.NORTH);

        JLabel ph = new JLabel("Design Picture", SwingConstants.CENTER);
        ph.setFont(UITheme.bold(20f)); ph.setForeground(new Color(0x888888));
        card.add(ph, BorderLayout.CENTER);

        loginBtn.addActionListener(e -> NavigationManager.getInstance().navigateTo(Constants.LOGIN));
        wrapper.add(card, BorderLayout.CENTER);
        return wrapper;
    }

    private void doRequest(JPanel msgPanel) {
        String name = nameField.getText().trim();
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword());
        String role = typeBox.getSelectedItem().toString().toUpperCase();

        if (!Validator.notEmpty(name) || !Validator.notEmpty(user) || !Validator.validPassword(pass)) {
            msgLabel.setText("Please fill all fields (password ≥ 6 chars)");
            msgPanel.setVisible(true); revalidate(); repaint(); return;
        }

        boolean ok = auth.requestAccount(user, pass, role);
        msgLabel.setForeground(ok ? new Color(0x006600) : Constants.RED);
        msgLabel.setText(ok
            ? "Request submitted! Wait for admin approval."
            : "Demo mode: DB not connected. Request queued.");
        msgPanel.setVisible(true); revalidate(); repaint();
    }

    private JPanel makeRow(String text, JTextField field) {
        JPanel row = new JPanel(new GridBagLayout());
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH; gc.gridy = 0;

        JLabel lbl = new JLabel(text, SwingConstants.RIGHT);
        lbl.setFont(UITheme.bold(17f));
        gc.gridx = 0; gc.weightx = 0.3;
        row.add(lbl, gc);

        field.setFont(UITheme.font(16f));
        field.setOpaque(false);
        field.setBorder(new RoundedBorder(Color.BLACK, Constants.FIELD_ARC, Constants.STROKE));
        gc.gridx = 1; gc.weightx = 0.7; gc.insets = new Insets(0, 12, 0, 0);
        row.add(field, gc);
        return row;
    }

    private JPanel makeComboRow(String text, JComboBox<String> combo) {
        JPanel row = new JPanel(new GridBagLayout());
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH; gc.gridy = 0;

        JLabel lbl = new JLabel(text, SwingConstants.RIGHT);
        lbl.setFont(UITheme.bold(17f));
        gc.gridx = 0; gc.weightx = 0.3;
        row.add(lbl, gc);

        combo.setFont(UITheme.font(16f));
        combo.setBorder(new RoundedBorder(Color.BLACK, Constants.FIELD_ARC, Constants.STROKE));
        combo.setBackground(Constants.BG);
        gc.gridx = 1; gc.weightx = 0.7; gc.insets = new Insets(0, 12, 0, 0);
        row.add(combo, gc);
        return row;
    }
}
