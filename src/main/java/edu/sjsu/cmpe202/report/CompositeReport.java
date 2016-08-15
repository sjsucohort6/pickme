package edu.sjsu.cmpe202.report;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rwatsh on 8/15/16.
 */
public class CompositeReport implements Report {
    private List<Report> reportsList = new ArrayList<>();

    @Override
    public void showReport() {
        for (Report report: reportsList) {
            report.showReport();
        }
    }

    public void addReport(Report report) {
        reportsList.add(report);
    }

    public void removeReport(Report report) {
        reportsList.remove(report);
    }
}
