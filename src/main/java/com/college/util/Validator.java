package com.college.util;

public final class Validator {

    private Validator() {}

    public static boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean validUsername(String s) {
        return notEmpty(s) && s.trim().length() >= 3;
    }

    public static boolean validPassword(String s) {
        return notEmpty(s) && s.length() >= 6;
    }

    public static boolean passwordsMatch(String p1, String p2) {
        return p1 != null && p1.equals(p2);
    }

    public static boolean validEmail(String s) {
        return notEmpty(s) && s.contains("@") && s.contains(".");
    }

    public static boolean validPhone(String s) {
        return notEmpty(s) && s.trim().matches("\\d{10}");
    }

    public static boolean validPasswordChange(String oldPass, String newPass, String confirm) {
        return notEmpty(oldPass) && validPassword(newPass) && passwordsMatch(newPass, confirm);
    }
}
