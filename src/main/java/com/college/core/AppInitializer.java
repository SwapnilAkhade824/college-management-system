package com.college.core;

import com.college.util.Constants;
import com.college.util.UITheme;
import com.college.view.panels.*;

import javax.swing.*;
import java.awt.*;

/**
 * Bootstraps the application:
 * 1. Applies Inter font globally.
 * 2. Creates the main JFrame (1080×720).
 * 3. Adds all panels to a CardLayout container.
 * 4. Wires up NavigationManager.
 * 5. Shows the Loading screen first.
 */
public class AppInitializer {

    public static void start() {
        // Apply FlatLaf if available, else fall back silently
        try {
            Class<?> flat = Class.forName("com.formdev.flatlaf.FlatLightLaf");
            UIManager.setLookAndFeel((javax.swing.LookAndFeel) flat.getDeclaredConstructor().newInstance());
        } catch (Exception ignored) {}

        // Apply Inter font globally
        UITheme.applyGlobal();

        // ── Main Frame ───────────────────────────────────────────────────────
        JFrame frame = new JFrame("College Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.W, Constants.H);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // ── Card Container ───────────────────────────────────────────────────
        CardLayout layout    = new CardLayout();
        JPanel     container = new JPanel(layout);
        container.setBackground(Constants.BG);

        // ── Register all panels ──────────────────────────────────────────────
        container.add(new LoadingPanel(),      Constants.LOADING);
        container.add(new LoginPanel(),        Constants.LOGIN);
        container.add(new SignupPanel(),       Constants.SIGNUP);
        container.add(new DashboardPanel(),    Constants.DASHBOARD);
        container.add(new DetailsPanel(),      Constants.DETAILS);
        container.add(new AttendancePanel(),   Constants.ATTENDANCE);
        container.add(new PaymentPanel(),      Constants.PAYMENT);
        container.add(new TimetablePanel(),    Constants.TIMETABLE);
        container.add(new ProfilePanel(),      Constants.PROFILE);
        container.add(new NotificationPanel(), Constants.NOTIFICATION);

        frame.setContentPane(container);

        // ── Wire NavigationManager ───────────────────────────────────────────
        NavigationManager.getInstance().init(frame, container, layout);

        // ── Show ─────────────────────────────────────────────────────────────
        frame.setVisible(true);
        NavigationManager.getInstance().navigateTo(Constants.LOADING);
    }
}
