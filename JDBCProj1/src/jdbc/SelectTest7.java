package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest7 {

	public static void main(String[] args) {
		Connection con= null;
		Statement st= null;
		ResultSet rs= null;
		try {
			//load jdbc driver
			Class.forName("oracle.jdbc.OracleDriver");
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1998");
			//create statement object
			if(con!=null)
				st = con.createStatement();
			//preapre query
			String query= "SELECT COUNT(*) FROM EMP";
			//send and execute query
			if(st!=null)
				rs = st.executeQuery(query);
			//process the result set object
			if (rs!=null) {
				rs.next();
				System.out.println(rs.getInt(1));
			}//if
		} //try
		
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch (SQLException se) {
			se.printStackTrace();
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
		}//finally
	}//main
}//class
