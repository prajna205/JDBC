package com.nt.blob;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BlobInsertionTest_MYSQL {
private static final String INSERT_ACTOR_QUERY = "INSERT INTO JDBC_ACTOR(ACTORNAME, ACTORADDR,PHOTO) VALUES(?,?,?)";
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql://@localhost:3306/jdbcdb", "root", "1998");
				PreparedStatement ps = con.prepareStatement(INSERT_ACTOR_QUERY);
				) {
			String name=null, addr= null, photoLoactain= null;
			if (scanner != null) {
				System.out.println("Enter actor name");
				name = scanner.next();
				System.out.println("Enter actor address");
				addr = scanner.next();
				System.out.println("Enter photo loaction");
				photoLoactain = scanner.next().replace('?', ' ').trim();
			}
			//create stream pointing to the photo location
			InputStream is = new FileInputStream(photoLoactain);
			//set the values to preaparedstatement and exexute the query
			int count =0;
			if (ps != null) {
				ps.setString(1, name);
				ps.setString(2, addr);
				//setBinaryStream() expects input sream as parameter
				ps.setBinaryStream(3, is);
				count = ps.executeUpdate();
				//process the result
				if(count ==0)
					System.out.println("Record not inserted");
				else
					System.out.println("Record inserted");
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
