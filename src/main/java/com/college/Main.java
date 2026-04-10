package com.college;

import com.college.core.AppInitializer;

import javax.swing.*;

/**
 * Entry point for College Management System.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppInitializer::start);
    }
}
