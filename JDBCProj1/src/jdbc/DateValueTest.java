package jdbc;

import java.text.SimpleDateFormat;

public class DateValueTest {

	public static void main(String[] args) throws Exception{
		String d1 = "20-10-1990";
		//convert the string date object to util date obj
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1 = sdf.parse(d1);
		//convert the util date object to sql date obj
		long ms = ud1.getTime();
		java.sql.Date sqd1 = new java.sql.Date(ms);
		System.out.println(d1);
		System.out.println(ud1);
		System.out.println(sqd1);
		System.out.println("------------------------------------------------");
		//if date is is given in the format yyyy-MM-dd then we can convert it
		//directly to sql date obj by valueOf() method
		String d2 = "1998-06-23";
		java.sql.Date sqd2 = java.sql.Date.valueOf(d2);
		System.out.println(d2);
		System.out.println(sqd2);
		System.out.println("-----------------------------------------------");
		//retriving date vaue object from db
		//convert sql date obj to util date object
		java.util.Date ud2 = sqd2;
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM-dd-yyyy");
		String d3 = sdf1.format(ud2);
		System.out.println(ud2);
		System.out.println(d3);
	}

}
