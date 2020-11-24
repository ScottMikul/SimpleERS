package com.revature.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.utilities.ConnectionCloser;
import com.revature.utilities.ConnectionFactory;





public class testConnectionUtilities {
	private static Connection conn;

	@BeforeClass
	public static void setUp(){
		
	}

	@Before
	public void perMethodSetUp() {
		try {
			conn = ConnectionFactory.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDatabaseConnectivity() throws SQLException {
		Assert.assertTrue(conn.isValid(1000));
	}
	
	@Test
	public void testDatabaseConnectionCloses() throws SQLException {
		ConnectionCloser.closeResource(conn);
		Assert.assertTrue(conn.isClosed());
	}


	@After
	public void perMethodTearDown() {
		
	}
	

	@AfterClass
	public static void tearDown() {
	}

}
