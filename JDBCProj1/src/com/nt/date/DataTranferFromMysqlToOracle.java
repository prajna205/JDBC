package com.nt.date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DataTranferFromMysqlToOracle {
private static final String INSERT_CUSTOMER_QUERY_ORACLE = "INSERT INTO JDBC_CUSTOMER VALUES(CID_SEQ.NEXTVAL,?,?,?,?";
private static final String GET_CUSOMER_QUERY_MYSQL = "SELECT CNAME,CADDRS,BILLAMT,DTOP FROM JDBC_CUSTOMER";
	public static void main(String[] args) {
		//load jdbc drivers
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//operations
		try (Scanner sc = new Scanner(System.in);
				Connection oracleCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1998");
				Connection mysqlCon = DriverManager.getConnection("jdbc:mysql://@localhost:3306/jdbcdb","root","1998");
				Statement mysqlSt = mysqlCon.createStatement();
				PreparedStatement oraclePs = oracleCon.prepareStatement(INSERT_CUSTOMER_QUERY_ORACLE);
				ResultSet rs = mysqlSt.executeQuery(GET_CUSOMER_QUERY_MYSQL);
				){
			if (rs!= null) {
				while (rs.next()) {
					//get records from resultset obj
					String name= rs.getString(1);
					String addr= rs.getString(2);
					Float billAmt= rs.getFloat(3);
					java.sql.Timestamp ts = rs.getTimestamp(4);
					//set the values to oracle
					oraclePs.setString(1, name);
					oraclePs.setString(2, addr);
					oraclePs.setFloat(3, billAmt);
					oraclePs.setTimestamp(4, ts);
					//send and execute the query
					oraclePs.executeUpdate();
				}//while
				System.out.println("Records are inserted from mysql to oracle");
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
