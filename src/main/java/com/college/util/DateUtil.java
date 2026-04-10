package com.college.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

    private DateUtil() {}

    private static final SimpleDateFormat DISPLAY = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat BADGE_D = new SimpleDateFormat("dd");
    private static final SimpleDateFormat BADGE_M = new SimpleDateFormat("MMM");

    public static String format(Date d) {
        return d == null ? "--" : DISPLAY.format(d);
    }

    public static String badgeDay(Date d) {
        return d == null ? "00" : BADGE_D.format(d);
    }

    public static String badgeMonth(Date d) {
        return d == null ? "---" : BADGE_M.format(d).toLowerCase();
    }

    /** Returns today's day name in uppercase e.g. "MONDAY". */
    public static String todayDayName() {
        return LocalDate.now()
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                .toUpperCase();
    }

    /** Short today label e.g. "Mon 10 Apr". */
    public static String todayLabel() {
        return LocalDate.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("EEE dd MMM"));
    }
}
