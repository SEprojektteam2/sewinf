package com.gwt.client;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;/**/

import com.google.gwt.visualization.client.DataTable;
import com.gwt.client.MySQLConnection;
import com.google.appengine.api.utils.SystemProperty;
//import com.google.cloud.sql.jdbc.ResultSet;
import com.mysql.jdbc.*;

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
	
	
	private ArrayList<String[]> readDatabase(String query, String searchingVar, String outputVar){
		ArrayList<String[]> result = new ArrayList<String[]>();
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
				String[] resultTemp = new String[3];
				resultTemp[0] = rs.getString(searchingVar);
				resultTemp[1] = rs.getString("Year");
				resultTemp[2] = rs.getString(outputVar);
				result.add(i, resultTemp);
				i++;
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public DataTable[] getDataTable(String country, String product, String type){
		// "null" nicht angegeben => nicht Beachtung der varaible 
		//private DataTable TableDATA;
		//-> siehe prepareData() VisualizationManager
		//evt. absteigend nach jahr sortieren und über geben
		// Achtung Import: Import Quantity gleich für Export!!!
		
		ArrayList<String[]> result = new ArrayList<String[]>();
		DataTable[] DATA = new DataTable[0];
		
		connectToDatabase();
		
		String query="null";
		String searchingVar="null";
		String outputVar="null";
		
		// country=null when world is selected and product is given and type is given => Output: Country + Year + Value
		if(country=="null"){ 
			query = "SELECT AreaName, Year, Value FROM agrar WHERE ElementName = '"+type+"' AND ItemName = '"+product+"' ORDER BY Year ASC";
		}
		//
		if(product=="null"){
			query = "SELECT ItemName, Year, Value FROM agrar WHERE ElementName = '"+type+"' AND AreaName = '"+country+"' ORDER BY Year ASC";
		}
		//
		if(type=="null"){
			query = "SELECT ElementName, Year, Value FROM agrar WHERE ItemName = '"+product+"' AND AreaName = '"+country+"' ORDER BY Year ASC";
		}
		
		result = readDatabase(query,searchingVar,outputVar);
		
		return DATA;
		
	}
	
	public ArrayList<String> getCountries(){
		//Array mit Strings als Rückgabe
		//limit 1 bei der Abfrage (entfernt die Dupletten)
		ArrayList<String> countries = new ArrayList<String>();
		//countries.add(0, "World");
		
		connectToDatabase();
		// if you only need a few columns, specify them by name instead of using "*"
		String query = "SELECT distinct AreaName FROM agrar WHERE Domain = 'Annual population'";
		
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
				String resultTemp = rs.getString("AreaName");
				countries.add(i, resultTemp);
				i++;
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (countries);
	}
	
	public void saveUser(String username, String param1, String param2){
		//coming soon
	}
	
	public void getUser(String username){
		//coming soon
	}
	
	
}
