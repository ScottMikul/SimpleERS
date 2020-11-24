package com.revature.utilities;

import java.sql.SQLException;

public class ConnectionCloser {
	public static void closeResource(AutoCloseable auto) {
		try{
			auto.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
