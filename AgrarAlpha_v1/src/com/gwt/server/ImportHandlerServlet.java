package com.gwt.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	    
        // process only multipart requests
       if (ServletFileUpload.isMultipartContent(req)) {
          // Create a factory for disk-based file items
           FileItemFactory factory = new DiskFileItemFactory(5000000,null);

            // Create a new file upload handler
          ServletFileUpload upload = new ServletFileUpload(factory);
	    
	    // Parse the request
	                 try {
	                 List<FileItem> items = upload.parseRequest(req);
	                  for (FileItem item : items) {
	                    // process only file upload - discard other form item types
	                      if (item.isFormField()) continue;
	                      
	                     String fileName = item.getName();
	                       // get only the file name not whole path
	                         if (fileName != null) {
	                            fileName = FilenameUtils.getName(fileName);
	                       }
	   
	                       File uploadedFile = new File(fileName);
	                       /* if (uploadedFile.createNewFile()) {
	                            item.write(uploadedFile);
	                            resp.setStatus(HttpServletResponse.SC_CREATED);
	                            resp.getWriter().print("The file was created successfully.");
	                          resp.flushBuffer();
	                      } else
	                           throw new IOException("The file already exists in repository.");
	                  */
	                    CSVReader reader = new CSVReader(new BufferedReader(new FileReader(uploadedFile)));
	               	    
	               	    List<String[]> lines;
	               	    lines = reader.readAll();
	               	   
	               	 String matrix[][] =  lines.toArray(new String[lines.size()][]);     
	                  
	               	 matrixList.add(matrix);
	                  reader.close();
	                  }
	              } catch (Exception e) {
	                   resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
	                           "An error occurred while creating the file : " + e.getMessage());
	                }
	  }
	    
	    
	    
	    
	    
	    
	    
/*	    
	    StringBuilder sb = new StringBuilder();
	    BufferedReader reader = req.getReader();
	    try {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line).append('\n');
	        }
	    } finally {
	        reader.close();
	    }
	   out.println(sb.toString());
*/	    
	
	    
	    
	    //CSVReader reader = new CSVReader(new BufferedReader(new FileReader(file)));
	    
	    //List<String[]> lines;
	    //lines = reader.readAll();
		//String matrix[][] =  lines.toArray(new String[lines.size()][]);
	    out.println("<html><head></head><body>Hello!</body></html>");
	    try {
	      if (SystemProperty.environment.value() ==
	          SystemProperty.Environment.Value.Production) {
	        // Load the class that provides the new "jdbc:google:mysql://" prefix.
	        Class.forName("com.mysql.jdbc.GoogleDriver");
	        url = "jdbc:google:mysql://testagrar:myapp/test?user=root";
	        out.println("<html><head></head><body>Production</body></html>");
	      } else {
	        // Local MySQL instance to use during development.
	        Class.forName("com.mysql.jdbc.Driver");
	        url = "jdbc:mysql://173.194.243.202:3306/test?user=root";
	        out.println("<html><head></head><body>Local</body></html>");
	        // Alternatively, connect to a Google Cloud SQL instance using:
	        // jdbc:mysql://ip-address-of-google-cloud-sql-instance:3306/guestbook?user=root
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      out.println(e.toString());
	      out.println("<html><head></head><body>Connection Exception</body></html>");
	      return;
	    }

	   
	    try {
	      Connection conn = DriverManager.getConnection(url);
	      try {
	        String fname = req.getParameter("fname");
	        String content = req.getParameter("content");
	        if (fname == "" || content == "") {
	          out.println(
	              "<html><head></head><body>You are missing either a message or a name! Try again! " +
	              "Redirecting in 3 seconds...</body></html>");
	        } else {
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
	          
	        	}
	          if (success == 1) {
	            out.println(
	                "<html><head></head><body>Success! Redirecting in 3 seconds...</body></html>");
	          } else if (success == 0) {
	            out.println(
	                "<html><head></head><body>Failure! Please try again! " +
	                "Redirecting in 3 seconds...</body></html>");
	          }
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
	    out.flush();
	    out.close(); 
	  }
	}

