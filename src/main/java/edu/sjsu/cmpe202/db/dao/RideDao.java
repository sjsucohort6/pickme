package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.cli.Ride;
import edu.sjsu.cmpe202.cli.RideStatus;
import edu.sjsu.cmpe202.db.SQLConnection;
import edu.sjsu.cmpe202.db.domain.RideDetails;
import org.sql2o.Connection;
import org.sql2o.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rwatsh on 8/12/16.
 */
public class RideDao {
    public static void addRideRequest(Ride ride)
    {
    	String rideRequest = " INSERT into ride_details(user_id,source_id,dest_id,create_date,start_date,status)"
    			+ "VALUES(:user_id,:source_id,:dest_id,:create_date,:start_date,:status)";
        try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(rideRequest)
                    .addParameter("user_id",ride.getUserid())
                    .addParameter("source_id", ride.getSourceid())
                    .addParameter("dest_id",ride.getDestid())
                    .addParameter("create_date", ride.getCreateDate())
                    .addParameter("start_date", ride.getStartDate())
                    .addParameter("status", ride.getStatus())
                    .executeUpdate();
        }

    }

    public static void deleteRequestedRide(String ride_id)
    {
    	String cancelRide = "UPDATE ride_details set status = :status where ride_id = :ride_idparam";
        try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(cancelRide)
                    .addParameter("ride_idparam",ride_id)
                    .addParameter("status", RideStatus.CANCELED.name())
                    .executeUpdate();
        }
    }

    public static String getRideStatus(String ride_id)
    {
    	//int memberID = getRiderID(ride_id);
    	String rideStatus = "Select status from ride_details where ride_id = :ride_idparam";
        try (Connection con = (new SQLConnection()).getConnection()) {
            String rides =  con.createQuery(rideStatus)
                    .addParameter("ride_idparam",ride_id)
                    .executeScalar(String.class);
            return rides == null? null : rides;
        }
    }
    
    public static Integer getRiderID(String ride_id)
    {
    	String member_idSQL = "Select member_id from member where email = :ride_idparam";
        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(member_idSQL)
                    .addParameter("ride_idparam",ride_id)
                    .executeScalar(Integer.class);
            //return m.getmemberId();
        }
    }

    public static List<RideDetails> getRidesByStatus(String status) {
        String rideStatus = "SELECT * FROM ride_details WHERE status = '" + status + "'";

        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(rideStatus)
                    .executeAndFetch(RideDetails.class);
        }
    }

    public static void updateRideStatus(List<RideDetails> rideList, String status) {

        List<Integer> rideIdList = new ArrayList<>();
        for (RideDetails r: rideList) {
            rideIdList.add(r.getRideId());
        }

        updateRideStatus(status, rideIdList);
    }

    public static void updateRideStatus( String status, List<Integer> rideIdList) {
        final String sql = "UPDATE ride_details set status = :status where ride_id = :ride_idparam";

        try (Connection con = SQLConnection.sql2o.beginTransaction()) {
            Query query = con.createQuery(sql);

            for (Integer rideId: rideIdList) {
                query.addParameter("ride_idparam", rideId)
                        .addParameter("status", status)
                        .addToBatch();
            }

            query.executeBatch(); // executes entire batch
            con.commit();         // remember to call commit(), else sql2o will automatically rollback.
        }
    }
}
