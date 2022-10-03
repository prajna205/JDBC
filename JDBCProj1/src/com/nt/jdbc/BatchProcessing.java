package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessing {

	public static void main(String[] args) {
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
				Statement st = con.createStatement();
				) {
			if (st != null) {
				st.addBatch("INSERT INTO STUDENT VALUES (25, 'RAHUL', 'BLR', 54.6)");
				st.addBatch("UPDATE STUDENT SET AVG = AVG + 10 WHERE SADDR = 'sbp' ");
				st.addBatch("DELETE FROM STUDENT WHERE SNO> 10");
				int[] result = st.executeBatch();	
				//process the result
				int total =0;
				for (int i = 0; i < result.length; i++) 
					total = total + result[i];					
				
				System.out.println("Total Number of records changed : "+ total);
			}//if
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
