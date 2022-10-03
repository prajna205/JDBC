package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc= null;
		Connection con= null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			//take the inputs
			float begavg =0.0f,endavg=0.0f;
			if(sc!=null) {
				System.out.println("Enter begining avg mark");
				 begavg =sc.nextFloat();
				System.out.println("Enter end avg mark");
				 endavg =sc.nextFloat();
			}
			//load jdbc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
			//create statement obj
			if(con!=null)
				st =con.createStatement();
			//preapare sql query
			String query= "DELETE FROM STUDENT WHERE AVG >= "+begavg + "AND AVG <= " + endavg;
			//send and execute the query
			int count =0;
			if(st!=null)
				  count = st.executeUpdate(query);
			//process result
			if(count ==0)
				System.out.println("No record found in this range.");
			else
				System.out.println(count + "records deleted.");
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
