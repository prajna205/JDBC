package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PS_ScrollableResultSetTest {
	private static final String SELECT_STUDENT ="SELECT * FROM STUDENT";
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
			}
			System.out.println("********************************************");
			System.out.println("Records from bottom to top");
			rs.afterLast();
			while (rs.previous()) {
				System.out.println(rs.getInt(1) +"  "+rs.getString(2)+ "  "+rs.getString(3)+"  "+rs.getFloat(4));
			}
			System.out.println("********************************************");
			System.out.println("Records accessed randomly");
			rs.last();
			System.out.println(rs.getRow()+"  "+ rs.getInt(1) +"  "+rs.getString(2)+ "  "+rs.getString(3)+"  "+rs.getFloat(4));
			rs.first();
			System.out.println(rs.getRow()+"  "+ rs.getInt(1) +"  "+rs.getString(2)+ "  "+rs.getString(3)+"  "+rs.getFloat(4));
			rs.relative(4);
			System.out.println(rs.getRow()+"  "+ rs.getInt(1) +"  "+rs.getString(2)+ "  "+rs.getString(3)+"  "+rs.getFloat(4));
			rs.relative(-2);
			System.out.println(rs.getRow()+"  "+ rs.getInt(1) +"  "+rs.getString(2)+ "  "+rs.getString(3)+"  "+rs.getFloat(4));
			rs.absolute(4);
			System.out.println(rs.getRow()+"  "+ rs.getInt(1) +"  "+rs.getString(2)+ "  "+rs.getString(3)+"  "+rs.getFloat(4));
			rs.absolute(-15);
			System.out.println(rs.getRow()+"  "+ rs.getInt(1) +"  "+rs.getString(2)+ "  "+rs.getString(3)+"  "+rs.getFloat(4));
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
