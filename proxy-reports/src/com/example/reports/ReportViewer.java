package com.example.reports;

/**
 * Viewer depends on the Report abstraction so clients can use a proxy.
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
