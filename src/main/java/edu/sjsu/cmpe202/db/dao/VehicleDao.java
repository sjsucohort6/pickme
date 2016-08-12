package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.cli.VehicleRegistration;
import edu.sjsu.cmpe202.db.SQLConnection;
import org.sql2o.Connection;

import java.util.List;

/**
 * @author rwatsh on 8/12/16.
 */
public class VehicleDao {
    public static void createVehicle(VehicleRegistration vehicle) {

    	String vehicleinfo =
    			"INSERT INTO vehicle (owner_id,name,capacity)" +
    	                   "VALUES(:owner_id, :name, :capacity)";
        try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(vehicleinfo)
                    .addParameter("owner_id",vehicle.getOwnerID())
                    .addParameter("name", vehicle.getRegistration())
                    .addParameter("capacity", vehicle.getCapacity())
                    .executeUpdate();
        }

    }

    public static void deleteVehicle(String vehicleID)
    {
    	String deleteVehicle = " DELETE * from vehicle where vehicle_id = :vehicle_id" ;
    	try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(deleteVehicle)
                    .addParameter("vehicle_id",vehicleID)
                    .executeUpdate();
        }
    }

    public static List<VehicleRegistration> showVehiclesOfOwner(String OwnerID)
    {
    	String vehicles = "SELECT * FROM vehicle where owner_id = :owner_id";
    	List<VehicleRegistration> vehiclers;
    	try (Connection con = (new SQLConnection()).getConnection()) {
           vehiclers =  con.createQuery(vehicles)
                    .addParameter("owner_id",OwnerID)
                    .executeAndFetch(VehicleRegistration.class);
        }

    	return vehiclers;
    }
}
