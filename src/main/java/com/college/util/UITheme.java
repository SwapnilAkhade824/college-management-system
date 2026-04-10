package com.college.util;

import java.awt.*;
import java.io.InputStream;

/**
 * Centralized theme: Inter font loader + font factory methods.
 */
public final class UITheme {

    private static Font BASE;

    static {
        Font loaded = null;
        try (InputStream is = UITheme.class.getResourceAsStream("/style/Inter-Regular.ttf")) {
            if (is != null) {
                loaded = Font.createFont(Font.TRUETYPE_FONT, is);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(loaded);
            }
        } catch (Exception ignored) {}
        BASE = loaded != null ? loaded : new Font("SansSerif", Font.PLAIN, 14);
    }

    private UITheme() {}

    /** Plain Inter at given pt size. */
    public static Font font(float size) {
        return BASE.deriveFont(Font.PLAIN, size);
    }

    /** Bold Inter at given pt size. */
    public static Font bold(float size) {
        return BASE.deriveFont(Font.BOLD, size);
    }

    /**
     * Apply the base font to all Swing components via UIManager.
     * Call once at startup.
     */
    public static void applyGlobal() {
        Font ui = font(14f);
        String[] keys = {
            "Button.font", "Label.font", "TextField.font", "PasswordField.font",
            "TextArea.font", "ComboBox.font", "Table.font", "TableHeader.font",
            "List.font", "MenuItem.font", "Menu.font", "PopupMenu.font",
            "OptionPane.messageFont", "OptionPane.buttonFont"
        };
        for (String k : keys) javax.swing.UIManager.put(k, ui);
    }
}
