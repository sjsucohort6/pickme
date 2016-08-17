package edu.sjsu.cmpe202.report;

import edu.sjsu.cmpe202.cli.TableBuilder;
import edu.sjsu.cmpe202.db.dao.ReportDao;
import edu.sjsu.cmpe202.db.domain.RidesByMember;

import java.util.List;

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
        List<RidesByMember> ridesByMemberList = ReportDao.getRidesByMember(memberId);
        TableBuilder t = new TableBuilder();
        t.addRow("First Name", "Last Name", "Email", "Source", "Destination", "Create Date", "Pickup Time", "Status", "Payment Status");
        t.addRow("----------", "----------", "-----", "-----", "----------", "----------", "------------", "-----", "-----------");

        for (RidesByMember r: ridesByMemberList) {
            t.addRow(r.getFirstName(), r.getLastName(), r.getEmail(), r.getLocation(), r.getDestLocation(), r.getCreateDate().toString(), r.getStartDate().toString(), r.getStatus(), r.getPaymentStatus());
        }
        System.out.println(t.toString());
    }
}
