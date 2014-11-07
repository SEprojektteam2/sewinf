package com.gwt.client;

import java.sql.Connection;

import com.google.gwt.visualization.client.DataTable;
import com.gwt.server.MySQLConnection;

public class Datamanager {
	
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
	
	
	public void getData(String country, String product, String type){// "0" nicht angegeben =>
		//private DataTable TableDATA;
		//-> siehe prepareData() VisualizationManager
		
	}
	
	public void saveUser(String username, String param1, String param2){
		//coming soon
	}
	
	public void getUser(String username){
		//coming soon
	}
	
	
}
