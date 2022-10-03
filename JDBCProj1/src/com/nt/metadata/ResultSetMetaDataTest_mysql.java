package com.nt.metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest_mysql {

	public static void main(String[] args) {
		try (Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdb", "root","1998");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");
				){
			ResultSetMetaData rsmd =null;
					if(rs != null)
						rsmd = rs.getMetaData();
					if (rsmd != null) {
						int colCount = rsmd.getColumnCount();
						//print column name
						for (int i = 1; i <= colCount ; i++) {
							System.out.print(rsmd.getColumnName(i) + "\t\t");
						}
						System.out.println();
						//print column date type
						for (int i = 1; i <= colCount ; i++) {
							System.out.print(rsmd.getColumnTypeName(i)+"\t\t");
						}
						System.out.println();
						//print column data
						while (rs.next()) {
							for (int i =1; i <= colCount ; i++) {
								System.out.print(rs.getString(i)+"\t\t");
							}
							System.out.println();
						}//while
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
