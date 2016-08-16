package edu.sjsu.cmpe202.report.test;

import edu.sjsu.cmpe202.db.dao.MembershipDao;
import edu.sjsu.cmpe202.db.domain.Member;
import edu.sjsu.cmpe202.report.CompositeReport;
import edu.sjsu.cmpe202.report.RidesByMemberReport;
import org.junit.Test;

/**
 * @author rwatsh on 8/16/16.
 */
public class ReportTest {
    @Test
    public void testReports() {
        String email = "abc@gmail.com";
        Member member = MembershipDao.getMemberByEmail(email);
        CompositeReport r = new CompositeReport();
        r.addReport(new RidesByMemberReport(member.getMemberId()));
        r.showReport();
    }
}
