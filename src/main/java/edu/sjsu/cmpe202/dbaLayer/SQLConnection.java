package edu.sjsu.cmpe202.dbaLayer;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class SQLConnection {
	String url = "jdbc:mysql://localhost:3307/"; 
	String DB =  "courseregistration";//change it to cmpe202
    String userName = "root";
	String password = "root";
	String DB_URL = url+DB;
	Connection connection = null;
	Sql2o sql2o = new Sql2o(DB_URL, userName, password);
	
	public Connection getConnection()
	{   
		if(connection == null)
		  connection = sql2o.open();
		return connection;
	}
	
}
