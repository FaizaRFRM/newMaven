package databaseProject;
	import java.sql.*;
	import java.util.*;

public class dataConnection {
	   
	    public static void main(String a[]) {

	        String url = "jdbc:mysql://localhost:3306/dataConnection";

	        String user = "root";
	        String pass = "root";

	        Scanner scanner = new Scanner(System.in);

	        System.out.println("enter name");
	        String name = scanner.next();

	        System.out.println("enter roll no");
	        Integer roll = scanner.nextInt();

	        System.out.println("enter class");
	        String cls = scanner.next();

	        String sql = "insert into Students values('" + name
	                + "'," + roll + ",'" + cls + "')";
	        
	        Connection con = null;

	        try {

	            Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	          
	            DriverManager.registerDriver(driver);

	            con = DriverManager.getConnection(url, user,
	                    pass);

	            Statement st = con.createStatement();

	            int m = st.executeUpdate(sql);
	            if (m >=1)
	                System.out.println(
	                        "inserted successfully : " + sql);
	            else
	                System.out.println("insertion failed");

	            con.close();
	        }

	        catch (Exception ex) {
	           
	            System.err.println(ex);
	        }
	    }
}