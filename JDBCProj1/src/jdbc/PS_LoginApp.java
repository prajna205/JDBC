package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_LoginApp {
	private static final String AUTH_QUERY = "SELECT COUNT(*) FROM USERINFO WHERE USERNAME = ? AND PASSWORD = ?";
	public static void main(String[] args) {
		Scanner sc= null;
		Connection con= null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			sc=new Scanner(System.in);
			//take the inputs
			String user = null ,pwd = null;
			if(sc!=null) {
				System.out.println("Enter username");
				user =sc.nextLine();
				System.out.println("Enter password");
				 pwd =sc.nextLine();
			}
			//load jdbc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
			//create statement obj
			if(con!=null)
				ps =con.prepareStatement(AUTH_QUERY);
			//set the values and send and execute the query
			if (ps != null) {
				ps.setString(1, user);
				ps.setString(2, pwd);
				rs = ps.executeQuery();
			}
			//process result
			int count =0;
			if(rs != null) {
				rs.next();
				count = rs.getInt(1);
			}//if
			if(count ==0)
				System.out.println("Invalid Credentials");
			else
				System.out.println("valid Credentials");
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

	}

}
