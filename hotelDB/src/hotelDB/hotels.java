package hotelDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class hotels {
	
	public static void readFromTable(){

		final String url = "jdbc:mysql://localhost:3306/HotelDBMS";
		   final String user = "root";
		   final String pass = "root";
		   final String QUERY = "SELECT * FROM Hotels ORDER BY id LIMIT 10";

		      Connection conn=null;
		 try {
			 conn = DriverManager.getConnection(url, user, pass);
		 Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
         Statement stmt = conn.createStatement();
	     DriverManager.registerDriver(driver);
	     ResultSet rst=stmt.executeQuery(QUERY);
			 while(rst.next()) {
			
				int id=rst.getInt("id");
				String hotel_name=rst.getString("hotel_name");
				String hotel_location=rst.getString("hotel_location");
				Date created_date=rst.getDate("created_date");
				Date updated_date=rst.getDate("updated_date");
				Boolean is_Active=rst.getBoolean("is_Active");
			     System.out.println("id :" + id);
			     System.out.println("hotel_name :" +hotel_name);
			     System.out.println("hotel_location" +hotel_location);
			     System.out.println("created_date" +created_date);
			     System.out.println("updated_date" +updated_date);
			     System.out.println("is_Active"+is_Active);
			   
			 }
			 conn.close() ;
		 }  catch (Exception ex) {
	           
	            System.err.println(ex);
   }
		
	    }
}
