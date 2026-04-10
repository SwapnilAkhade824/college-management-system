package com.college.controller;

import com.college.core.DBConnection;
import com.college.util.DemoData;

public class PaymentController {

    public String[] getColumns() { return DemoData.paymentCols(); }

    public Object[][] getPaymentData(String statusFilter, String typeFilter) {
        Object[][] all = DBConnection.isDemoMode() ? DemoData.paymentRows() : DemoData.paymentRows();
        return filter(all, statusFilter, typeFilter);
    }

    private Object[][] filter(Object[][] rows, String status, String type) {
        if ((status == null || status.equals("All")) &&
            (type   == null || type.equals("All Modes"))) return rows;
        java.util.List<Object[]> out = new java.util.ArrayList<>();
        for (Object[] r : rows) {
            boolean stMatch = status == null || status.equals("All") ||
                              r[4].toString().equalsIgnoreCase(status);
            boolean tyMatch = type   == null || type.equals("All Modes") ||
                              r[5].toString().equalsIgnoreCase(type);
            if (stMatch && tyMatch) out.add(r);
        }
        return out.toArray(new Object[0][]);
    }

    public String[] getPaymentModes() {
        return new String[]{"All Modes", "Online", "DD", "Cash"};
    }
}
