package com.college.view.panels;

import com.college.core.NavigationManager;
import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;

/**
 * Loading screen: gray background + spinning progress bar centered.
 * Auto-transitions to Login after 2s.
 */
public class LoadingPanel extends JPanel {

    public LoadingPanel() {
        setBackground(Constants.BG);
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0; gc.anchor = GridBagConstraints.CENTER;

        // Spinner
        JProgressBar spinner = new JProgressBar();
        spinner.setIndeterminate(true);
        spinner.setPreferredSize(new Dimension(120, 120));
        spinner.setBackground(Constants.BG);
        spinner.setForeground(new Color(0x888888));
        spinner.setBorderPainted(false);
        spinner.setString("");
        gc.gridy = 0;
        add(spinner, gc);

        // "College Management System" subtle label
        JLabel lbl = new JLabel("College Management System");
        lbl.setFont(UITheme.font(14f));
        lbl.setForeground(new Color(0x777777));
        gc.gridy = 1; gc.insets = new Insets(16, 0, 0, 0);
        add(lbl, gc);

        // Auto-navigate after 2s
        Timer t = new Timer(2000, e -> NavigationManager.getInstance().navigateTo(Constants.LOGIN));
        t.setRepeats(false);
        t.start();
    }
}
