//ConnTest.java

import java.sql.*;
public class ConnTest{
	public static void main(String[] args)throws Exception{
		//1.register jdbc driver s/w with DriverManager
			//a. Create jdbc driver class obj
			 oracle.jdbc.OracleDriver driver = new oracle.jdbc.OracleDriver();
			//b.register jdbc driver s/w with drivermanager
			DriverManager.registerDriver(driver);
			
		//2.Establish connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "1998");
		
		//check if the connection is established or not
		if(con == null)
			System.out.println("connection is not established");
		else
			System.out.println("connection established");
	}
	
}