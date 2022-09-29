package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseMetaDataTest {

	public static void main(String[] args) {
		try (Connection con  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","1998")
				){
			DatabaseMetaData dbmd =null;
					if(con != null)
						 dbmd = con.getMetaData();
					if (dbmd != null) {
						System.out.println("Db S/W NAME : " + dbmd.getDatabaseProductName());
						System.out.println("Db S/W VERSION : " + dbmd.getDatabaseProductVersion());
						System.out.println("Db S/W VERSION : " + dbmd.getDatabaseMajorVersion() + "."+ dbmd.getDatabaseMinorVersion());
						System.out.println("Db S/W KEYWORDS : " + dbmd.getSQLKeywords());
						System.out.println("Db S/W NUMERIC FUNCTIONS : " + dbmd.getNumericFunctions());
						System.out.println("Db MAX USER LENGTH : " + dbmd.getMaxUserNameLength());
						System.out.println("Db MAX ROW SIZE : " + dbmd.getMaxRowSize());
					}
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
