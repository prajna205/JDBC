package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		Scanner sc= null;
		Connection con= null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			//take the inputs
			int sno =0;
			String sname = null ,sadd = null;
			float avg =0.0f;
			if(sc!=null) {
				System.out.println("Enter Student number");
				 sno =sc.nextInt();
				System.out.println("Enter student name");
				 sname =sc.next();
				 System.out.println("Enter student address");
				 sadd =sc.next();
				 System.out.println("Enter student average mark");
				 avg =sc.nextFloat();
			}
			//convert inputs into sql required format
			String inputString = "("+sno+",'"+sname+"','"+sadd+"',"+avg+")";
			//load jdbc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
			//create statement obj
			if(con!=null)
				st =con.createStatement();
			//preapare sql query
			String query= "INSERT INTO STUDENT VALUES  "+inputString;
			System.out.println(query);
			//send and execute the query
			int count =0;
			if(st!=null)
				  count = st.executeUpdate(query);
			//process result
			if(count ==0)
				System.out.println("No record found in this range.");
			else
				System.out.println("records added.");
		}//try
		
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
				if(st!=null)
					st.close();
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

	}

}
