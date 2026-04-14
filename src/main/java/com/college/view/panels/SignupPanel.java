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
 * Center: 2px vertical black divider.
 * Right: gray card with "Login →" button.
 */
public class SignupPanel extends JPanel {

    private final JTextField       nameField = new JTextField();
    private final JTextField       userField = new JTextField();
    private final JPasswordField   passField = new JPasswordField();
    private final JComboBox<String> typeBox  = new JComboBox<>(new String[]{"Student", "Faculty"});
    private final JLabel           msgLabel  = new JLabel(" ");
    private final AuthController   auth      = new AuthController();

    public SignupPanel() {
        setBackground(Constants.BG);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Constants.BG);
        header.setPreferredSize(new Dimension(0, Constants.HEADER_H));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, Constants.STROKE, 0, Color.BLACK));
        JLabel title = new JLabel("College Management System", SwingConstants.CENTER);
        title.setFont(UITheme.bold(34f));
        title.setForeground(Color.BLACK);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Body: Left | Divider | Right
        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(Constants.BG);
        body.add(buildLeft(),    BorderLayout.WEST);
        body.add(buildDivider(), BorderLayout.CENTER);
        body.add(buildRight(),   BorderLayout.EAST);
        add(body, BorderLayout.CENTER);
    }

    private JPanel buildDivider() {
        JPanel div = new JPanel();
        div.setBackground(Color.BLACK);
        div.setPreferredSize(new Dimension(Constants.STROKE, 0));
        return div;
    }

    private JPanel buildLeft() {
        JPanel wrapper = new JPanel();
        wrapper.setBackground(Constants.BG);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setPreferredSize(new Dimension(Constants.W / 2, 0));
        wrapper.setBorder(BorderFactory.createEmptyBorder(50, 40, 36, 24));

        // Error / message panel
        RoundedOutlinePanel msgPanel = new RoundedOutlinePanel(Constants.RED, 28, Constants.STROKE);
        msgPanel.setLayout(new BorderLayout());
        msgLabel.setFont(UITheme.font(16f));
        msgLabel.setForeground(Constants.RED);
        msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        msgLabel.setText(" ");
        msgPanel.add(msgLabel, BorderLayout.CENTER);
        msgPanel.setMaximumSize(new Dimension(400, 48));
        msgPanel.setAlignmentX(CENTER_ALIGNMENT);
        msgPanel.setVisible(false);
        wrapper.add(msgPanel);
        wrapper.add(Box.createVerticalStrut(24));

        // "SignUp" title
        JLabel signupLbl = new JLabel("SignUp", SwingConstants.CENTER);
        signupLbl.setFont(UITheme.bold(30f));
        signupLbl.setForeground(Color.BLACK);
        signupLbl.setAlignmentX(CENTER_ALIGNMENT);
        wrapper.add(signupLbl);
        wrapper.add(Box.createVerticalStrut(24));

        // Form fields
        wrapper.add(makeRow("Name:",     nameField));
        wrapper.add(Box.createVerticalStrut(10));
        wrapper.add(makeRow("UserID:",   userField));
        wrapper.add(Box.createVerticalStrut(10));
        wrapper.add(makeComboRow("Type:", typeBox));
        wrapper.add(Box.createVerticalStrut(10));
        wrapper.add(makeRow("Password:", passField));
        wrapper.add(Box.createVerticalStrut(24));

        // Request button
        ArrowButton reqBtn = new ArrowButton("Request");
        reqBtn.setPreferredSize(new Dimension(185, 60));
        reqBtn.setMaximumSize(new Dimension(185, 60));
        reqBtn.setAlignmentX(CENTER_ALIGNMENT);
        wrapper.add(reqBtn);

        reqBtn.addActionListener(e -> doRequest(msgPanel));
        return wrapper;
    }

    private JPanel buildRight() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Constants.BG);
        wrapper.setPreferredSize(new Dimension(Constants.W / 2, 0));
        wrapper.setBorder(BorderFactory.createEmptyBorder(36, 20, 36, 40));

        RoundedPanel card = new RoundedPanel(Constants.CARD_COLOR, Constants.CARD_ARC);
        card.setLayout(new BorderLayout());

        ArrowButton loginBtn = new ArrowButton("Login");
        loginBtn.setPreferredSize(new Dimension(185, 60));
        loginBtn.setMaximumSize(new Dimension(185, 60));
        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 14));
        topRow.setOpaque(false);
        topRow.add(loginBtn);
        card.add(topRow, BorderLayout.SOUTH);

        JLabel ph = new JLabel("Design Picture", SwingConstants.CENTER);
        ph.setFont(UITheme.bold(20f));
        ph.setForeground(new Color(0x777777));
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
            msgLabel.setForeground(Constants.RED);
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
        row.setPreferredSize(new Dimension(400, 60));
        row.setMaximumSize(new Dimension(400, 60));
        row.setAlignmentX(CENTER_ALIGNMENT);

        // row.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1));

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH; gc.gridy = 0;

        JLabel lbl = new JLabel(text, SwingConstants.RIGHT);
        lbl.setFont(UITheme.bold(18f));
        lbl.setForeground(Color.BLACK);
        lbl.setPreferredSize(new Dimension(20, 0));
        gc.gridx = 0; gc.weightx = 0.3;

        // lbl.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
        row.add(lbl, gc);

        field.setFont(UITheme.font(18f));
        field.setOpaque(false);
        field.setBorder(new RoundedBorder(Color.BLACK, Constants.FIELD_ARC, Constants.STROKE));
        field.setPreferredSize(new Dimension(0, 50));
        gc.gridx = 1; gc.weightx = 0.7; gc.insets = new Insets(0, 10, 0, 0);

        // field.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        row.add(field, gc);

        return row;
    }

    private JPanel makeComboRow(String text, JComboBox<String> combo) {
        JPanel row = new JPanel(new GridBagLayout());
        row.setOpaque(false);
        row.setPreferredSize(new Dimension(400, 60));
        row.setMaximumSize(new Dimension(400, 60));
        row.setAlignmentX(CENTER_ALIGNMENT);

        // row.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1));

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH; gc.gridy = 0;

        JLabel lbl = new JLabel(text, SwingConstants.RIGHT);
        lbl.setFont(UITheme.bold(18f));
        lbl.setForeground(Color.BLACK);
        lbl.setPreferredSize(new Dimension(70, 0));
        gc.gridx = 0; gc.weightx = 0.3;

        // lbl.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
        row.add(lbl, gc);

        combo.setFont(UITheme.font(18f));
        combo.setBorder(new RoundedBorder(Color.BLACK, Constants.FIELD_ARC, Constants.STROKE));
        combo.setBackground(Constants.BG);
        combo.setFocusable(false);
        gc.gridx = 1; gc.weightx = 0.7; gc.insets = new Insets(0, 10, 0, 0);

        // combo.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        row.add(combo, gc);
        return row;
    }
}
