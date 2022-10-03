package com.nt.date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateValuesRetriveTest {
private static final String RETRIVE_PERSON_DATE = "SELECT * FROM JDBC_PERSON_DATE";
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//load jdbc driver s/w
			Class.forName("oracle.jdbc.OracleDriver");
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","1998");
			//create statement object
			if(con != null)
				ps = con.prepareStatement(RETRIVE_PERSON_DATE);
			//send and execute the query
			if(ps != null) 
					rs = ps.executeQuery();
			//process the result set obj
			if (rs != null) {
				while (rs.next()) {
					//read the date values as sql date objects
					java.sql.Date sd1 = rs.getDate(3);
					java.sql.Date sd2 = rs.getDate(4);
					java.sql.Date sd3 = rs.getDate(5);
					//convert the sql date objs to util date obj
					java.util.Date ud1 =sd1;
					java.util.Date ud2 =sd2;
					java.util.Date ud3 =sd3;
					//convert util class obj to string obj
					SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
					String dob = sdf.format(ud1);
					String doj = sdf.format(ud2);
					String dom = sdf.format(ud3);
					System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) 
														+"\t\t" + dob +"\t\t" + doj +"\t\t" + dom);
				}
			}//if
		} //try
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
				if(rs!=null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
