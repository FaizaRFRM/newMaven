import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.google.gson.Gson;

public class AuthorAPI {
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
					AuthorAPI.ReadJsonFile();
					break;

				case 2:
					AuthorAPI.ReadOrderly();
					break;

				case 3:
					AuthorAPI.createTable();

					break;
				case 4:
					AuthorAPI.insert();

					break;
//				case 5:
//				ArticalAPI.readFromTable();
				}
			}
			exit = false;
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	public static void ReadJsonFile() throws Exception {

		String jsonUrl = "https://api.nytimes.com/svc/books/v3/reviews.json?author=Stephen+King&api-key=UD8wewzN6GNwnrbBU19uCJvj6T0eK8JA\r\n";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());

	}
	public static void ReadOrderly() throws Exception {
		String jsonUrl = "https://api.nytimes.com/svc/books/v3/reviews.json?author=Stephen+King&api-key=UD8wewzN6GNwnrbBU19uCJvj6T0eK8JA\r\n";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		Gson gn = new Gson();
		section[] useGson = gn.fromJson(response.body(), section[].class);
		for (section RAC : useGson) {
//		String status;
//			String copyright;
//			int num_results;
			
//			results[] Results;
			
//			
			
			System.out.println("status" + RAC.getStatus());
			System.out.println("copyright" + RAC.getCopyright());
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
//			int num_results;
			
//			results[] Results;
			
//			String byline;
//			String book_title;
//			String book_author;
//			String uuid;
			
//			String  isbn13[];
			
			
			String sql = ("CREATE TABLE AuthorTable(" 
			        + "id int Primary Key AUTO_INCREMENT,"
					+ "status varchar(225)," 
					+ "copyright varchar(225),"
					+ "num_results Integer," 
					+ "byline varchar(225)," 
					+ "book_title varchar(225)," 
					+ "book_author varchar(225)," 
					+ "uuid varchar(225)," 
					 + "isbn13 varchar(225))");
			
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
		String jsonUrl = "https://api.nytimes.com/svc/books/v3/reviews.json?author=Stephen+King&api-key=UD8wewzN6GNwnrbBU19uCJvj6T0eK8JA\r\n";
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
//				int num_results;
				
//				results[] Results;
				
//				String byline;
//				String book_title;
//				String book_author;
//				String uuid;
				
//				String  isbn13[];
			
				String sql = "insert into AuthorTable (status,copyright,num_results,byline,book_title,book_author,uuid,isbn13)"
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
}
