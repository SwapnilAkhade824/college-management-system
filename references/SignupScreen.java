import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;

public class SignupScreen {

    static int ScreenWidth = 1080;
    static int ScreenHeight = 720;
    static int BorderStroke = 2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ScreenWidth, ScreenHeight);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0xE9E9E9));
        frame.setLayout(new BorderLayout());

        // Derived Classes
        Theme theme = new Theme();
        Panels panels = new Panels();
        Buttons buttons = new Buttons();

        // Panels
        JPanel headerPanel = panels.getHeaderPanel(100, BorderStroke); // header height
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(0xE9E9E9));

        JPanel bodyPanel = new JPanel(new GridLayout(1, 2, 0, 0));
        bodyPanel.setBackground(new Color(0xE9E9E9));

        JPanel contentPanelLeft = panels.getContentPanelLeft(
            new int[] { ScreenWidth / 2, ScreenHeight - 100 },
            BorderStroke
        );
        contentPanelLeft.setLayout(new GridBagLayout());
        contentPanelLeft.setBackground(new Color(0xE9E9E9));

        JPanel contentPanelRight = panels.getContentPanelRight(
            new int[] { ScreenWidth / 2, ScreenHeight - 100 },
            BorderStroke
        );
        contentPanelRight.setLayout(new GridBagLayout());
        contentPanelRight.setBackground(new Color(0xE9E9E9));

        // Labels
        JLabel label = new JLabel("College Management System");
        label.setFont(theme.getDerivedFont(40f).deriveFont(Font.BOLD));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        // Header Layout
        headerPanel.add(label, BorderLayout.CENTER);

        // ---------------- LEFT CONTENT (SIGNUP FORM) ----------------
        JPanel leftInnerPanel = new JPanel();
        leftInnerPanel.setOpaque(false);
        leftInnerPanel.setPreferredSize(new Dimension(450, 520));
        leftInnerPanel.setLayout(null);

        JPanel messagePanel = panels.getRoundedOutlinePanel(34, Color.RED, 3);
        messagePanel.setBounds(0, 0, 450, 70);
        messagePanel.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Message");
        messageLabel.setFont(theme.getDerivedFont(24f).deriveFont(Font.PLAIN));
        messageLabel.setForeground(Color.RED);
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        JLabel signupLabel = new JLabel("SignUp");
        signupLabel.setFont(theme.getDerivedFont(28f).deriveFont(Font.BOLD));
        signupLabel.setBounds(125, 80, 200, 70);
        signupLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(theme.getDerivedFont(20f).deriveFont(Font.BOLD));
        nameLabel.setBounds(25, 160, 100, 60);
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel userLabel = new JLabel("UserID:");
        userLabel.setFont(theme.getDerivedFont(20f).deriveFont(Font.BOLD));
        userLabel.setBounds(25, 225, 100, 60);
        userLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(theme.getDerivedFont(20f).deriveFont(Font.BOLD));
        typeLabel.setBounds(25, 290, 100, 60);
        typeLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(theme.getDerivedFont(20f).deriveFont(Font.BOLD));
        passLabel.setBounds(5, 355, 120, 60);
        passLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        int startY = 165;
        int gap = 15;
        int size = 50;

        JTextField nameField = new JTextField();
        nameField.setFont(theme.getDerivedFont(20f).deriveFont(Font.PLAIN));
        nameField.setBounds(180, startY, 230, size);
        nameField.setOpaque(false);
        nameField.setBorder(new RoundedBorder(30, Color.BLACK, 2));

        JTextField userField = new JTextField();
        userField.setFont(theme.getDerivedFont(20f).deriveFont(Font.PLAIN));
        userField.setBounds(180, startY + (gap + size), 230, size);
        userField.setOpaque(false);
        userField.setBorder(new RoundedBorder(30, Color.BLACK, 2));

        JComboBox<String> typeCombo = new JComboBox<>(
            new String[] { "Student", "Faculty" }
        );
        typeCombo.setFont(theme.getDerivedFont(20f).deriveFont(Font.BOLD));
        typeCombo.setBounds(180, startY + (gap + size) * 2, 230, size);
        typeCombo.setBorder(new RoundedBorder(30, Color.BLACK, 2));
        typeCombo.setBackground(new Color(0xE9E9E9));
        typeCombo.setFocusable(false);
        typeCombo.setOpaque(false);

        DefaultListCellRenderer comboRenderer = new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(
                JList<?> list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus
            ) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(
                    list,
                    value,
                    index,
                    isSelected,
                    cellHasFocus
                );
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setFont(theme.getDerivedFont(20f).deriveFont(Font.BOLD));
                lbl.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

                if (isSelected) {
                    lbl.setBackground(new Color(0xD9D9D9));
                    lbl.setForeground(Color.BLACK);
                } else {
                    lbl.setBackground(Color.WHITE);
                    lbl.setForeground(Color.BLACK);
                }
                return lbl;
            }
        };
        typeCombo.setRenderer(comboRenderer);

        typeCombo.setUI(
            new BasicComboBoxUI() {
                protected JButton createArrowButton() {
                    JButton arrow = new JButton("⌄");
                    arrow.setFont(
                        theme.getDerivedFont(16f).deriveFont(Font.BOLD)
                    );
                    arrow.setForeground(Color.BLACK);
                    arrow.setContentAreaFilled(false);
                    arrow.setBorder(
                        BorderFactory.createEmptyBorder(0, 0, 0, 12)
                    );
                    arrow.setFocusPainted(false);
                    arrow.setCursor(
                        Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                    );
                    return arrow;
                }

                public void paintCurrentValueBackground(
                    Graphics g,
                    Rectangle bounds,
                    boolean hasFocus
                ) {}
            }
        );

        JPasswordField passField = new JPasswordField();
        passField.setFont(theme.getDerivedFont(20f).deriveFont(Font.PLAIN));
        passField.setBounds(180, startY + (gap + size) * 3, 230, size);
        passField.setOpaque(false);
        passField.setBorder(new RoundedBorder(30, Color.BLACK, 2));

        JButton requestButton = buttons.getRoundedArrowButton(
            "Request",
            230,
            64
        );
        requestButton.setFont(theme.getDerivedFont(24f).deriveFont(Font.BOLD));
        requestButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        requestButton.setBounds(120, startY + (gap + size) * 4 + gap, 230, 64);

        leftInnerPanel.add(messagePanel);
        leftInnerPanel.add(signupLabel);
        leftInnerPanel.add(nameLabel);
        leftInnerPanel.add(userLabel);
        leftInnerPanel.add(typeLabel);
        leftInnerPanel.add(passLabel);
        leftInnerPanel.add(nameField);
        leftInnerPanel.add(userField);
        leftInnerPanel.add(typeCombo);
        leftInnerPanel.add(passField);
        leftInnerPanel.add(requestButton);

        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        gbcLeft.weightx = 1;
        gbcLeft.weighty = 1;
        gbcLeft.insets = new Insets(15, 25, 25, 25);
        gbcLeft.anchor = GridBagConstraints.CENTER;
        contentPanelLeft.add(leftInnerPanel, gbcLeft);

        // ---------------- RIGHT CONTENT (DESIGN PANEL + LOGIN BTN) ----------------
        RoundedPanel rightInnerPanel = panels.getRoundedPanel(
            new Color(0xC6C6C8),
            60
        );
        rightInnerPanel.setPreferredSize(new Dimension(450, 500));
        rightInnerPanel.setLayout(null);

        JButton loginButton = buttons.getRoundedArrowButton("Login", 200, 60);
        loginButton.setFont(theme.getDerivedFont(24f).deriveFont(Font.BOLD));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBounds(125, 400, 200, 60);

        JLabel designLabel = new JLabel("Design Picture");
        designLabel.setFont(theme.getDerivedFont(32f).deriveFont(Font.BOLD));
        designLabel.setHorizontalAlignment(SwingConstants.CENTER);
        designLabel.setBounds(70, 190, 300, 100);

        rightInnerPanel.add(loginButton);
        rightInnerPanel.add(designLabel);

        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        gbcRight.weightx = 1;
        gbcRight.weighty = 1;
        gbcRight.insets = new Insets(25, 25, 25, 25);
        gbcRight.anchor = GridBagConstraints.CENTER;
        contentPanelRight.add(rightInnerPanel, gbcRight);

        // Body Layout
        bodyPanel.add(contentPanelLeft);
        bodyPanel.add(contentPanelRight);

        // Screen Layout
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(bodyPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

class Panels {

    JPanel getHeaderPanel(int height, int stroke) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, height));
        panel.setBorder(
            BorderFactory.createMatteBorder(0, 0, stroke, 0, Color.BLACK)
        );
        return panel;
    }

    JPanel getContentPanelLeft(int[] dimensions, int stroke) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(dimensions[0], dimensions[1]));
        panel.setBorder(
            BorderFactory.createMatteBorder(0, 0, 0, stroke, Color.BLACK)
        );
        return panel;
    }

    JPanel getContentPanelRight(int[] dimensions, int stroke) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(dimensions[0], dimensions[1]));
        panel.setBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK)
        );
        return panel;
    }

    RoundedPanel getRoundedPanel(Color color, int arc) {
        return new RoundedPanel(color, arc);
    }

    RoundedOutlinePanel getRoundedOutlinePanel(
        int arc,
        Color color,
        int stroke
    ) {
        return new RoundedOutlinePanel(arc, color, stroke);
    }
}

