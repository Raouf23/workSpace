package com.tavant.dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DbTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con  = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/hr" ,"root", "root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from regions");
		int count =0;
		while (rs.next())
		{
			count++;
			System.out.println(rs.getInt(1)+ " " + rs.getString(2));
		}
		System.out.println(count);
		con.close();
	}

}
