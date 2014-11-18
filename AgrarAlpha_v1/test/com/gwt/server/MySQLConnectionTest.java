package com.gwt.server;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class MySQLConnectionTest {

	@Test
	public void testConnect(){
		MySQLConnection connection = new MySQLConnection("173.194.253.240","root","","agrar","agraralphav1:agrar");
		if(!connection.connect())
			fail("connection failed");
	}
	
	@Test
	public void testReturnConnection() {
		MySQLConnection connection = new MySQLConnection("173.194.253.240","root","","agrar","agraralphav1:agrar");
		Connection testConnection = connection.returnConnection();
		
			
	
	}
	@Test
	public void testCloseConnection() {
		MySQLConnection connection = new MySQLConnection("173.194.253.240","root","","agrar","agraralphav1:agrar");
		connection.connect();
		if(!connection.close())
			fail("Error while closing connection");
	}

}
