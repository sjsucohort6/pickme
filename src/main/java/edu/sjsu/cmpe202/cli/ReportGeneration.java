package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.dao.MembershipDao;
import edu.sjsu.cmpe202.db.domain.Member;
import edu.sjsu.cmpe202.report.CompositeReport;
import edu.sjsu.cmpe202.report.RidesByMemberReport;

import java.util.Scanner;

/**
 * @author rwatsh on 8/15/16.
 */
public class ReportGeneration {

    public static void printReportMenu(){
        System.out.println("\t [1] Rides by user report");
        System.out.println("\t [2] Go back to main menu");
    }

    public void handleRidesByMemberReport() {
        Scanner scanner = new Scanner(System.in);
        Member member = null;
        while (member == null) {
            System.out.println("\t Enter member email: ");
            String email = scanner.nextLine();

            member = MembershipDao.getMemberByEmail(email);
            if (member == null) {
                System.out.println("No such member found: " + email);
            }
        }

        CompositeReport r = new CompositeReport();
        r.addReport(new RidesByMemberReport(member.getMemberId()));
        r.showReport();
    }
}
