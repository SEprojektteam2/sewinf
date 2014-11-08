package com.gwt.client;

import java.sql.Connection;

import com.google.gwt.visualization.client.DataTable;
import com.gwt.server.MySQLConnection;

import java.util.ArrayList;

public class DataManager {
	Connection connection;
	
	//Create connection to mysqldatabase
	private void connectToDatabase(){ 
		MySQLConnection database = new MySQLConnection("173.194.253.240:3306","root","","agrar","agraralphav1:agrar");
	    if(database.connect()){
	    	System.out.println("<html><head></head><body>Connection Started</body></html>");
	    }
	    Connection connection = database.returnConnection();
	}
	
	private void calculateInterpolation(){
		//evt. Klassse die die Berechnung übernimmt
		//Achtung Zahl sind String
	}
	
	
	public void getDataTable(String country, String product, String type){
		// "null" nicht angegeben => nicht Beachtung der varaible 
		//private DataTable TableDATA;
		//-> siehe prepareData() VisualizationManager
		//evt. absteigend nach jahr sortieren und über geben
		if(country=="null"){// country=null when world is selected 
				
		}
		if(product=="null"){
			
		}
		if(type=="null"){
			
		}
		
		
		
	}
	
	public void getCountries(){
		//Array mit Strings als Rückgabe
		//limit 1 bei der Abfrage (entfernt die Dupletten)
		connectToDatabase();
		ArrayList<String> countries = new ArrayList<String>();
		countries.add(0, "Hi Mom");
		//preparedStatement stm = connection.prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
		
	}
	
	public void saveUser(String username, String param1, String param2){
		//coming soon
	}
	
	public void getUser(String username){
		//coming soon
	}
	
	
}
