//SelectTest3.java
import java.sql.*;
import java.util.Scanner;

public class SelectTest3{
	public static void main(String[] args) throws Exception{
		//Take the inputs for start avg and end avg
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Start average:");
		float startAvg = sc.nextFloat();
		System.out.println("Enter end average:");
		float endAvg = sc.nextFloat();
		
		//Load the JDBC driver
		Class.forName("oracle.jdbc.OracleDriver");
		
		//Establish the connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1998");
		
		//create satrement obj
		Statement st = con.createStatement();
		
		//Prepare the sql query to select student names between the range of startAvg and endAvg
		//SELECT SNAME FROM STUDENT WHERE AVG>=30 AND AVG<=100
		String Query = "SELECT SNAME FROM STUDENT WHERE AVG>=" + startAvg + "AND AVG<=" + endAvg;
		System.out.println(Query);
		//send and execute the query
		ResultSet rs = st.executeQuery(Query);
		
		//processs the ResultSet obj
		Boolean isRSEmpty = true;
		while(rs.next())
		{
			isRSEmpty = false;
			System.out.println(rs.getString(1));
		}
		
		if(isRSEmpty)
			System.out.println("No Record Found...");
	
	}//main
}//class