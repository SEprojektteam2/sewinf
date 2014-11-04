package com.gwt.client;

import java.sql.DriverManager;

import com.google.appengine.api.utils.SystemProperty;
import com.google.cloud.sql.jdbc.Connection;

public class SQLConnector {
	Connection conn = DriverManager.getConnection("jdbc:google:mysql://your-project-id:your-instance-name/database",
		    "user", "password");
}
