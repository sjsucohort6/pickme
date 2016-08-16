package edu.sjsu.cmpe202.db;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper class for SQL2O library.
 *
 * @author swetha
 */
public class SQLConnection implements AutoCloseable {
	private final static String url = "jdbc:mysql://localhost:3306/";
	private final static String DB =  "pickme";
    private final static String userName = "cmpe202";
	private final static String password = "cmpe202";
	private final static String DB_URL = url+DB;
	private Connection connection = null;
    public static Sql2o sql2o;

	static {
        sql2o = new Sql2o(DB_URL, userName, password);

        //initialize the column mappings across all DB tables to field names in POJO classes
        Map<String, String> colMaps = new HashMap<String,String>();
        colMaps.put("member_id", "memberId");
        colMaps.put("first_name", "firstName");
        colMaps.put("last_name", "lastName");
        colMaps.put("is_driver", "isDriver");

        colMaps.put("pool_id", "poolId");
        colMaps.put("vehicle_id", "vehicleId");
        colMaps.put("driver_id", "driverId");
        colMaps.put("passenger_count", "passengerCount");

        colMaps.put("ride_id", "rideId");
        colMaps.put("start_time", "startTime");

        colMaps.put("license_number", "licenseNumber");
        colMaps.put("expiry_date", "expiryDate");
        colMaps.put("registration_id", "registrationId");

        colMaps.put("location_id", "locationId");

        colMaps.put("notify_id", "notifyId");
        colMaps.put("notifyuser_id", "notifyUserId");

        colMaps.put("payment_id", "paymentId");
        colMaps.put("card_number", "cardNumber");
        colMaps.put("card_type", "cardType");
        colMaps.put("user_id", "userId");
        colMaps.put("source_id", "sourceId");
        colMaps.put("dest_id", "destId");
        colMaps.put("create_date", "createDate");
        colMaps.put("start_date", "startDate");

        colMaps.put("location_1", "location1");
        colMaps.put("location_2", "location2");

        colMaps.put("owner_id", "ownerId");
        colMaps.put("payment_status", "paymentStatus");

        sql2o.setDefaultColumnMappings(colMaps);
    }

	
	public Connection getConnection()
	{   
		if(connection == null)
		  connection = sql2o.open();
		return connection;
	}

	public static void main(String[] args) {

		SQLConnection sqlConnection = new SQLConnection();
		sqlConnection.getConnection();
	}

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
