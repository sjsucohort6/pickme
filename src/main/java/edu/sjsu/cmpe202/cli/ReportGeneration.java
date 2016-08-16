package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.dao.MembershipDao;
import edu.sjsu.cmpe202.db.dao.ReportDao;
import edu.sjsu.cmpe202.db.domain.Member;
import edu.sjsu.cmpe202.db.domain.RidesByMember;

import java.util.List;
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

        List<RidesByMember> ridesByMemberList = ReportDao.getRidesByMember(member.getMemberId());

        for (RidesByMember ridesByMember : ridesByMemberList) {
            System.out.println(ridesByMember);
        }
    }
}
