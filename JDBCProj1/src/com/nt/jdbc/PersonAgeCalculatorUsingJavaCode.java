package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;


public class PersonAgeCalculatorUsingJavaCode {
private static final String GET_DOB_QUERY= "SELECT DOB FROM JDBC_PERSON_DATE WHERE PID = ?";
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
				ps = con.prepareStatement(GET_DOB_QUERY);
			//set the values to the query
			if (ps!= null) {
				ps.setInt(1, pid);
				rs = ps.executeQuery();
			}//if
			java.sql.Date sqdob= null;
			if (rs != null) {
				if(rs.next()) {
					sqdob = rs.getDate(1);
					java.util.Date sysDate = new Date(); // this gives current system date
					long dobMs = sqdob.getTime();
					long sysDateMs = sysDate.getTime();
					float age = (float) ((sysDateMs - dobMs )/(1000*60*60*24*365.25));
					System.out.println("Person Age : " + age);
				}
				else {
					System.out.println("Record not found.");
				}
			}
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
