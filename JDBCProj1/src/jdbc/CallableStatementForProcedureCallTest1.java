package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;


public class CallableStatementForProcedureCallTest1 {
private static final String GET_EMP_DATA_PROCEDURE= "{ call P_GET_EMP_DETAILS(?,?,?,?) }";
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
				CallableStatement cs = con.prepareCall(GET_EMP_DATA_PROCEDURE);
				){
			//take the input parameter
			int eno = 0;
			if (sc != null) {
				System.out.println("Enter Employee num : ");
				eno = sc.nextInt();
			}//if
		   if (cs!=null) {
			//register Out param
			   cs.registerOutParameter(2, Types.VARCHAR);
			   cs.registerOutParameter(3, Types.VARCHAR);
			   cs.registerOutParameter(4, Types.FLOAT);
			   //SET value ti IN param
			   cs.setInt(1, eno);
			   //cexecute the query
			   cs.execute();
			   //gather out param result
			   System.out.println("Employee Name : " + cs.getString(2));
			   System.out.println("Employee Job : " + cs.getString(3));
			   System.out.println("Employee Sal : " + cs.getString(4));
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
