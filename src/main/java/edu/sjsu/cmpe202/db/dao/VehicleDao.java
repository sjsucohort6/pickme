package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.cli.VehicleRegistration;
import edu.sjsu.cmpe202.db.SQLConnection;
import edu.sjsu.cmpe202.db.domain.Vehicle;
import org.sql2o.Connection;

import java.util.List;

/**
 * @author rwatsh on 8/12/16.
 */
public class VehicleDao {
    public static Vehicle createVehicle(VehicleRegistration vehicle) {

    	String vehicleinfo =
    			"INSERT INTO vehicle (owner_id,registration_id,capacity,status)" +
    	                   "VALUES(:owner_id, :registration_id, :capacity, :status)";
        try (Connection con = (new SQLConnection()).getConnection()) {
            Integer vehicleId = con.createQuery(vehicleinfo)
                    .addParameter("owner_id",vehicle.getOwnerID())
                    .addParameter("registration_id", vehicle.getRegistration_id())
                    .addParameter("capacity", vehicle.getCapacity())
                    .addParameter("status", vehicle.getStatus())
                    .executeUpdate().getKey(Integer.class);

            String sql = "SELECT * FROM vehicle WHERE vehicle_id = :vehicleId";
            return con.createQuery(sql)
                    .addParameter("vehicleId",vehicleId)
                    .executeAndFetchFirst(Vehicle.class);
        }

    }

    public static void deleteVehicle(String vehicleID)
    {
    	String deleteVehicle = "Update vehicle set status = 'NA' where vehicle_id = :vehicle_id" ;
    	try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(deleteVehicle)
                    .addParameter("vehicle_id",vehicleID)
                    .executeUpdate();
        }
    }

    public static List<VehicleRegistration> showVehiclesOfOwner(int OwnerID)
    {
    	String vehiclesSql = "SELECT * FROM vehicle where owner_id = :owner_id";
    	List<VehicleRegistration> vehicles;
    	try (Connection con = (new SQLConnection()).getConnection()) {
           vehicles =  con.createQuery(vehiclesSql)
                    .addParameter("owner_id",OwnerID)
                    .executeAndFetch(VehicleRegistration.class);
        }

    	return vehicles;
    }
}
