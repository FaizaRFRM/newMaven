import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.google.gson.Gson;

public class sectionAPI {
	public static void main() throws Exception {

		try {
			boolean exit = true;
			while (exit) {
				Scanner sc = new Scanner(System.in);
				System.out.println("\t \tChoose One Option:\t \t");
				System.out.println("\t\t 1.read JSON file in concol ");
				System.out.println("\t\t 2.ReadOrderly ");
				System.out.println("\t\t 3. createTable ");
				System.out.println("\t\t 4. insert ");
				System.out.println("\t\t 5. readFromTable ");
				System.out.println(" *********************************************** ");
				Scanner scanner = new Scanner(System.in);

				int option = sc.nextInt();
				switch (option) {

				case 1:
					sectionAPI.ReadJsonFile();
					break;

				case 2:
					sectionAPI.ReadOrderly();
					break;

				case 3:
					sectionAPI.createTable();

					break;
				case 4:
					sectionAPI.insert();

					break;
//				case 5:
//					sectionAPI.readFromTable();
//					break;
				}
			}
			exit = false;
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	public static void ReadJsonFile() throws Exception {

		String jsonUrl = "https://api.nytimes.com/svc/topstories/v2/world.json?api-key=UD8wewzN6GNwnrbBU19uCJvj6T0eK8JA\r\n"
				+ "";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());

	}
	public static void ReadOrderly() throws Exception {
		String jsonUrl = "https://api.nytimes.com/svc/topstories/v2/world.json?api-key=UD8wewzN6GNwnrbBU19uCJvj6T0eK8JA\r\n"
				+ "";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		Gson gn = new Gson();
		section[] useGson = gn.fromJson(response.body(), section[].class);
		for (section RAC : useGson) {
//			String status;
//			String copyright;
//			String last_updated;
//			int num_results;
//			results[] Results;
			
			
			
			System.out.println("status" + RAC.getStatus());
			System.out.println("copyright" + RAC.getCopyright());
			System.out.println("last_updated" + RAC.getLast_updated());
			System.out.println("num_results" + RAC.getNum_results());
			System.out.println("Results" + RAC.getResults()[0]);
		}
	}
	
	public static void createTable() throws Exception {

		final String url = "jdbc:mysql://localhost:3306/APIs";

		final String user = "root";
		final String pass = "root";
		Connection conn = null;
		try {
			
//			String status;
//			String copyright;
//			String last_updated;
//			int num_results;
//			String section;
//			String subsection;
//			String title;
//			String uri;
//			String byline;
			
			
			String sql = ("CREATE TABLE sectionTable(" + "id int Primary Key AUTO_INCREMENT,"
					+ "status varchar(225)," 
					+ "copyright varchar(225),"
					+ "last_updated varchar(225)," + "pub_date date,"
					+ "num_results Integer," 
					+ "section varchar(225)," 
					+ "subsection varchar(225)," 
					+ "title varchar(225)," 
					+ "uri varchar(225)," 
					 + "byline varchar(225))");
			
			Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			DriverManager.registerDriver(driver);

			conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();

			int m = st.executeUpdate(sql);
			if (m >= 0)
				{System.out.println("Created table in given database...");

				}else {
				System.out.println("failed to create");
				}
			conn.close();
		} catch (Exception ex) {

			System.err.println(ex);
		}
	}
	
	
	public static void insert() throws Exception {
		String jsonUrl = "https://api.nytimes.com/svc/topstories/v2/world.json?api-key=UD8wewzN6GNwnrbBU19uCJvj6T0eK8JA\r\n"
				+ "";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		Gson gn = new Gson();
		section[] useGson = gn.fromJson(response.body(), section[].class);
		for (section RAC : useGson) {
			final String url = "jdbc:mysql://localhost:3306/APIs";

			final String user = "root";
			final String pass = "root";
			Connection conn = null;
			try {
//				String status;
//				String copyright;
//				String last_updated;
//				int num_results;
//				String section;
//				String subsection;
//				String title;
//				String url;
//				String uri;
//				String byline;
			
				String sql = "insert into sectionTable (status,copyright,last_updated,num_results,section,subsection,title,uri,byline)"
						+ "values ('" +RAC.getStatus() + "','" 
						+ RAC.getCopyright() +"','" 
						+ RAC.getLast_updated()+"','" 
						+ RAC.getNum_results()+"','"
						+ RAC.getResults()[0].getSection()+ "','" 
						+ RAC.getResults()[0].getSubsection()+ "','" 
						+ RAC.getResults()[0].getTitle()+ "','" 
						+ RAC.getResults()[0].getTitle()+ "','" 
						+ RAC.getResults()[0].getUri()+ "','" 
						+ RAC.getResults()[0].getByline()+"')";

				Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

				DriverManager.registerDriver(driver);

				conn = DriverManager.getConnection(url, user, pass);
				Statement st = conn.createStatement();
		
				int m = st.executeUpdate(sql);
				if (m >= 0) 
					{System.out.println("inserted in given database..." + sql);
					}
				else {
					System.out.println("failed");
				}
				
				conn.close();
			}catch (Exception ex) {

				System.err.println(ex);
			}

		}
	}
	
//	public static void readFromTable(){
//
//		final String url = "jdbc:mysql://localhost:3306/APIs";
//		   final String user = "root";
//		   final String pass = "root";
//		   
//		   
//		   
//		  String QUERY = "SELECT * FROM sectionTable";
//
//		      Connection conn=null;
//		      
//		 try {
//			 conn = DriverManager.getConnection(url, user, pass);
//		 Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//         Statement stmt = conn.createStatement();
//	     DriverManager.registerDriver(driver);
//	     ResultSet rs=stmt.executeQuery(QUERY);
//			 while(rs.next()) {
////					String status;
////					String copyright;
////					String last_updated;
////					int num_results;
////					String section;
////					String subsection;
////					String title;
////					String uri;
////					String byline;
//				 
//				int id=rs.getInt("id");
//				String status=rs.getString("status");
//				String copyright=rs.getString("copyright");
//				String last_updated=rs.getString("last_updated");
//				int num_results=rs.getInt("num_results");
//				String section=rs.getString("section");
//				String subsection=rs.getString("subsection");
//				String title=rs.getString("title");
//				String uri=rs.getString("uri");
//				String byline=rs.getString("byline");
//
//				
//				
//				
//			     System.out.println("id :" + id);
//			     System.out.println("status :" +status);
//			     System.out.println("copyright" +copyright);
//			     System.out.println("last_updated" +last_updated);
//			     System.out.println("num_results" +num_results);
//			     System.out.println("section" +section);
//			     System.out.println("subsection"+subsection);
//			     System.out.println("title"+title);
//			     System.out.println("uri"+uri);
//			     System.out.println("byline"+byline);
//			     System.out.println("===========================================================");
//			   
//			 }
//			 conn.close() ;
//		 }  catch (Exception ex) {
//	           
//	            System.err.println(ex);
//   }
//    }
//	
}
