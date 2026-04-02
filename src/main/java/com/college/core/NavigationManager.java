package com.college.core;

import javax.swing.JPanel;

/**
 * ============================================
 * CLASS: NavigationManager
 * ============================================
 *
 * PURPOSE:
 * Handles switching between different UI panels.
 *
 * RESPONSIBILITIES:
 * - Load panels dynamically
 * - Replace content inside main frame
 *
 * USED BY:
 * - Controllers
 * - UI Components (Sidebar, Buttons)
 *
 * METHODS TO IMPLEMENT:
 *
 * 1. setMainPanel(JPanel panel)
 *    @param panel
 *    @return void
 *
 * 2. navigateTo(JPanel panel)
 *    @param panel
 *    @return void
 *
 * NOTES:
 * - Should update main container (JFrame or main panel)
 *
 * ============================================
 */
public class NavigationManager {

    private static JPanel mainPanel;

    public static void setMainPanel(JPanel panel) {}

    public static void navigateTo(JPanel panel) {}
}
