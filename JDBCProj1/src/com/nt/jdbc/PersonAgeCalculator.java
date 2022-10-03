package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class PersonAgeCalculator {
private static final String AGE_CALCULATOR_QUERY= "SELECT (SYSDATE-DOB)/365.25 FROM JDBC_PERSON_DATE WHERE PID = ?";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con=null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			sc=new Scanner(System.in);
			int pid =0;
			if(sc!= null) {
				System.out.println("Enter person id : ");
				pid = sc.nextInt();
			}//if
			Class.forName("oracle.jdbc.OracleDriver");
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","1998");
			//create prepared statement obj
			if(con != null)
				ps = con.prepareStatement(AGE_CALCULATOR_QUERY);
			//set the values to the query
			if (ps!= null) {
				ps.setInt(1, pid);
				rs = ps.executeQuery();
			}//if
			if (rs != null) {
				if(rs.next())
					System.out.println("person age : " + rs.getFloat(1));
				else
					System.out.println("Record not found");
			}//if
		} //try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
