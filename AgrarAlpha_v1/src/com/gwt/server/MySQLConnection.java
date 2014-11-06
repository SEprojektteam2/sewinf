package com.gwt.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;

public class MySQLConnection {
	private String host, user, password, db, appengineSource;
	Connection conn;
	public MySQLConnection(String host, String user, String password, String db, String appengineSource){
		this.host = host;
		this.user = user;
		this.password = password;
		this.db = db;
		this.appengineSource = appengineSource;
	}
	
	public boolean connect(){
		try {
		      String url;
			if (SystemProperty.environment.value() ==
		          SystemProperty.Environment.Value.Production) {
		        // Load the class that provides the new "jdbc:google:mysql://" prefix.
		        Class.forName("com.mysql.jdbc.GoogleDriver");
		        url = "jdbc:google:mysql://"+appengineSource+"/"+ db +"?user=" + user;
		        if(!password.equals("")){
		        	url = url + "?password=" + password;
		        }
		      } else {
		        // Local MySQL instance to use during development.
		        Class.forName("com.mysql.jdbc.Driver");
		        url = "jdbc:mysql://"+ host +":3306/"+ db +"?user=" + user;
		        // Alternatively, connect to a Google Cloud SQL instance using:
		        // jdbc:mysql://ip-address-of-google-cloud-sql-instance:3306/guestbook?user=root
		        if(!password.equals("")){
		        	url = url + "?password=" + password;
		        }
		      }
			  conn = DriverManager.getConnection(url);
		      return true;
		    } catch (Exception e) {
		      e.printStackTrace();
		      return false;
		    }
	}
	
	public int executeUpdate(String statement){
    	int success = 2;
    	try {
		PreparedStatement stmt = conn.prepareStatement(statement);
		success = stmt.executeUpdate();
		} catch (SQLException e) {
			success = -1;
		}
		return success;
	}
}
