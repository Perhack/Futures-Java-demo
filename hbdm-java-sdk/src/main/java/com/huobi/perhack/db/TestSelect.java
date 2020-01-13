package com.huobi.perhack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestSelect {

	public static Connection getConnection(){
		
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/datahub.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		return conn;
		
	}
	
	public static void main(String []args) {
		
		Queue<String> periods = new LinkedBlockingQueue<String>();
		Connection connection = getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			ResultSet rs = statement.executeQuery("select * from trade");

			while (rs.next()) {
			    String period = rs.getString("ts");
			    periods.add(period);
		    }
		    System.out.println(periods.size());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
