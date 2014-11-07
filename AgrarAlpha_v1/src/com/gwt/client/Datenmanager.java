package com.gwt.client;

import java.sql.Connection;

import com.gwt.server.MySQLConnection;

public class Datenmanager {
	
	//Create connection to mysqldatabase
	private void connectToDatabase(){ 
		MySQLConnection database = new MySQLConnection("173.194.253.240:3306","root","","agrar","agraralphav1:agrar");
	    if(database.connect()){
	    	System.out.println("<html><head></head><body>Connection Started</body></html>");
	    }
	    Connection conn = database.returnConnection();
	}
	
	private void calculateInterpolation(){
		//evt. Klassse die die Berechnung übernimmt
		//Achtung Zahl sind String
	}
	
	
	public void getDataBy(String param1, String param2){
		
	}
	
	public void saveUser(String username, String param1, String param2){
		
	}
	
	public void getUser(String username){
		
	}
	
	
}
