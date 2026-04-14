package com.college.view.components;

import com.college.core.NavigationManager;
import com.college.util.Constants;
import com.college.util.UITheme;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.awt.*;

/**
 * Persistent top header bar — appears on all logged-in screens.
 * Left:  "Welcome 👋 [Name]!" in large bold text.
 * Right: AvatarButton with dropdown popup, properly centered vertically.
 * Bottom: 2px black border separator.
 */
public class Topbar extends JPanel {

    private final JPanel       welcomeWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    private final JLabel       welcomePrefix  = new JLabel("Welcome ");
    private final JLabel       iconLabel      = new JLabel();
    private final JLabel       welcomeName    = new JLabel();
    private final AvatarButton avatar         = new AvatarButton();

    public Topbar() {
        setBackground(Constants.BG);
        setPreferredSize(new Dimension(0, Constants.HEADER_H));
        setBorder(BorderFactory.createMatteBorder(0, 0, Constants.STROKE, 0, Color.BLACK));
        setLayout(new BorderLayout());

        // Welcome wrapper (left), vertically centered
        welcomeWrapper.setOpaque(false);
        welcomeWrapper.setBorder(BorderFactory.createEmptyBorder(25, 24, 0, 0));
        
        welcomePrefix.setFont(UITheme.bold(30f));
        welcomePrefix.setForeground(Color.BLACK);
        
        iconLabel.setIcon(new FlatSVGIcon("icons/hand_shake.svg", 40, 40));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 12));
        
        welcomeName.setFont(UITheme.bold(30f));
        welcomeName.setForeground(Color.BLACK);
        
        welcomeWrapper.add(welcomePrefix);
        welcomeWrapper.add(iconLabel);
        welcomeWrapper.add(welcomeName);
        
        add(welcomeWrapper, BorderLayout.CENTER);

        // Avatar (right) — FlowLayout with computed vgap for vertical centering
        int vgap = (Constants.HEADER_H - AvatarButton.SIZE) / 2;
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, vgap));
        right.setOpaque(false);
        right.add(avatar);
        add(right, BorderLayout.EAST);

        // Avatar popup menu
        JPopupMenu menu = buildMenu();
        avatar.addActionListener(e ->
            menu.show(avatar, avatar.getWidth() - menu.getPreferredSize().width, avatar.getHeight())
        );
    }

    public void setName(String displayName) {
        welcomeName.setText(displayName + "!");
    }

    private JPopupMenu buildMenu() {
        JPopupMenu menu = new JPopupMenu();
        menu.setBackground(Constants.CARD_COLOR);
        menu.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0x888888), 1, true),
            BorderFactory.createEmptyBorder(4, 0, 4, 0)
        ));

        addItem(menu, "Home",            Color.BLACK, () ->
            NavigationManager.getInstance().navigateTo(Constants.DASHBOARD));
        addItem(menu, "Profile",         Color.BLACK, () ->
            NavigationManager.getInstance().navigateTo(Constants.PROFILE));
        addItem(menu, "Change Password", Color.BLACK, () ->
            NavigationManager.getInstance().navigateTo(Constants.PROFILE));
        menu.addSeparator();
        addItem(menu, "LogOut", Constants.RED, () -> {
            new com.college.controller.AuthController().logout();
            NavigationManager.getInstance().navigateTo(Constants.LOGIN);
        });
        return menu;
    }

    private void addItem(JPopupMenu menu, String text, Color fg, Runnable action) {
        JMenuItem item = new JMenuItem(text) {
            @Override
            protected void paintComponent(Graphics g) {
                // Draw hover background manually
                if (getModel().isArmed() || getModel().isPressed()) {
                    g.setColor(new Color(0xB0B0B2));
                    g.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    g.setColor(Constants.CARD_COLOR);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                super.paintComponent(g);
            }
        };
        item.setFont(UITheme.bold(15f));
        item.setForeground(fg);
        item.setBackground(Constants.CARD_COLOR);
        item.setOpaque(false);
        item.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 24));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        item.addActionListener(e -> action.run());
        menu.add(item);
    }
}
