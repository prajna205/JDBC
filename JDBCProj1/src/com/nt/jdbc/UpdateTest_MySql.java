package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest_MySql {

	public static void main(String[] args) {
		Scanner sc= null;
		Connection con= null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			int sno =0;
			String sadd = null;
			float avg =0.0f;
			if(sc!=null) {
				System.out.println("Enter Student number");
				 sno =sc.nextInt();
				 System.out.println("Enter student address");
				 sadd =sc.next();
				 System.out.println("Enter student average mark");
				 avg =sc.nextFloat();
			}
			//convert inputs into sql required format
			sadd = "'"+ sadd + "'";
			//load jdbc driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdb","root","1998");
			//create statement obj
			if(con!=null)
				st =con.createStatement();
			//preapare sql query
			String query= "UPDATE STUDENT SET SADD = " + sadd +"  , AVG = " + avg + " WHERE SNO = "+sno;
			System.out.println(query);
			//send and execute the query
			int count =0;
			if(st!=null)
				  count = st.executeUpdate(query);
			//process result
			if(count ==0)
				System.out.println("No record found...");
			else
				System.out.println("record updated.");
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
