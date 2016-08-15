package edu.sjsu.cmpe202.report;

import edu.sjsu.cmpe202.db.dao.ReportDao;

/**
 * @author rwatsh on 8/15/16.
 */
public class RidesByMemberReport implements Report {
    private int memberId;

    public RidesByMemberReport(int memberId) {
        this.memberId = memberId;
    }

    @Override
    public void showReport() {
        System.out.println("Generating report....");
        System.out.println(ReportDao.getRidesByMember(memberId));
    }
}
