package com.example.reports;

/**
 * Proxy responsibilities:
 * - access check
 * - lazy loading
 * - caching of RealReport within the same proxy
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        System.out.println("[proxy] request report=" + reportId + " user=" + user.getName() + " role=" + user.getRole());

        if (!accessControl.canAccess(user, classification)) {
            System.out.println("[proxy] access denied for classification=" + classification);
            return;
        }

        System.out.println("[proxy] access granted for classification=" + classification);

        if (realReport == null) {
            System.out.println("[proxy] creating real report " + reportId);
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("[proxy] reusing cached real report " + reportId);
        }

        realReport.display(user);
    }
}