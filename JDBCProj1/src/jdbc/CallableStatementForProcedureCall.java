package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CallableStatementForProcedureCall {
private static final String CALL_PROCEDURE= "{ call first_pro(?,?) }";
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);
				){
			//take the input parameter
			int num = 0;
			if (sc != null) {
				System.out.println("Enter the number : ");
				num = sc.nextInt();
			}//if
		   if (cs!=null) {
			//register Out param
			   cs.registerOutParameter(2, Types.INTEGER);
			   //SET value ti IN param
			   cs.setInt(1, num);
			   //cexecute the query
			   cs.execute();
			   //gather out param result
			   int result = cs.getInt(2);
			   System.out.println("Square of "+num +" is : "+ result);
		}//if
		}//try 
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//main
}//calss
