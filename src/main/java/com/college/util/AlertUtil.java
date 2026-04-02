package com.college.util;

import javax.swing.JOptionPane;

/**
 * ============================================
 * CLASS: AlertUtil
 * ============================================
 *
 * PURPOSE:
 * Displays popup messages.
 *
 * USED BY:
 * - Controllers
 * - UI Panels
 *
 * METHODS TO IMPLEMENT:
 *
 * 1. showSuccess(String message)
 * 2. showError(String message)
 * 3. showInfo(String message)
 *
 * ============================================
 */
public class AlertUtil {

    public static void showSuccess(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
