package com.photopia.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {


	
	private Connection connection;

	private static final String DB_SCHEMA = "final_project";
	private static final String DB_PORT = "3306";
	private static final String DB_HOST = "localhost";
	private static final String DB_USERNAME = "lility";
	private static final String DB_PASSWORD = "QWEasd123";


	public DBConnection() throws SQLException, ClassNotFoundException  {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_SCHEMA,
				DB_USERNAME, DB_PASSWORD);
	}


	public Connection getConnection() {
		return connection;
	}

}
