package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest_MySql {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con= null;
		PreparedStatement ps = null;
		try {
			sc = new Scanner(System.in);
			System.out.println("Enter no of students: ");
			int num = sc.nextInt();
			
			//load jdbc driver 
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish connection
			con = DriverManager.getConnection("jdbc:mysql:///jdbcdb","root","1998");
			//create preapared statement object
			if(con!= null)
				ps = con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?)");
			//read student details 
			for (int i = 1; i <= num; i++) {
				System.out.println("Enter Student " + i + " Details : ");
				System.out.println("Enter student no: ");
				int sno = sc.nextInt();
				System.out.println("Enter student name: ");
				String sname = sc.next();
				System.out.println("Enter student address: ");
				String sadd = sc.next();
				System.out.println("Enter student average: ");
				float avg = sc.nextFloat();
				//set the values to the pre-compiled query
				ps.setInt(1, sno);
				ps.setString(2, sname);
				ps.setString(3, sadd);
				ps.setFloat(4, avg);
				//send and execute the query
				int count =0;
				if(ps!= null)
					count = ps.executeUpdate();
				//process the result
				if(count == 0)
					System.out.println("No Record Inserted.");
				else
					System.out.println(count + " Record inserted.");
			}//for
		}//try
		//handle the exceptions
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//close jdbc objects
		finally {
			try {
				if(ps!= null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!= null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!= null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
