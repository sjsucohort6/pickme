package edu.sjsu.cmpe202.dbaLayer;

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
    private static Sql2o sql2o;

	static {
        sql2o = new Sql2o(DB_URL, userName, password);

        //initialize the column mappings across all DB tables to field names in POJO classes
        Map<String, String> colMaps = new HashMap<String,String>();
        colMaps.put("member_id", "memberId");
        colMaps.put("first_name", "firstName");
        colMaps.put("last_name", "lastName");
        colMaps.put("SHORT_DESC", "shortDescription");

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