class Theme {

    File fontFile;
    Font baseFont;

    Theme() {
        try {
            fontFile = new File("resources/fonts/Inter-Regular.ttf");
            baseFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (IOException | FontFormatException e) {
            baseFont = new Font("SansSerif", Font.PLAIN, 16);
            e.printStackTrace();
        }
    }

    public Font getBaseFont() {
        return baseFont;
    }

    public Font getDerivedFont(float size) {
        return baseFont.deriveFont(size);
    }
}

class Buttons {

    JButton getRoundedButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        return button;
    }

    JButton getRoundedArrowButton(String text, int width, int height) {
        JButton button = new RoundedArrowButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        return button;
    }
}

class RoundedPanel extends JPanel {

    Color color;
    int arc;

    RoundedPanel(Color color, int arc) {
        this.color = color;
        this.arc = arc;
        setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setColor(color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        g2.dispose();
        super.paintComponent(g);
    }
}

class RoundedOutlinePanel extends JPanel {

    int arc;
    Color color;
    int stroke;

    RoundedOutlinePanel(int arc, Color color, int stroke) {
        this.arc = arc;
        this.color = color;
        this.stroke = stroke;
        setOpaque(false);
    }

    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setColor(color);
        g2.setStroke(new BasicStroke(stroke));
        g2.drawRoundRect(
            stroke / 2,
            stroke / 2,
            getWidth() - stroke,
            getHeight() - stroke,
            arc,
            arc
        );
        g2.dispose();
    }
}

