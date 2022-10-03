package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		Scanner sc= null;
		Connection con= null;
		Statement st=null;
		ResultSet rs =null;
		try {
			sc=new Scanner(System.in);
			//take the inputs
			String user = null ,pwd = null;
			if(sc!=null) {
				System.out.println("Enter username");
				user =sc.next();
				System.out.println("Enter password");
				 pwd =sc.next();
			}
			//convert inputs into sql required format
			user = "'" + user + "'";
			pwd = "'"+pwd +"'";
			//load jdbc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
			//create statement obj
			if(con!=null)
				st =con.createStatement();
			//preapare sql query
			String query= "SELECT COUNT(*) FROM USERINFO WHERE USERNAME = "+user + " AND PASSWORD = " + pwd;
			System.out.println(query);
			//send and execute the query
			if(st!=null)
				 rs= st.executeQuery(query);
			//process result
			int count =0;
			if(rs != null) {
				rs.next();
				count = rs.getInt(1);
			}//if
			if(count ==0)
				System.out.println("Invalid Record");
			else
				System.out.println("valid record");
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
