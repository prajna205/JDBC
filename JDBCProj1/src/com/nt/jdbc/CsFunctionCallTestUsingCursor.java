package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*CREATE OR REPLACE FUNCTION FS_GET_STUDENT_DETAILS 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, ADDRESS OUT VARCHAR2 
) RETURN FLOAT AS 
    aggregation float ;
BEGIN
  SELECT SNAME,SADDR,AVG INTO NAME,ADDRESS,aggregation FROM STUDENT WHERE SNO = NO;
  RETURN aggregation;
  
END FS_GET_STUDENT_DETAILS;*/

public class CsFunctionCallTestUsingCursor {
private static final String GET_STUDENT_DETAILS_PROCEDURE= "{ ? = call FS_GET_STUDENT_DETAILS (?,?,?) }";
//first ?  represents avg, 2nd - student number(input param), 3rd -> name, 4th-> addeess
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
				CallableStatement cs = con.prepareCall(GET_STUDENT_DETAILS_PROCEDURE);
				){
			//take the input parameter
			int no = 0;
			if (sc != null) {
				System.out.println("Enter Student number : ");
				no= sc.nextInt();
			}//if
		   if (cs!=null) {
			//register Out param to jdbc data types
			   cs.registerOutParameter(1, OracleTypes.FLOAT);
			   cs.registerOutParameter(3, OracleTypes.VARCHAR);
			   cs.registerOutParameter(4, OracleTypes.VARCHAR);
			   //SET value ti IN param
			   cs.setInt(2, no);
			   //call pl/sql query
			   cs.execute();
			   System.out.println("Student Name : " + cs.getString(3));
			   System.out.println("Student Address : " + cs.getString(4));
			   System.out.println("Student Average : " + cs.getFloat(1));
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
