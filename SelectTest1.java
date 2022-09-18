//SelectTest1.java

import java.sql.*;
public class SelectTest1{
	public static void main(String[] args)throws Exception{
		//Load jdbc driver s/w
		Class.forName("oracle.jdbc.OracleDriver");
			
		//Establish connection with DB s/w
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "1998");
		
		//Create statement object
		
		Statement st = con.createStatement();
		
		//preapare the sql query
		String query = "SELECT * FROM STUDENT";
		//send and execute the query 
		ResultSet rs = st.executeQuery(query);
		//process the resultset object
			while(rs.next())
				System.out.println(rs.getInt(1) +"\t\t" +rs.getString(2) +"\t\t" +rs.getString(3) +"\t\t" +rs.getFloat(4));
			
		//close jdbc objects
		rs.close();
		st.close();
		con.close();
	}
	
}