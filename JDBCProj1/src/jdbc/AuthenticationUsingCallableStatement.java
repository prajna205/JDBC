package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_AUTHENTICATION 
(
  NAME IN VARCHAR2 
, PWD IN VARCHAR2 
, RESULT OUT VARCHAR2 
) AS 
    CNT NUMBER(2);
BEGIN
  SELECT COUNT(*) INTO CNT FROM USERINFO WHERE USERNAME = NAME AND PASSWORD = PWD;
  IF(CNT <> 0)THEN
    RESULT := 'VALID CREDENTIALS';
  ELSE
    RESULT := 'INVALID CREDENTIALS';
  END IF;
END P_AUTHENTICATION; */

public class AuthenticationUsingCallableStatement {
private static final String AUTHENTICATION_PROCEDURE= "{ call P_AUTHENTICATION(?,?,?) }";
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
				CallableStatement cs = con.prepareCall(AUTHENTICATION_PROCEDURE);
				){
			//take the input parameter
			String name = null, pwd = null;
			if (sc != null) {
				System.out.println("Enter user name : ");
				name = sc.next();
				System.out.println("Enter user password : ");
				pwd = sc.next();
			}//if
		   if (cs!=null) {
			//register Out param to jdbc data types
			   cs.registerOutParameter(3, Types.VARCHAR);
			   //SET value ti IN param
			   cs.setString(1, name);
			   cs.setString(2, pwd);
			   //call pl/sql query
			   cs.execute();
			   //gather out param result
			   System.out.println(cs.getString(3));
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
