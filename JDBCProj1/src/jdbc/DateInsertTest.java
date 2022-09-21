package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsertTest {
private static final String INSERT_PERSON_DATES= "INSERT INTO JDBC_PERSON_DATE VALUES (?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con=null;
		PreparedStatement ps= null;
		try {
			sc=new Scanner(System.in);
			int pid =0;
			String pname = null, dob= null, doj =null , dom =null;
			if(sc!= null) {
				System.out.println("Enter person id : ");
				pid = sc.nextInt();
				System.out.println("Enter person name : ");
				pname = sc.next();
				System.out.println("Enter Date of Birth (dd-MM-yyyy) : ");
				dob = sc.next();
				System.out.println("Enter Date of joining(MM-dd-yyyy) : ");
				doj = sc.next();
				System.out.println("Enter date of marriage(yyyy-MM-dd) : ");
				dom = sc.next();
			}//if
			//convert the string dates to java.sql.date class obj
			//for DOB
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date ud1 = sdf1.parse(dob);
			long ms = ud1.getTime();
			java.sql.Date sqdob = new java.sql.Date(ms);
			//for DOJ
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date ud2 = sdf2.parse(doj);
			long ms1 = ud2.getTime();
			java.sql.Date sqdoj = new java.sql.Date(ms1);
			//for DOM
			java.sql.Date sqdom = java.sql.Date.valueOf(dom);
			//load jdbc driver s/w
			Class.forName("oracle.jdbc.OracleDriver");
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","1998");
			//create prepared statement obj
			if(con != null)
				ps = con.prepareStatement(INSERT_PERSON_DATES);
			//set the values to the query
			int count =0;
			if (ps != null) {
				ps.setInt(1, pid);
				ps.setString(2, pname);
				ps.setDate(3,sqdob);
				ps.setDate(4, sqdoj);
				ps.setDate(5, sqdom);
				//send and execute the query
				count = ps.executeUpdate();
				//process the result
				if(count == 0)
					System.out.println("Record not inserted.");
				else
					System.out.println("Record inserted.");
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
