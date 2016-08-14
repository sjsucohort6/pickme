package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.scheduler.CarpoolGroup;

/**
 * @author rwatsh on 8/13/16.
 */
public class CarpoolDao {
    public static void createCarpool(CarpoolGroup carpoolGroup) {
        String sql =
                "INSERT INTO carpool_details (vehicle_id, driver_id, passenger_count, status, route) " +
                        "VALUES (:vehicle_id, :driver_id, :passenger_count, :status, :route)";

        /*try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(sql)
                    .addParameter("vehicle_id", membership.getFirstName())
                    .addParameter("last_name", membership.getLastName())
                    .addParameter("dob", membership.getDob())
                    .addParameter("address", membership.getAddress())
                    .addParameter("contact", membership.getPhone())
                    .addParameter("email", membership.getEmail())
                    .addParameter("is_driver", "N")
                    .executeUpdate();
        }*/
    }
}
