package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest5 {

	public static void main(String[] args) {
		Connection con =null;
		Statement st= null;
		ResultSet rs= null;
		try {
			//Load jdbc driver s/w
			Class.forName("oracle.jdbc.OracleDriver");
			//establish connection with drivermanager
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","1998");
			//create statement obj
			if(con != null)
				 st = con.createStatement();
			//prepare the query
		   String query = "SELECT * FROM STUDENT WHERE AVG = (SELECT MAX(AVG) FROM STUDENT)";
		   //send and execute query
		   if(st!=null)
			   rs = st.executeQuery(query);
		   //process the result set
		   boolean isRsEmpty = true;
		   if(rs!=null) {
			   while(rs.next()) {
				   isRsEmpty = false;
				   System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
			   }//while
			   if(isRsEmpty)
				   System.out.println("No record found...");
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
