//SelectTest2.java
import java.sql.*;
import java.util.Scanner;

public class SelectTest2{
	public static void main(String[] args) throws Exception{
		//Take the input of employee designation from user
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter employee desg:");
		String desg = sc.next().toUpperCase();
		desg = "'"+ desg+"'";
		//Load the JDBC Driver
		Class.forName("oracle.jdbc.OracleDriver");
		
		//Establish the connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system" ,"1998");
		
		//create Statement obj
		Statement st = con.createStatement();
				
		//Prepare the sql Query
		// SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB='CLERK'
		String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB=" + desg;
		System.out.println(query);
		
		//send and exexute sql select query in DB
		ResultSet rs = st.executeQuery(query);
		//isRSEmpty is used to check if result set is empty or not
		Boolean isRSEmpty = true;
		
		//process the result set obj
		//while(rs.next()!=false)
		while(rs.next())
		{
			isRSEmpty = false;
			System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t" + rs.getFloat(4));
		}
		
		if(isRSEmpty)
			System.out.println("No Record Found...");
		
		rs.close();
		st.close();
		con.close();
		sc.close();
	}//main
}//class


