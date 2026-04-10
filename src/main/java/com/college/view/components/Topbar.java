package com.college.view.components;

import com.college.core.NavigationManager;
import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Persistent top header bar — appears on all logged-in screens.
 * Left:  "Welcome 👋 [Name]!" in large bold text.
 * Right: AvatarButton with dropdown popup.
 * Bottom: 2px black border separator.
 */
public class Topbar extends JPanel {

    private final JLabel      welcomeLabel = new JLabel();
    private final AvatarButton avatar       = new AvatarButton();

    public Topbar() {
        setBackground(Constants.BG);
        setPreferredSize(new Dimension(0, Constants.HEADER_H));
        setBorder(BorderFactory.createMatteBorder(0, 0, Constants.STROKE, 0, Color.BLACK));
        setLayout(new BorderLayout());

        // Welcome label (left)
        welcomeLabel.setFont(UITheme.bold(34f));
        welcomeLabel.setForeground(Colors.BLACK);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));
        add(welcomeLabel, BorderLayout.CENTER);

        // Avatar (right)
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 0));
        right.setOpaque(false);
        right.add(avatar);
        add(right, BorderLayout.EAST);

        // Avatar popup menu
        JPopupMenu menu = buildMenu();
        avatar.addActionListener(e ->
            menu.show(avatar, avatar.getWidth() - menu.getPreferredSize().width, avatar.getHeight())
        );

        // Center vertically
        setAlignmentY(CENTER_ALIGNMENT);
    }

    public void setName(String displayName) {
        welcomeLabel.setText("Welcome \uD83D\uDC4B " + displayName + "!");
    }

    private JPopupMenu buildMenu() {
        JPopupMenu menu = new JPopupMenu();
        menu.setBackground(Constants.CARD_COLOR);
        menu.setBorder(BorderFactory.createLineBorder(new Color(0xAAAAAA)));

        addItem(menu, "Home",            Color.BLACK, () ->
            NavigationManager.getInstance().navigateTo(Constants.DASHBOARD));
        addItem(menu, "Profile",         Color.BLACK, () ->
            NavigationManager.getInstance().navigateTo(Constants.PROFILE));
        addItem(menu, "Change Password", Color.BLACK, () ->
            NavigationManager.getInstance().navigateTo(Constants.PROFILE)); // profile has btn
        menu.addSeparator();
        addItem(menu, "LogOut",          Constants.RED, () -> {
            new com.college.controller.AuthController().logout();
            NavigationManager.getInstance().navigateTo(Constants.LOGIN);
        });
        return menu;
    }

    private void addItem(JPopupMenu menu, String text, Color fg, Runnable action) {
        JMenuItem item = new JMenuItem(text);
        item.setFont(UITheme.bold(15f));
        item.setForeground(fg);
        item.setBackground(Constants.CARD_COLOR);
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        item.addActionListener(e -> action.run());
        menu.add(item);
    }

    // Avoid importing java.awt.Color with short alias — use direct import
    private static class Colors { static final Color BLACK = Color.BLACK; }
}
