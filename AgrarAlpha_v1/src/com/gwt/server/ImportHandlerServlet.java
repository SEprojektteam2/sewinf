package com.gwt.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.google.appengine.api.ThreadManager;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreInputStream;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.com.bytecode.opencsv.CSVReader;
//
//Import external class for simple csv handling


	@SuppressWarnings("serial")
	public class ImportHandlerServlet extends HttpServlet {
		public static final Logger log = Logger.getLogger(ImportHandlerServlet.class.getName());
		private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();  	
		private int noOfThreads =0;
		
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			PrintWriter out = resp.getWriter();
			Date start = new Date();
		
			
			Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
			List<BlobKey> bkList = blobs.get("importCSV");
			BlobKey blobKey = bkList.get(0);
			BlobstoreInputStream blobStream = new BlobstoreInputStream(blobKey);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(blobStream));
			ArrayList<String[][]> arrays= new ArrayList<String[][]>(); 
			CSVReader csvReader = null;
			
			MySQLConnection database = new MySQLConnection("173.194.253.240:3306","root","","agrar","agraralphav1:agrar");
		    if(database.connect()){
		    	out.println("<html><head></head><body>Connection Started</body></html>");
		    }
		   
		    //TEst
			Connection conn = database.returnConnection();
			//Print out first line to check if BufferedReader is empty
			while(bufferedReader.readLine() != null){
			
			//New CSVReader object reading from bufferedReader
			csvReader = new CSVReader(bufferedReader);
			
			//Add csv-Fields to two-dimensional array 
			List<String[]> rows = csvReader.readAll();
			//String output[][];
			arrays.add(rows.toArray(new String[rows.size()][]));
			}
			//out.println(output.length + " " + rows.size() +" AND " + output[1].length);
			
			csvReader.close();
			 out.println("success");
				//out.flush();
				//out.close();
			 blobstoreService.delete(blobKey);
				
			 resp.sendRedirect("http://default.agraralphav1.appspot.com/");
			 
				String statement = "INSERT INTO records (domainCode, domain, areaCode, areaName, elementCode, elementName, itemCode, itemName, year, unit, value, flag, flagD) VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
				PreparedStatement stmt = null;
			  try {
				  stmt = conn.prepareStatement(statement);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		for(int v = 0; v < arrays.size(); v++){
    		
			  String output[][] = arrays.get(v);
			
				for(int y = 1; y<output.length-2; y++){

					  try {
						stmt.setString(1, output[y][0]);
						stmt.setString(2,output[y][1]);
			          stmt.setString(3,output[y][2]);
			          stmt.setString(4,output[y][3]);
			          stmt.setString(5,output[y][4]);
			          stmt.setString(6,output[y][5]);
			          stmt.setString(7,output[y][6]);
			          stmt.setString(8,output[y][7]);
			          stmt.setString(9,output[y][8]);
			          stmt.setString(10,output[y][9]);
			          stmt.setString(11,output[y][10]);
			          stmt.setString(12,output[y][11]);
			          stmt.setString(13,output[y][12]);
			          stmt.addBatch();
					  } catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			         
				}
			
				}
    		try {
				stmt.executeBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Date end = new Date();
    		long timeElapsed = end.getTime()-start.getTime();
    		log.severe("Zeit: " + Objects.toString(timeElapsed));
		}}
    		

		

