package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.db.SQLConnection;
import edu.sjsu.cmpe202.db.domain.RidesByMember;
import org.sql2o.Connection;

import java.util.List;

/**
 * @author rwatsh on 8/15/16.
 */
public class ReportDao {
    public static List<RidesByMember> getRidesByMember(int memberId) {
        String rideStatus = "SELECT * FROM rides_by_member_view";

        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(rideStatus)
                    .executeAndFetch(RidesByMember.class);
        }
    }
}
