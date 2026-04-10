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
 */
public class DetailsPanel extends JPanel implements Refreshable {

    private final StudentController ctrl = new StudentController();
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

        // Sub-header
        JPanel subHdr = buildSubHeader();
        add(subHdr, BorderLayout.CENTER);
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
        titleLbl.setFont(UITheme.bold(28f));
        titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));
        sub.add(titleLbl, BorderLayout.WEST);

        BackButton back = new BackButton();
        back.addActionListener(e -> NavigationManager.getInstance().navigateBack());
        JPanel rightSub = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 8));
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
        content.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        // Avatar
        JPanel avatarRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        avatarRow.setOpaque(false);
        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        avatarRow.add(avatar);
        content.add(avatarRow);

        // Sections
        content.add(section("Personal Information",
            new String[]{"UserID", "Name", "Date of Birth", "Gender"},
            new JLabel[]{vUserId, vName, vDob, vGender}));

        content.add(section("Academic Information",
            new String[]{"Year", "Semester", "Department", "Branch", "Backlogs"},
            new JLabel[]{vYear, vSem, vDept, vBranch, vBacklog}));

        content.add(section("Contact Information",
            new String[]{"Email", "Phone", "Address"},
            new JLabel[]{vEmail, vPhone, vAddress}));

        content.add(section("Enrollment Details",
            new String[]{"Course", "Assigned Faculty", "Subjects"},
            new JLabel[]{vCourse, vFaculty, vSubjects}));

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setBackground(Constants.BG);
        scroll.getViewport().setBackground(Constants.BG);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        return scroll;
    }

    private JPanel section(String title, String[] labels, JLabel[] values) {
        JPanel s = new JPanel();
        s.setOpaque(false);
        s.setLayout(new BoxLayout(s, BoxLayout.Y_AXIS));
        s.setAlignmentX(LEFT_ALIGNMENT);
        s.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        // Section title
        JLabel hdr = new JLabel(title + ":");
        hdr.setFont(UITheme.bold(19f)); hdr.setForeground(Color.BLACK);
        hdr.setAlignmentX(LEFT_ALIGNMENT);
        s.add(hdr);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(0xAAAAAA));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        sep.setAlignmentX(LEFT_ALIGNMENT);
        s.add(sep);
        s.add(Box.createVerticalStrut(8));

        // Rows
        for (int i = 0; i < labels.length; i++) {
            JPanel row = new JPanel(new GridLayout(1, 2, 12, 0));
            row.setOpaque(false);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            row.setAlignmentX(LEFT_ALIGNMENT);

            JLabel k = new JLabel(labels[i] + ":"); k.setFont(UITheme.bold(15f));
            values[i].setFont(UITheme.font(15f));
            row.add(k); row.add(values[i]);
            s.add(row);
            s.add(Box.createVerticalStrut(4));
        }
        s.add(Box.createVerticalStrut(18));
        return s;
    }

    private JLabel val() {
        JLabel l = new JLabel("--");
        l.setFont(UITheme.font(15f)); l.setForeground(Color.BLACK);
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
