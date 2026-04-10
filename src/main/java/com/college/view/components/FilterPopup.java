package com.college.view.components;

import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

/**
 * Floating filter popup panel — matches Figma "Select" popups on Attendance/Payment/Notification screens.
 * Shows two radio options + an optional JComboBox + Apply/Reset buttons.
 */
public class FilterPopup extends JPanel {

    private final ButtonGroup   group   = new ButtonGroup();
    private final JRadioButton  opt1    = new JRadioButton();
    private final JRadioButton  opt2    = new JRadioButton();
    private final JComboBox<String> combo;
    private final JButton       apply   = new JButton("Apply");
    private final JButton       reset   = new JButton("Reset");

    /**
     * @param opt1Label     first radio label  (e.g. "Present" / "Pending")
     * @param opt2Label     second radio label (e.g. "Absent"  / "Completed")
     * @param dropdownItems items for combo;  null to hide combo
     * @param onApply       BiConsumer(selectedOpt "opt1"|"opt2"|"all", selectedCombo or null)
     */
    public FilterPopup(String opt1Label, String opt2Label,
                       String[] dropdownItems,
                       BiConsumer<String, String> onApply) {
        setOpaque(false);
        setLayout(new BorderLayout(0, 6));

        // Outer rounded card
        RoundedPanel card = new RoundedPanel(Constants.CARD_COLOR, 24);
        card.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0; gc.gridy = 0; gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(12, 14, 2, 14);

        // Title
        JLabel title = new JLabel("Select");
        title.setFont(UITheme.bold(17f));
        card.add(title, gc);

        // Separator
        gc.gridy++; gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(0, 10, 4, 10);
        card.add(makeSep(), gc);

        // Radio 1
        opt1.setText(opt1Label); opt1.setFont(UITheme.bold(15f));
        opt1.setOpaque(false); opt1.setForeground(Color.BLACK);
        group.add(opt1); opt1.setSelected(true);
        gc.gridy++; gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(4, 14, 2, 14);
        card.add(radioRow(opt1, new Color(0x08F000)), gc);

        // Radio 2
        opt2.setText(opt2Label); opt2.setFont(UITheme.bold(15f));
        opt2.setOpaque(false); opt2.setForeground(Color.BLACK);
        group.add(opt2);
        gc.gridy++;
        card.add(radioRow(opt2, new Color(0xBBBBBB)), gc);

        // Combo (optional)
        combo = dropdownItems != null ? new JComboBox<>(dropdownItems) : null;
        if (combo != null) {
            combo.setFont(UITheme.bold(14f));
            combo.setBackground(Constants.CARD_COLOR);
            gc.gridy++; gc.fill = GridBagConstraints.HORIZONTAL;
            gc.insets = new Insets(6, 10, 4, 10);
            combo.setBorder(BorderFactory.createLineBorder(new Color(0x999999), 1, true));
            card.add(combo, gc);
        }

        // Separator
        gc.gridy++; gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(6, 10, 0, 10);
        card.add(makeSep(), gc);

        // Apply / Reset row
        apply.setFont(UITheme.bold(15f)); apply.setForeground(Constants.BLUE);
        apply.setBorderPainted(false); apply.setContentAreaFilled(false);
        apply.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        reset.setFont(UITheme.bold(15f)); reset.setForeground(Constants.RED);
        reset.setBorderPainted(false); reset.setContentAreaFilled(false);
        reset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel btns = new JPanel(new GridLayout(1, 2));
        btns.setOpaque(false);
        btns.add(apply); btns.add(reset);

        gc.gridy++; gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(4, 10, 10, 10);
        card.add(btns, gc);

        add(card, BorderLayout.CENTER);

        // Listeners
        apply.addActionListener(e -> {
            String sel   = opt1.isSelected() ? opt1.getText() : opt2.getText();
            String combo2 = combo != null ? (String) combo.getSelectedItem() : null;
            onApply.accept(sel, combo2);
        });
        reset.addActionListener(e -> {
            opt1.setSelected(true);
            if (combo != null && combo.getItemCount() > 0) combo.setSelectedIndex(0);
            onApply.accept(null, null);
        });
    }

    private JSeparator makeSep() {
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(0x999999));
        return sep;
    }

    private JPanel radioRow(JRadioButton rb, Color dotColor) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        row.setOpaque(false);
        // Colored dot
        JLabel dot = new JLabel() {
            @Override protected void paintComponent(Graphics g) {
                g.setColor(dotColor);
                g.fillOval(0, (getHeight() - 14) / 2, 14, 14);
            }
        };
        dot.setPreferredSize(new Dimension(14, 20));
        row.add(dot); row.add(rb);
        return row;
    }
}
