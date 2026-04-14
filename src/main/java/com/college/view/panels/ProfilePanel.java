package com.college.view.panels;

import com.college.controller.AuthController;
import com.college.controller.StudentController;
import com.college.core.NavigationManager;
import com.college.core.Refreshable;
import com.college.model.academic.Student;
import com.college.util.Constants;
import com.college.util.UITheme;
import com.college.view.components.*;
import com.college.view.dialogs.ChangePasswordDialog;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Profile screen: avatar, masked UserID with eye-toggle, display name, Change Password button.
 * The Change Password button is fully custom-painted so it reliably renders red on all LAFs.
 */
public class ProfilePanel extends JPanel implements Refreshable {

    private final StudentController ctrl   = new StudentController();
    private final Topbar            topbar = new Topbar();
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
        title.setFont(UITheme.bold(26f));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));
        sub.add(title, BorderLayout.WEST);
        BackButton back = new BackButton();
        back.addActionListener(e -> NavigationManager.getInstance().navigateBack());
        JPanel rightSub = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 9));
        rightSub.setOpaque(false);
        rightSub.add(back);
        sub.add(rightSub, BorderLayout.EAST);
        outer.add(sub, BorderLayout.NORTH);

        // Content grid
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Constants.BG);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(14, 80, 14, 80);

        // Avatar — magenta rounded card, same as DetailsPanel
        JPanel avatar = buildAvatarCard();
        gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        content.add(avatar, gc);

        // UserID row
        gc.gridy = 1; gc.gridwidth = 1; gc.gridx = 0; gc.weightx = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        JLabel idLbl = new JLabel("UserID:", SwingConstants.RIGHT);
        idLbl.setFont(UITheme.bold(17f));
        idLbl.setForeground(Color.BLACK);
        content.add(idLbl, gc);

        JPanel idRow = new JPanel(new BorderLayout(8, 0));
        idRow.setOpaque(false);
        userIdVal.setFont(UITheme.bold(17f));
        userIdVal.setForeground(Color.BLACK);
        idRow.add(userIdVal, BorderLayout.CENTER);

        JButton eye = new JButton("👁");
        eye.setFont(UITheme.font(16f));
        eye.setFocusPainted(false);
        eye.setContentAreaFilled(false);
        eye.setBorderPainted(false);
        eye.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        idRow.add(eye, BorderLayout.EAST);

        gc.gridx = 1; gc.weightx = 1;
        content.add(idRow, gc);

        // Name row
        gc.gridy = 2; gc.gridx = 0; gc.weightx = 0;
        JLabel nameLbl = new JLabel("Name:", SwingConstants.RIGHT);
        nameLbl.setFont(UITheme.bold(17f));
        nameLbl.setForeground(Color.BLACK);
        content.add(nameLbl, gc);

        nameField.setFont(UITheme.font(16f));
        nameField.setOpaque(false);
        nameField.setBorder(new RoundedBorder(Color.BLACK, Constants.FIELD_ARC, Constants.STROKE));
        nameField.setPreferredSize(new Dimension(0, 44));
        gc.gridx = 1; gc.weightx = 1;
        content.add(nameField, gc);

        // Change Password button — custom-painted red pill
        JButton chgPwd = new JButton("Change Password") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight();
                Color bg = getModel().isArmed() ? new Color(0xCC0000) : Constants.RED;
                g2.setColor(bg);
                g2.fill(new RoundRectangle2D.Float(0, 0, w, h, h, h));
                g2.setFont(getFont());
                g2.setColor(Color.WHITE);
                FontMetrics fm = g2.getFontMetrics();
                int tx = (w - fm.stringWidth(getText())) / 2;
                int ty = (h - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(getText(), tx, ty);
                g2.dispose();
            }
        };
        chgPwd.setFont(UITheme.bold(17f));
        chgPwd.setFocusPainted(false);
        chgPwd.setContentAreaFilled(false);
        chgPwd.setBorderPainted(false);
        chgPwd.setForeground(Color.WHITE);
        chgPwd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chgPwd.setPreferredSize(new Dimension(220, 50));

        gc.gridy = 3; gc.gridx = 0; gc.gridwidth = 2; gc.weightx = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(28, 80, 14, 80);
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

    /** Magenta rounded-rect avatar with mask icon — same rendering as DetailsPanel. */
    private JPanel buildAvatarCard() {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight();
                g2.setColor(Constants.MAGENTA);
                g2.fillRoundRect(0, 0, w - 1, h - 1, 22, 22);
                g2.setColor(Color.WHITE);
                int pad = (int)(w * 0.16);
                int sw  = w - 2 * pad;
                int sh  = h - 2 * pad;
                g2.fillRoundRect(pad, pad, sw, sh, sw / 3, sh / 4);
                g2.setColor(Constants.MAGENTA);
                int ew = sw / 5, eh = sh / 6;
                int eyY = pad + sh * 2 / 5;
                g2.fillOval(pad + sw / 4 - ew / 2, eyY, ew, eh);
                g2.fillOval(pad + 3 * sw / 4 - ew / 2, eyY, ew, eh);
                g2.setStroke(new BasicStroke(3f));
                g2.drawArc(pad + sw / 4, eyY + eh + sh / 14, sw / 2, sh / 7, 0, -180);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setPreferredSize(new Dimension(80, 80));
        return card;
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
