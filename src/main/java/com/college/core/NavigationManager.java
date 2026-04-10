package com.college.core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Singleton navigation manager — wraps a CardLayout container.
 * Maintains a back-stack so navigateBack() works across all screens.
 */
public class NavigationManager {

    private static NavigationManager instance;

    private JFrame     frame;
    private JPanel     container;
    private CardLayout layout;
    private String     currentCard;
    private final Deque<String> history = new ArrayDeque<>();

    private NavigationManager() {}

    public static NavigationManager getInstance() {
        if (instance == null) instance = new NavigationManager();
        return instance;
    }

    /** Called once by AppInitializer to bind the frame, container, and layout. */
    public void init(JFrame frame, JPanel container, CardLayout layout) {
        this.frame     = frame;
        this.container = container;
        this.layout    = layout;
    }

    /** Navigate to a named card. Pushes current to history. Calls refresh() if applicable. */
    public void navigateTo(String cardName) {
        if (currentCard != null && !currentCard.equals(cardName))
            history.push(currentCard);
        currentCard = cardName;
        layout.show(container, cardName);
        // Refresh the newly visible panel
        SwingUtilities.invokeLater(() -> {
            for (Component c : container.getComponents()) {
                if (c.isVisible() && c instanceof Refreshable)
                    ((Refreshable) c).refresh();
            }
        });
    }

    /** Navigate back to the previous card. If no history, goes to DASHBOARD. */
    public void navigateBack() {
        if (!history.isEmpty()) {
            navigateTo(history.pop());
            if (!history.isEmpty()) history.pop(); // remove the one re-pushed by navigateTo
        } else {
            navigateTo("DASHBOARD");
        }
    }

    public String getCurrentCard() { return currentCard; }
}
