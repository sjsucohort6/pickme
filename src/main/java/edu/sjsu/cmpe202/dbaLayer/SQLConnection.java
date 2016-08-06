package edu.sjsu.cmpe202.dbaLayer;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class SQLConnection implements AutoCloseable {
	String url = "jdbc:mysql://localhost:3306/";
	String DB =  "cmpe202";//change it to cmpe202
    String userName = "cmpe202";
	String password = "cmpe202";
	String DB_URL = url+DB;
	Connection connection = null;
	Sql2o sql2o = new Sql2o(DB_URL, userName, password);
	
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
