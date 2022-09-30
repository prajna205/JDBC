package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MoneyTransferTxmgmt {

	public static void main(String[] args) {
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
				Statement st = con.createStatement();
				Scanner sc = new Scanner(System.in);
				) {
			//make the auto commit mode of driver to false
			if(con != null)
				con.setAutoCommit(false);
			//take the inputs
			int srcAcct =0 , destAcct =0;
			Float amt = 0.0f;
			if (sc!=null) {
				System.out.println("Enter the source account : ");
				srcAcct = sc.nextInt();
				System.out.println("Enter destination account");
				destAcct = sc.nextInt();
				System.out.println("Enter amount to transfer");
				amt = sc.nextFloat();
			}
			int result[] = null;
			if (st !=null) {
				//write withdraw query
				st.addBatch("UPDATE JDBC_ACCOUNT SET AMOUNT = AMOUNT - " + amt + "WHERE ACNO =  " + srcAcct);
				//write deduct query
				st.addBatch("UPDATE JDBC_ACCOUNT SET AMOUNT = AMOUNT + " + amt + "WHERE ACNO =  " + destAcct);
				//execute batch query
				result = st.executeBatch();
			}
			boolean flag = false;
			for (int i = 0; i < result.length; i++) {
				if (result[i] ==0) {
					flag =true;
					break;
				}//if
			}//for
			if (flag) {
				con.rollback();
				System.out.println("Transaction failed.");
			}
			else {
				con.commit();
				System.out.println("Transaction successful.");
			}
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	}//main
}//class
