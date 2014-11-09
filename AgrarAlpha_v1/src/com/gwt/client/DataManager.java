package com.gwt.client;

import java.sql.*;

import com.google.gwt.visualization.client.DataTable;
import com.gwt.server.MySQLConnection;
import com.google.appengine.api.utils.SystemProperty;
import com.mysql.jdbc.Connection;

import java.util.ArrayList;

public class DataManager {
	Connection conn;
	
	//Create connection to mysqldatabase
	private void connectToDatabase(){ 
		MySQLConnection database = new MySQLConnection("173.194.253.240:3306","root","","agrar","agraralphav1:agrar");
	    if(database.connect()){
	    	System.out.println("<html><head></head><body>Connection Started</body></html>");
	    }
	    conn = database.returnConnection();
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
	
	public ArrayList<String> getCountries(){
		//Array mit Strings als Rückgabe
		//limit 1 bei der Abfrage (entfernt die Dupletten)
		ArrayList<String> countries = new ArrayList<String>();
		countries.add(0, "World");
		
		/*connectToDatabase();
		// if you only need a few columns, specify them by name instead of using "*"
		String query = "SELECT distinct AreaName FROM agrar WHERE Domain = Annual population";
	 
		// create the java statement
		Statement st = null;
		try {
			st = conn.createStatement();
	       
			// execute the query, and get a java resultset
			ResultSet rs = null;
			rs = st.executeQuery(query);
		
			// iterate through the java resultset
			int i=0;
		
			while (rs.next())
			{
				String country = rs.getString("bal");
				countries.add(i, country);
				i++;
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return (countries);
	}
	
	public void saveUser(String username, String param1, String param2){
		//coming soon
	}
	
	public void getUser(String username){
		//coming soon
	}
	
	
}
