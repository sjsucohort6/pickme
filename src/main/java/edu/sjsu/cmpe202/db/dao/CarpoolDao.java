package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.cli.CarpoolStatus;
import edu.sjsu.cmpe202.cli.RideStatus;
import edu.sjsu.cmpe202.cli.VehicleStatus;
import edu.sjsu.cmpe202.db.SQLConnection;
import edu.sjsu.cmpe202.db.domain.CarpoolDetails;
import edu.sjsu.cmpe202.db.domain.Location;
import edu.sjsu.cmpe202.db.domain.Member;
import edu.sjsu.cmpe202.db.domain.Vehicle;
import org.sql2o.Connection;

import java.util.List;

/**
 * @author rwatsh on 8/13/16.
 */
public class CarpoolDao {
    public static Integer createCarpool(CarpoolDetails details) {
        String sql =
                "INSERT INTO carpool_details (vehicle_id, driver_id, passenger_count, status, route) " +
                        "VALUES (:vehicle_id, :driver_id, :passenger_count, :status, :route)";

        try (Connection con = (new SQLConnection()).getConnection()) {
            Integer poolId = con.createQuery(sql)
                    .addParameter("vehicle_id", details.getVehicleId())
                    .addParameter("driver_id", details.getDriverId())
                    .addParameter("passenger_count", details.getPassengerCount())
                    .addParameter("status", details.getStatus())
                    .addParameter("route", details.getStatus())
                    .executeUpdate().getKey(Integer.class);
            return poolId;
        }
    }

    public static Vehicle findFirstAvailableVehicle() {
        String vehicleSql = "SELECT * FROM vehicle WHERE status = '" + VehicleStatus.AVAILABLE.name() + "'";

        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(vehicleSql)
                    .executeAndFetchFirst(Vehicle.class);
        }
    }

    public static Member findDriverForVehicle(int vehicleId) {
        String driverSql = "SELECT member_id FROM driver_vehicle WHERE vehicle_id = " + vehicleId + " AND is_current = 1";

        try (Connection con = (new SQLConnection()).getConnection()) {
            Integer driverId = con.createQuery(driverSql)
                    .executeScalar(Integer.class);
            if (driverId != null) {
                String sql = "SELECT * FROM member WHERE member_id = " + driverId;
                return con.createQuery(sql).executeAndFetchFirst(Member.class);
            }
        }
        return null;
    }

    public static Location getLocation(int locationId) {
        String sql = "SELECT * FROM location WHERE location_id = " + locationId;

        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(sql)
                    .executeAndFetchFirst(Location.class);
        }
    }

    public static List<CarpoolDetails> findCarpoolsByStatus(String status) {
        String sql = "SELECT * FROM carpool_details WHERE status = '" + status + "'";

        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(sql)
                    .executeAndFetch(CarpoolDetails.class);
        }
    }

    public static List<Integer> findRidesInACarpool(int poolId) {
        String sql = "SELECT ride_id FROM carpool_details WHERE pool_id = :pool_id";

        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(sql)
                    .addParameter("pool_id", poolId)
                    .executeScalarList(Integer.class);
        }
    }

    public static void dispatchCarpool(CarpoolDetails carpoolDetails) {
        List<Integer> ridesList = findRidesInACarpool(carpoolDetails.getPoolId());
        RideDao.updateRideStatus(RideStatus.IN_PROGRESS.name(), ridesList);
        updateCarpoolStatus(carpoolDetails.getPoolId(), CarpoolStatus.DISPATCHED.name());
    }

    public static void updateCarpoolStatus(int poolId, String status) {
        final String sql = "UPDATE carpool_details set status = :status where pool_id = :pool_id";

        try (Connection con = (new SQLConnection()).getConnection()) {
             con.createQuery(sql)
                     .addParameter("pool_id", poolId)
                     .addParameter("status", status)
                     .executeUpdate();
        }

    }
}
