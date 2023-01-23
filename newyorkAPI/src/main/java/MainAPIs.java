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
				System.out.println("\t \tChoose One table:\t \t");
				System.out.println("\t\t 1.Artical Table ");
				System.out.println("\t\t 2.section Table ");
				System.out.println("\t\t 3. Author Table ");
				System.out.println(" *********************************************** ");
				Scanner scanner = new Scanner(System.in);

				int option = sc.nextInt();
				switch (option) {

				case 1:
					ArticalAPI.main();
					break;

				case 2:
					sectionAPI.main();
					break;

				case 3:
					AuthorAPI.main();

					break;
				}
			}
			exit = false;
		} catch (Exception e) {
			System.out.println(e);
		}

	}


}
