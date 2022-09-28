package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*CREATE OR REPLACE PROCEDURE P_GET_EMP_BY_INITIAL_CHARS 
(
  INITCHAR IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
  OPEN DETAILS FOR
    SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE INITCHAR; 
END P_GET_EMP_BY_INITIAL_CHARS;*/

public class CallableStatementTestUsingCursor {
private static final String GET_EMP_DETAILS_PROCEDURE= "{ call P_GET_EMP_BY_INITIAL_CHARS(?,?) }";
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
				CallableStatement cs = con.prepareCall(GET_EMP_DETAILS_PROCEDURE);
				){
			//take the input parameter
			String initchars = null;
			if (sc != null) {
				System.out.println("Enter Initial Characters of Employee name : ");
				initchars = sc.next().toUpperCase();
			}//if
		   if (cs!=null) {
			//register Out param to jdbc data types
			   cs.registerOutParameter(2, OracleTypes.CURSOR);
			   //SET value ti IN param
			   cs.setString(1, initchars+ "%");
			   //call pl/sql query
			   cs.execute();
			   try (ResultSet rs = (ResultSet) (cs.getObject(2))){
				if (rs!=null) {
					System.out.println("Employee Records Starting with " + initchars);
					boolean isRsEmpty = true;
					while (rs.next()) {
						isRsEmpty = false;
						System.out.println(rs.getInt(1)+"  "+ rs.getString(2) +  "  " + rs.getString(3) +"  "+ rs.getFloat(4) );
					}//while
					if(isRsEmpty)
						System.out.println("No Records found...");
				}//if
			}//if
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
