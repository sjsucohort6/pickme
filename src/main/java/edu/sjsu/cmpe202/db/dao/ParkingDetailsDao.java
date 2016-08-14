package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.cli.ParkingDetails;
import edu.sjsu.cmpe202.db.SQLConnection;
import edu.sjsu.cmpe202.db.domain.RideDetails;
import org.sql2o.Connection;
import edu.sjsu.cmpe202.db.domain.Member;

/**
 * Created by swethamuchukota on 8/13/16.
 */
public class ParkingDetailsDao {

    public static void addParkingRequest(ParkingDetails pDetails)
    {
        String parkingRequest = " INSERT into parking_details(parker_id,parking_id,start_time,end_time)"
                + "VALUES(:parker_id,:parking_id,:start_time,:end_time)";
        try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(parkingRequest)
                    .addParameter("parker_id",pDetails.getParkerID())
                    .addParameter("parking_id", pDetails.getParkingID())
                    .addParameter("start_time",pDetails.getStartTime())
                    .addParameter("start_time", pDetails.getEndTime())
                    .addParameter("status", pDetails.getStatus())
                    .executeUpdate();
        }

    }

    public static void cancelParkingRequest(int pDetailsID)
    {
        String parkingRequest = "Update parking_details set status = :status where parking_details_id=:parking_details_id ";
        try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(parkingRequest)
                    .addParameter("parking_details_id",pDetailsID)
                    .addParameter("status","Cancelled")
                    .executeUpdate();
        }

    }




}
