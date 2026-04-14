package com.college.view.panels;

import com.college.controller.StudentController;
import com.college.core.NavigationManager;
import com.college.core.Refreshable;
import com.college.model.academic.Student;
import com.college.util.Constants;
import com.college.util.DateUtil;
import com.college.util.UITheme;
import com.college.view.components.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Details screen — full student profile with Personal/Academic/Contact/Enrollment info.
 * Avatar is a magenta rounded-rectangle with the AvatarButton-style mask icon.
 */
public class DetailsPanel extends JPanel implements Refreshable {

    private final StudentController ctrl   = new StudentController();
    private final Topbar            topbar = new Topbar();

    // Value labels
    private final JLabel vUserId   = val();
    private final JLabel vName     = val();
    private final JLabel vDob      = val();
    private final JLabel vGender   = val();
    private final JLabel vYear     = val();
    private final JLabel vSem      = val();
    private final JLabel vDept     = val();
    private final JLabel vBranch   = val();
    private final JLabel vBacklog  = val();
    private final JLabel vEmail    = val();
    private final JLabel vPhone    = val();
    private final JLabel vAddress  = val();
    private final JLabel vCourse   = val();
    private final JLabel vFaculty  = val();
    private final JLabel vSubjects = val();

    public DetailsPanel() {
        setBackground(Constants.BG);
        setLayout(new BorderLayout());
        add(topbar, BorderLayout.NORTH);
        add(buildSubHeader(), BorderLayout.CENTER);
    }

