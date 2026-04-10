package com.college.view.components;

import com.college.util.Constants;
import com.college.util.UITheme;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * Rounded gray table container that wraps a JTable.
 * Column separators: solid black. Row separators: dashed.
 * Background: #D3D3D3.
 */
public class StyledTable extends JPanel {

    private final JTable     table;
    private final JScrollPane scroll;

    public StyledTable(String[] columns, Object[][] data) {
        setOpaque(false);
        setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(model);
        table.setBackground(Constants.TABLE_COLOR);
        table.setFont(UITheme.font(14f));
        table.setRowHeight(38);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(0xBBBBBB));
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(new Color(0xAAAAAA));

        // Header
        JTableHeader header = table.getTableHeader();
        header.setBackground(Constants.TABLE_COLOR);
        header.setFont(UITheme.bold(14f));
        header.setForeground(Color.BLACK);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x888888)));
        header.setReorderingAllowed(false);

        // Custom renderer for dashed rows + column separators
        table.setDefaultRenderer(Object.class, new DashedCellRenderer());

        scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setBackground(Constants.TABLE_COLOR);
        scroll.getViewport().setOpaque(true);

        // Wrap in rounded panel
        RoundedPanel roundedWrap = new RoundedPanel(Constants.TABLE_COLOR, Constants.TABLE_ARC);
        roundedWrap.setLayout(new BorderLayout());
        roundedWrap.add(scroll, BorderLayout.CENTER);
        add(roundedWrap, BorderLayout.CENTER);
    }

    public void setData(String[] columns, Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table.setModel(model);
        // re-apply renderer
        table.setDefaultRenderer(Object.class, new DashedCellRenderer());
        table.getTableHeader().setBackground(Constants.TABLE_COLOR);
        table.getTableHeader().setFont(UITheme.bold(14f));
    }

    public JTable getTable() { return table; }

    // ── Inner renderer ──────────────────────────────────────────────────────
    private static class DashedCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable t, Object val, boolean sel, boolean foc, int row, int col) {
            JLabel lbl = (JLabel) super.getTableCellRendererComponent(t, val, sel, foc, row, col);
            lbl.setBackground(sel ? new Color(0xBBBBBB) : Constants.TABLE_COLOR);
            lbl.setOpaque(true);
            lbl.setFont(UITheme.font(14f));
            lbl.setForeground(Color.BLACK);
            lbl.setBorder(new DashedBorderBottom(col, t.getColumnCount()));
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            return lbl;
        }
    }

    private static class DashedBorderBottom extends javax.swing.border.AbstractBorder {
        private final int col, total;
        DashedBorderBottom(int col, int total) { this.col = col; this.total = total; }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(0x888888));
            // Dashed bottom
            g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    1, new float[]{4, 4}, 0));
            g2.drawLine(x, y + h - 1, x + w, y + h - 1);
            // Solid right column separator
            if (col < total - 1) {
                g2.setStroke(new BasicStroke(1));
                g2.drawLine(x + w - 1, y, x + w - 1, y + h);
            }
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) { return new Insets(4, 8, 4, 8); }
    }
}
