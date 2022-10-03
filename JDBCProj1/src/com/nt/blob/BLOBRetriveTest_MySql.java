package com.nt.blob;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;


public class BLOBRetriveTest_MySql {
private static final String GET_ACTOR_QUERY= "SELECT * FROM JDBC_ACTOR WHERE ACTORID = ?";
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdb","root","1998");
				PreparedStatement ps = con.prepareStatement(GET_ACTOR_QUERY);
				) {
			int id=0;
			if (sc != null) {
				System.out.println("Enter Actor Id: ");
				id = sc.nextInt();
			}//if
			if (ps!= null) 
				ps.setInt(1, id);
			//create result set object
			try (ResultSet rs= ps.executeQuery()){
				//process the resultset obj
				InputStream is= null;
				if (rs != null) {
					if (rs.next()) {
						System.out.println(rs.getInt(1)+ "  "+ rs.getString(2) + "  "+ rs.getString(3));
						//get access to the inputfile
						is = rs.getBinaryStream(4);
						//create the output stream representing empty destination file
						OutputStream os = new FileOutputStream("Retrived_photo1.jpeg");
						//use third party api to copy file content from is to os
						IOUtils.copy(is, os);
						System.out.println("Photo copied to destination file");
					}//if
				}//if
				else
					System.out.println("Record Not found...");
			}//try2
		} //try1
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
