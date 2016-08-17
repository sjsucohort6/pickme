package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.cli.ParkingHandler;
import edu.sjsu.cmpe202.db.SQLConnection;
import edu.sjsu.cmpe202.db.domain.Parking;
import org.sql2o.Connection;

import java.util.List;

/**
 * Created by swethamuchukota on 8/13/16.
 */
public class ParkingDetailsDao {

    public static void addParkingRequest(ParkingHandler pDetails)
    {
        String parkingRequest = " INSERT into parking_details(parker_id,parking_id,start_time,end_time,status)"
                + "VALUES(:parker_id,:parking_id,:start_time,:end_time,:status)";
        try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(parkingRequest)
                    .addParameter("parker_id",pDetails.getParkerID())
                    .addParameter("parking_id", pDetails.getParkingID())
                    .addParameter("start_time",pDetails.getStartTime())
                    .addParameter("end_time", pDetails.getEndTime())
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


    public static Parking createParking(ParkingHandler parkingHandler) {
        String sql =
                "INSERT INTO parking (owner_id,location_id,capacity,status)" +
                        "VALUES(:owner_id, :location_id, :capacity, :status)";
        try (Connection con = (new SQLConnection()).getConnection()) {
            Integer parkingId = con.createQuery(sql)
                    .addParameter("owner_id",parkingHandler.getOwnerID())
                    .addParameter("location_id", parkingHandler.getLocationId())
                    .addParameter("capacity", parkingHandler.getCapacity())
                    .addParameter("status", parkingHandler.getStatus())
                    .executeUpdate().getKey(Integer.class);

            String sql1 = "SELECT * FROM parking WHERE parking_id = :parkingId";
            return con.createQuery(sql1)
                    .addParameter("parkingId",parkingId)
                    .executeAndFetchFirst(Parking.class);
        }
    }

    public static List<Parking> listParking() {
        String sql1 = "SELECT * FROM parking";
        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(sql1)
                    .executeAndFetch(Parking.class);
        }

    }
}
