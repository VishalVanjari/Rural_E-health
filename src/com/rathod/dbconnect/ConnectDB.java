package com.rathod.dbconnect;

import java.sql.*;

public class ConnectDB {
	static Connection con=null;
	public static Connection getConnect(){
		if(con==null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_health","root","");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
