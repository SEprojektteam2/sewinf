package com.gwt.server;

import java.io.BufferedReader;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreInputStream;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import au.com.bytecode.opencsv.CSVReader;

import com.google.appengine.api.utils.SystemProperty;
//Import external class for simple csv handling


	@SuppressWarnings("serial")
	public class ImportHandlerServlet extends HttpServlet {
	  @Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    
		  resp.setContentType("text/html");
		  
		  String url = null;
		  PrintWriter out = resp.getWriter();
		  ArrayList<String[][]> matrixList = new ArrayList<String[][]>();
	    
        
		   BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		    
		  String fileName = "";
		  
		  Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		  BlobKey blobKey = blobs.get("importCSV");
		  
		  BufferedReader blobreader = new BufferedReader(new InputStreamReader(new BlobstoreInputStream(blobKey))); 
		  System.out.println("BlobReader Created!");
		  if (blobKey == null) {
			  out.println("/");
		  }
		  else {
			 
			 fileName = blobKey.getKeyString(); 
			
			  }
			 
	  
		  
		  
		  
		  
		  
		  
		  
		  
		  CSVReader reader = new CSVReader(blobreader);
	               	    
	      List<String[]> lines;
	      lines = reader.readAll();
	               	   
	      String matrix[][] =  lines.toArray(new String[lines.size()][]);     
	                  
	      matrixList.add(matrix);
	      reader.close();
	  
	             
	 
	    
	    
	    
	    
	    
	    
	  
	    
	    
	    MySQLConnection database = new MySQLConnection("173.194.253.240:3306","root","","agrar","agraralphav1:agrar");
	    if(database.connect()){
	    	out.println("<html><head></head><body>Connection Started</body></html>");
	    }
	    Connection conn = database.returnConnection();
	    
	    try {
	    
	      try {
	        
	        	int success = 2;
	        	String output[][] = matrixList.get(0);
	        	
	        	if(matrixList.isEmpty())
	        		out.println("Arraylist is empty!");
	        	
	        	for(int y = 1; y<output.length; y++){
	        		String statement = "INSERT INTO records (domainCode, domain, areaCode, areaName, elementCode, elementName, itemCode, itemName, year, unit, value, flag, flagD) VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
		        	PreparedStatement stmt = conn.prepareStatement(statement);
		        	
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
	          
	          success = stmt.executeUpdate();
	          out.println("35: in try loop!");
	          
	        	}
	          if (success == 1) {
	            out.println(
	                "<html><head></head><body>Success! Redirecting in 3 seconds...</body></html>");
	          } else if (success == 0) {
	            out.println(
	                "<html><head></head><body>Failure! Please try again! " +
	                "Redirecting in 3 seconds...</body></html>");
	          }
	        
	      } finally {
	        conn.close();
	        out.println("<html><head></head><body>Close Connection</body></html>");
	      }
	    } catch (SQLException e) {
	    	String exception = "<html><head></head><body></body></html>";
	    	 out.println(exception);
	    // out.print(e.printStackTrace());
	    }
	    database.close();
	    out.flush();
	    out.close(); 
	  }
	}

