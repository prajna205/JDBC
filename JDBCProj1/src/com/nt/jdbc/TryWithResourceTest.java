package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;


public class TryWithResourceTest {
private static final String INSERT_CUSTOMER_QUERY= "INSERT INTO JDBC_CUSTOMER(CNAME, CADDRS, BILLAMT, DTOP) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc =new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql://@localhost:3306/jdbcdb", "root","1998");
				PreparedStatement ps= con.prepareStatement(INSERT_CUSTOMER_QUERY);
					) {
			String cname= null, cadds = null;
			float amt = 0.0f;
			if(sc!= null) {
				System.out.println("Enter customer name : ");
				cname = sc.next();
				System.out.println("Enter customer address : ");
				cadds = sc.next();
				System.out.println("Enter Bill amount : ");
				amt = sc.nextFloat();
			}//if
			//set the values to the query
			int count = 0;
			if (ps!= null) {
				ps.setString(1, cname);
				ps.setString(2, cadds);
				ps.setFloat(3, amt);
				ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
				count = ps.executeUpdate();
				if(count ==0)
					System.out.println("Record not Inserted.");
				else
					System.out.println("Record inserted.");
			}//if
		} //try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
