package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SensitiveRSTest {
	private static final String SELECT_STUDENT ="SELECT SNO,SNAME,SADDR,AVG FROM STUDENT";
	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
				PreparedStatement ps = con.prepareStatement( SELECT_STUDENT,
																											ResultSet.TYPE_SCROLL_SENSITIVE, 
																											ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery();
				){
		   if (rs!=null) {
			System.out.println("Records from top to bottom");
			int count =0;
			while (rs.next()) {
				
				if(count ==0) {
					System.out.println("Modify db table in the sleep time");
					Thread.sleep(30000);
				}//if
				rs.refreshRow();
				count++;
				System.out.println(rs.getInt(1) +"  "+rs.getString(2)+ "  "+rs.getString(3)+"  "+rs.getFloat(4));
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
