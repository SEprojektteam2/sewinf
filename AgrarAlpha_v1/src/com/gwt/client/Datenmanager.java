package com.gwt.client;

import java.sql.Connection;

import com.gwt.server.MySQLConnection;

public class Datenmanager {
	private void connectToDatabase(){
		
		MySQLConnection database = new MySQLConnection("173.194.253.240:3306","root","","agrar","agraralphav1:agrar");
	    if(database.connect()){
	    	System.out.println("<html><head></head><body>Connection Started</body></html>");
	    }
	    Connection conn = database.returnConnection();
	}
}
