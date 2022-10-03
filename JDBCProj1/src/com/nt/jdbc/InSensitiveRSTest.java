package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class InSensitiveRSTest {
	private static final String SELECT_STUDENT ="SELECT SNO,SNAME,SADDR,AVG FROM STUDENT";
	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
				PreparedStatement ps = con.prepareStatement( SELECT_STUDENT,
																											ResultSet.TYPE_SCROLL_SENSITIVE, 
																											ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery();
				){
		   if (rs!=null) {
			System.out.println("Records from top to bottom");
			while (rs.next()) {
				System.out.println(rs.getInt(1) +"  "+rs.getString(2)+ "  "+rs.getString(3)+"  "+rs.getFloat(4));
			}//while
			
			/*
			 * //insert data System.out.println("Record insertion "); rs.moveToInsertRow();
			 * rs.updateInt(1,1234); rs.updateString(2, "minu"); rs.updateString(3, "Sbp");
			 * rs.updateFloat(4, 70.98f); rs.insertRow();
			 */
			 
			/*
			 * System.out.println("Record updation");
			 * rs.absolute(16); rs.updateString(2, "minati"); rs.updateRow();
			 */
			System.out.println("Record deletion");
			rs.absolute(18);
			rs.deleteRow();
			
			
		}//if
		}//try 
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
