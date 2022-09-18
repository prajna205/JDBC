package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest6 {

	public static void main(String[] args) {
		Scanner sc= null;
		Connection con= null;
		Statement st= null;
		ResultSet rs= null;
		try {
			//take the input for employee no
			sc = new Scanner(System.in);
			System.out.println("Enter emp no: ");
			int empno = sc.nextInt();
			//load jdbc driver
			Class.forName("oracle.jdbc.OracleDriver");
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1998");
			//create statement object
			if(con!=null)
				st = con.createStatement();
			//preapre query
			String query= "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO= "+ empno;
			//send and execute query
			if(st!=null)
				rs = st.executeQuery(query);
			//process the result set object
			if (rs!=null) {
				if (rs.next()) {
					   System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
				}//if
				else
					System.out.println("No Record Found...");
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
			try {
				if(sc!=null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
