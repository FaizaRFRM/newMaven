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


public class ArticalAPI {
	public static void main(String[] args) throws Exception {

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
				System.out.println("\t\t 6. updateById ");
				System.out.println("\t\t 7. deleteById ");
				System.out.println(" *********************************************** ");
				Scanner scanner = new Scanner(System.in);

				int option = sc.nextInt();
				switch (option) {

				case 1:
//					ArticalAPI apis=new ArticalAPI();
					ArticalAPI.ReadJsonFile();
					break;

				case 2:
					ArticalAPI.ReadOrderly();
					break;

				case 3:
					ArticalAPI.createTable();

					break;
				case 4:
					ArticalAPI.insert();

					break;
				case 5:
				ArticalAPI.readFromTable();
					break;
				case 6:
//				ArticalAPI.updateById();
					break;
				case 7:
//				ArticalAPI.deleteById();
					break;
				}
			}
			exit = false;
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	public static void ReadJsonFile() throws Exception {

		String jsonUrl = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=UD8wewzN6GNwnrbBU19uCJvj6T0eK8JA";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());

	}
	public static void ReadOrderly() throws Exception {
		String jsonUrl = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=UD8wewzN6GNwnrbBU19uCJvj6T0eK8JA";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		Gson gn = new Gson();
		artical[] useGson = gn.fromJson(response.body(), artical[].class);
		for (artical RAC : useGson) {
//			status,copyright,response{docs(web_url,source,keywords[(name,value,rank,major)],pub_date,section_name,subsection_name,byline{original,person[]},_id,uri)}
//			private  String status;
//		     private  String copyright;
//		     private  response Response;
			
			
			System.out.println("status" + RAC.getStatus());
			System.out.println("copyright" + RAC.getCopyright());
			System.out.println("Response" + RAC.getResponse());
		}
	}
	
	public static void createTable() throws Exception {

		final String url = "jdbc:mysql://localhost:3306/APIs";

		final String user = "root";
		final String pass = "root";
		Connection conn = null;
		try {
//			status,copyright,response{docs(web_url,source,keywords[(name,value,rank,major)],pub_date,section_name,subsection_name,byline{original,person[]},_id,uri)}
//			private  String status;
//		     private  String copyright;
//			private String web_url;
//			private String source;
//			private Date pub_date;
//			private String section_name;
//			private String subsection_name;
			String sql = ("CREATE TABLE articalTable(" + "id int Primary Key AUTO_INCREMENT,"
					+ "status varchar(225)," 
					+ "copyright varchar(225)," + "web_url varchar(225),"
					+ "source varchar(225)," + "pub_date date,"
					+ "section_name varchar(225)," 
					 + "subsection_name varchar(225))");
			
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
		String jsonUrl = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=UD8wewzN6GNwnrbBU19uCJvj6T0eK8JA";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		Gson gn = new Gson();
		artical[] useGson = gn.fromJson(response.body(), artical[].class);
		for (artical RAC : useGson) {
			final String url = "jdbc:mysql://localhost:3306/APIs";

			final String user = "root";
			final String pass = "root";
			Connection conn = null;
			try {
//				status,copyright,response{docs(web_url,source,keywords[(name,value,rank,major)],pub_date,section_name,subsection_name,byline{original,person[]},_id,uri)}
//				private  String status;
//			     private  String copyright;
//				private String web_url;
//				private String source;
//				private Date pub_date;
//				private String section_name;
//				private String subsection_name;
			
				String sql = "insert into articalTable (status,copyright,web_url,source,pub_date,section_name,subsection_name)"
						+ "values ('" +RAC.getStatus() + "','" + RAC.getCopyright() + "','" + RAC.getResponse().getDocs()+ "','" + RAC.getResponse().getDocs()+ "','" 
						+ RAC.getResponse().getDocs()+ "','" + RAC.getResponse().getDocs()
								+ "','" + RAC.getResponse().getDocs()[0]+"')";

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
	
	public static void readFromTable(){

		final String url = "jdbc:mysql://localhost:3306/APIs";
		   final String user = "root";
		   final String pass = "root";
		   
		   
		   
		  String QUERY = "SELECT * FROM articalTable";

		      Connection conn=null;
		      
		 try {
			 conn = DriverManager.getConnection(url, user, pass);
		 Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
         Statement stmt = conn.createStatement();
	     DriverManager.registerDriver(driver);
	     ResultSet rs=stmt.executeQuery(QUERY);
			 while(rs.next()) {
//					status,copyright,response{docs(web_url,source,keywords[(name,value,rank,major)],pub_date,section_name,subsection_name,byline{original,person[]},_id,uri)}
//					private  String status;
//				     private  String copyright;
//					private String web_url;
//					private String source;
//					private Date pub_date;
//					private String section_name;
//					private String subsection_name; 
				 
				 
				int id=rs.getInt("id");
				String status=rs.getString("status");
				int copyright=rs.getInt("copyright");
				String web_url=rs.getString("web_url");
				String source=rs.getString("source");
				Date pub_date=rs.getDate("pub_date");
				String section_name=rs.getString("section_name");
				String subsection_name=rs.getString("subsection_name");
				
				
				
			     System.out.println("id :" + id);
			     System.out.println("status :" +status);
			     System.out.println("copyright" +copyright);
			     System.out.println("web_url" +web_url);
			     System.out.println("source" +source);
			     System.out.println("pub_date" +pub_date);
			     System.out.println("section_name"+section_name);
			     System.out.println("subsection_name"+subsection_name);
			     System.out.println("===========================================================");
			   
			 }
			 conn.close() ;
		 }  catch (Exception ex) {
	           
	            System.err.println(ex);
   }
    }
}
