package com.college.util;

import java.awt.Color;

public final class Constants {

    private Constants() {}

    // --- Window ---
    public static final int W          = 1080;
    public static final int H          = 720;
    public static final int HEADER_H   = 100;
    public static final int SUB_H      = 68;

    // --- Card Names ---
    public static final String LOADING      = "LOADING";
    public static final String LOGIN        = "LOGIN";
    public static final String SIGNUP       = "SIGNUP";
    public static final String DASHBOARD    = "DASHBOARD";
    public static final String DETAILS      = "DETAILS";
    public static final String ATTENDANCE   = "ATTENDANCE";
    public static final String PAYMENT      = "PAYMENT";
    public static final String TIMETABLE    = "TIMETABLE";
    public static final String PROFILE      = "PROFILE";
    public static final String NOTIFICATION = "NOTIFICATION";

    // --- Colors ---
    public static final Color BG          = new Color(0xE9E9E9);
    public static final Color CARD_COLOR  = new Color(0xC6C6C8);
    public static final Color TABLE_COLOR = new Color(0xD3D3D3);
    public static final Color GREEN       = new Color(0x08F000);
    public static final Color GREEN_LIGHT = new Color(0xBFEFC3);
    public static final Color RED         = new Color(0xFF2020);
    public static final Color MAGENTA     = new Color(0xE040FB);
    public static final Color BLUE        = new Color(0x0000CC);
    public static final Color SEPARATOR   = new Color(0xAAAAAA);
    public static final Color WHITE       = Color.WHITE;
    public static final Color BLACK       = Color.BLACK;

    // --- Design ---
    public static final int STROKE    = 2;
    public static final int CARD_ARC  = 60;
    public static final int FIELD_ARC = 30;
    public static final int TABLE_ARC = 24;

    // --- Demo/Offline Credentials ---
    public static final String DEMO_USER = "student1";
    public static final String DEMO_PASS = "password";
}
