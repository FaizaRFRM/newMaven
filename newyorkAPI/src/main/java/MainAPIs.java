import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

//import maven2.MVNMain;

public class MainAPIs  {

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
					ArticalAPI apis=new ArticalAPI();
					apis.ReadJsonFile();
					break;

				case 2:
//					MVNMain.ReadOrderly();
					break;

				case 3:
//					MVNMain.createTable();

					break;
				case 4:
//				MVNMain.insert();

					break;
				case 5:
//				MVNMain.readFromTable();
					break;
				case 6:
//				MVNMain.updateById();
					break;
				case 7:
//				MVNMain.deleteById();
					break;
				}
			}
			exit = false;
		} catch (Exception e) {
			System.out.println(e);
		}

	}


}