class RoundedArrowButton extends JButton {

    Color green = new Color(0x08F000);
    Color circle = new Color(0xBFEFC3);

    RoundedArrowButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setHorizontalAlignment(SwingConstants.LEFT);
        setMargin(new Insets(0, 34, 0, 0));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        int w = getWidth();
        int h = getHeight();

        g2.setColor(green);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, h, h));

        int circleSize = h - 8;
        int cx = w - circleSize - 4;
        int cy = 4;

        g2.setColor(circle);
        g2.fillOval(cx, cy, circleSize, circleSize);

        g2.setFont(getFont());
        g2.setColor(Color.WHITE);
        FontMetrics fm = g2.getFontMetrics();
        int textY = (h - fm.getHeight()) / 2 + fm.getAscent() - 2;
        g2.drawString(getText(), 34, textY);

        g2.setColor(green);
        g2.setFont(getFont().deriveFont((float) (getFont().getSize() + 6)));
        FontMetrics afm = g2.getFontMetrics();
        String arrow = "→";
        int ax = cx + (circleSize - afm.stringWidth(arrow)) / 2;
        int ay = (h - afm.getHeight()) / 2 + afm.getAscent() - 1;
        g2.drawString(arrow, ax, ay);

        g2.dispose();
    }
}

class RoundedBorder extends AbstractBorder {

    int radius;
    Color color;
    int thickness;

    RoundedBorder(int radius, Color color, int thickness) {
        this.radius = radius;
        this.color = color;
        this.thickness = thickness;
    }

    public void paintBorder(
        Component c,
        Graphics g,
        int x,
        int y,
        int width,
        int height
    ) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawRoundRect(
            x + thickness / 2,
            y + thickness / 2,
            width - thickness,
            height - thickness,
            radius,
            radius
        );
        g2.dispose();
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(10, 16, 10, 16);
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = 16;
        insets.top = insets.bottom = 10;
        return insets;
    }
}
