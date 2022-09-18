package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_UpdateTest_MySql {
private static final String UPDATE_STUDENT_BY_SNO = "UPDATE STUDENT SET SADD = ? , AVG = ?  WHERE SNO = ?";
	public static void main(String[] args) {
		Scanner sc= null;
		Connection con= null;
		PreparedStatement ps=null;
		try {
			sc=new Scanner(System.in);
			int sno =0;
			String sadd = null;
			float avg =0.0f;
			if(sc!=null) {
				System.out.println("Enter Student number to update details");
				 sno =sc.nextInt();
				 System.out.println("Enter student address");
				 sadd =sc.next();
				 System.out.println("Enter student average mark");
				 avg =sc.nextFloat();
			}
			//load jdbc driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdb","root","1998");
			//create statement obj
			if(con!=null)
				ps =con.prepareStatement(UPDATE_STUDENT_BY_SNO);
			//send and execute the query
			int count =0;
			if(ps !=null) {
					ps.setString(1, sadd);
					ps.setFloat(2, avg);
					ps.setInt(3, sno);
				  count = ps.executeUpdate();
			//process result
			if(count ==0)
				System.out.println("No record found to update");
			else
				System.out.println("record updated.");
			}//if
		}//try
		
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
			try {
				if(sc!=null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