    private JPanel buildSubHeader() {
        JPanel outer = new JPanel(new BorderLayout());
        outer.setBackground(Constants.BG);

        // Sub-header bar
        JPanel sub = new JPanel(new BorderLayout());
        sub.setBackground(Constants.BG);
        sub.setBorder(new MatteBorder(0, 0, Constants.STROKE, 0, Color.BLACK));
        sub.setPreferredSize(new Dimension(0, Constants.SUB_H));

        JLabel titleLbl = new JLabel("Details:");
        titleLbl.setFont(UITheme.bold(26f));
        titleLbl.setForeground(Color.BLACK);
        titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));
        sub.add(titleLbl, BorderLayout.WEST);

        BackButton back = new BackButton();
        back.addActionListener(e -> NavigationManager.getInstance().navigateBack());
        JPanel rightSub = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 9));
        rightSub.setOpaque(false);
        rightSub.add(back);
        sub.add(rightSub, BorderLayout.EAST);

        outer.add(sub, BorderLayout.NORTH);
        outer.add(buildScrollContent(), BorderLayout.CENTER);
        return outer;
    }

    private JScrollPane buildScrollContent() {
        JPanel content = new JPanel();
        content.setBackground(Constants.BG);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(24, 60, 30, 60));

        content.add(section("Personal Information",
            new String[]{"PhotoID", "UserID", "Name", "Date of Birth", "Gender"},
            new JComponent[]{buildAvatarCard(), vUserId, vName, vDob, vGender}));

        content.add(section("Academic Information",
            new String[]{"Year", "Semester", "Department", "Branch", "Backlog"},
            new JComponent[]{vYear, vSem, vDept, vBranch, vBacklog}));

        content.add(section("Contact Information",
            new String[]{"Email", "Phone", "Address"},
            new JComponent[]{vEmail, vPhone, vAddress}));

        content.add(section("Enrollment Details",
            new String[]{"Enrolled Courses", "Assigned Faculty", "Subjects"},
            new JComponent[]{vCourse, vFaculty, vSubjects}));

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setBackground(Constants.BG);
        scroll.getViewport().setBackground(Constants.BG);
        scroll.getVerticalScrollBar().setUnitIncrement(14);
        return scroll;
    }

    /** Magenta rounded-rect avatar with mask icon, matching the Details reference image. */
    private JPanel buildAvatarCard() {
        int w = 110, h = 110;
        JPanel main = new JPanel(null);
        main.setOpaque(false);
        main.setPreferredSize(new Dimension(w, h));
        main.setMinimumSize(new Dimension(w, h));
        main.setMaximumSize(new Dimension(w, h));

        // Base Magenta Card
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Constants.MAGENTA);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                
                // Avatar SVG
                com.formdev.flatlaf.extras.FlatSVGIcon icon = new com.formdev.flatlaf.extras.FlatSVGIcon("icons/avatar.svg", (int)(getWidth()*0.7), (int)(getHeight()*0.7));
                icon.paintIcon(this, g2, (getWidth()-icon.getIconWidth())/2, (getHeight()-icon.getIconHeight())/2);
                g2.dispose();
            }
        };
        card.setBounds(0, 0, w, h);
        card.setOpaque(false);
        main.add(card);

        // Edit Badge (Red rounded rect with change.svg)
        JPanel badge = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Constants.RED);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                
                com.formdev.flatlaf.extras.FlatSVGIcon icon = new com.formdev.flatlaf.extras.FlatSVGIcon("icons/change.svg", 12, 12);
                icon.paintIcon(this, g2, (getWidth()-icon.getIconWidth())/2, (getHeight()-icon.getIconHeight())/2);
                g2.dispose();
            }
        };
        badge.setBounds(w - 28, h - 28, 24, 24);
        badge.setOpaque(false);
        main.add(badge);

        return main;
    }

    private JPanel section(String title, String[] labels, JComponent[] values) {
        JPanel s = new JPanel();
        s.setOpaque(false);
        s.setLayout(new BoxLayout(s, BoxLayout.Y_AXIS));
        s.setAlignmentX(LEFT_ALIGNMENT);
        s.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        // Section title
        JLabel hdr = new JLabel(title + ":");
        hdr.setFont(UITheme.bold(22f));
        hdr.setForeground(Color.BLACK);
        hdr.setAlignmentX(LEFT_ALIGNMENT);
        hdr.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        s.add(hdr);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(0x777777));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        sep.setAlignmentX(LEFT_ALIGNMENT);
        s.add(sep);
        s.add(Box.createVerticalStrut(15));

        for (int i = 0; i < labels.length; i++) {
            JPanel row = new JPanel(new BorderLayout(24, 0));
            row.setOpaque(false);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, values[i] instanceof JPanel ? 120 : 35));
            row.setAlignmentX(LEFT_ALIGNMENT);

            JLabel k = new JLabel(labels[i] + ":", SwingConstants.RIGHT);
            k.setFont(UITheme.bold(18f));
            k.setForeground(Color.BLACK);
            k.setPreferredSize(new Dimension(180, 0));
            
            if (values[i] instanceof JLabel) {
                values[i].setFont(UITheme.font(18f));
                ((JLabel)values[i]).setHorizontalAlignment(SwingConstants.LEFT);
            }
            values[i].setForeground(Color.BLACK);
            
            row.add(k, BorderLayout.WEST);
            
            // For PhotoID, ensure TOP alignment
            if (values[i] instanceof JPanel) {
                JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                wrapper.setOpaque(false);
                wrapper.add(values[i]);
                row.add(wrapper, BorderLayout.CENTER);
            } else {
                row.add(values[i], BorderLayout.CENTER);
            }
            
            s.add(row);
            s.add(Box.createVerticalStrut(8));
        }
        s.add(Box.createVerticalStrut(20));
        return s;
    }

    private JLabel val() {
        JLabel l = new JLabel("--");
        l.setFont(UITheme.font(15f));
        l.setForeground(Color.BLACK);
        return l;
    }

    @Override
    public void refresh() {
        topbar.setName(new com.college.controller.DashboardController().getWelcomeName());
        Student s = ctrl.getStudent();
        if (s == null) return;
        vUserId.setText("STU" + s.getUserId());
        vName.setText(s.getFullName());
        vDob.setText(DateUtil.format(s.getDob()));
        vGender.setText(s.getGender());
        vYear.setText(ctrl.getYearLabel());
        vSem.setText(ctrl.getSemLabel());
        vDept.setText(ctrl.getDeptName());
        vBranch.setText(ctrl.getBranch());
        vBacklog.setText(String.valueOf(ctrl.getBacklog()));
        vEmail.setText(s.getEmail());
        vPhone.setText(s.getPhone());
        vAddress.setText(s.getAddress());
        vCourse.setText(ctrl.getCourseName());
        vFaculty.setText(ctrl.getFacultyName());
        vSubjects.setText(ctrl.getSubjectList());
    }
}
