package com.college.view.dialogs;

import com.college.controller.AuthController;
import com.college.util.Constants;
import com.college.util.UITheme;
import com.college.util.Validator;
import com.college.view.components.RoundedBorder;
import com.college.view.components.RoundedOutlinePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Change Password modal dialog — matches Figma "Change Password" design:
 * centered rounded gray card on dimmed background, with Old/New/Confirm fields + red Change button.
 */
public class ChangePasswordDialog extends JDialog {

    private final JPasswordField oldPass     = new JPasswordField();
    private final JPasswordField newPass     = new JPasswordField();
    private final JPasswordField confirmPass = new JPasswordField();
    private final JLabel         msgLabel    = new JLabel(" ", SwingConstants.CENTER);
    private final AuthController auth        = new AuthController();

    public ChangePasswordDialog(JFrame parent) {
        super(parent, "Change Password", true);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        JPanel overlay = new JPanel(new GridBagLayout()) {
            @Override protected void paintComponent(Graphics g) {
                g.setColor(new Color(0xBBBBBB));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        overlay.setOpaque(false);

        // Card
        JPanel card = buildCard();
        overlay.add(card);

        setContentPane(overlay);
        setSize(parent.getSize());
        setLocationRelativeTo(parent);
    }

    private JPanel buildCard() {
        JPanel card = new JPanel(new GridBagLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Constants.CARD_COLOR);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setPreferredSize(new Dimension(560, 380));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0; gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(8, 40, 4, 40);

        // Title
        JLabel title = new JLabel("Change Password", SwingConstants.CENTER);
        title.setFont(UITheme.bold(28f));
        gc.gridy = 0; gc.insets = new Insets(20, 40, 4, 40);
        card.add(title, gc);

        // Divider
        JSeparator sep = new JSeparator();
        sep.setForeground(Color.BLACK);
        gc.gridy = 1; gc.insets = new Insets(0, 20, 8, 20);
        card.add(sep, gc);

        // Error label
        msgLabel.setFont(UITheme.font(14f));
        msgLabel.setForeground(Constants.RED);
        gc.gridy = 2; gc.insets = new Insets(0, 40, 0, 40);
        card.add(msgLabel, gc);

        // Fields
        gc.insets = new Insets(8, 40, 4, 40);
        gc.gridy = 3; card.add(makeFieldRow("Old Password:", oldPass), gc);
        gc.gridy = 4; card.add(makeFieldRow("New Password:", newPass), gc);
        gc.gridy = 5; card.add(makeFieldRow("Confirm Password:", confirmPass), gc);

        // Change button
        JButton changeBtn = new JButton("Change");
        changeBtn.setFont(UITheme.bold(22f));
        changeBtn.setBackground(Constants.RED);
        changeBtn.setForeground(Color.WHITE);
        changeBtn.setFocusPainted(false);
        changeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changeBtn.setBorder(BorderFactory.createEmptyBorder(14, 40, 14, 40));
        changeBtn.addActionListener(e -> doChange());

        JPanel btnWrap = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnWrap.setOpaque(false);
        btnWrap.add(changeBtn);
        gc.gridy = 6; gc.insets = new Insets(16, 40, 20, 40);
        card.add(btnWrap, gc);

        // Cancel on click outside
        card.addMouseListener(new java.awt.event.MouseAdapter() {});
        return card;
    }

    private JPanel makeFieldRow(String labelText, JPasswordField field) {
        JPanel row = new JPanel(new GridBagLayout());
        row.setOpaque(false);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH; gc.gridy = 0;

        JLabel lbl = new JLabel(labelText, SwingConstants.RIGHT);
        lbl.setFont(UITheme.bold(18f));
        gc.gridx = 0; gc.weightx = 0.42;
        row.add(lbl, gc);

        field.setFont(UITheme.font(16f));
        field.setBackground(Color.WHITE);
        field.setBorder(new RoundedBorder(new Color(0xDDDDDD), Constants.FIELD_ARC, Constants.STROKE));
        gc.gridx = 1; gc.weightx = 0.58; gc.insets = new Insets(0, 12, 0, 0);
        row.add(field, gc);
        return row;
    }

    private void doChange() {
        String old      = new String(oldPass.getPassword());
        String newP     = new String(newPass.getPassword());
        String confirm  = new String(confirmPass.getPassword());

        if (!Validator.validPasswordChange(old, newP, confirm)) {
            msgLabel.setText("Check fields: passwords must match & be ≥ 6 chars");
            return;
        }
        boolean ok = auth.changePassword(old, newP);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Password changed successfully!");
            dispose();
        } else {
            msgLabel.setText("Old password is incorrect.");
        }
    }
}
