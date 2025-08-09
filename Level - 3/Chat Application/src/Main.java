import java.util.Scanner;
import server.Server;
import client.Client;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select mode:");
        System.out.println("1) Start Server");
        System.out.println("2) Start Client");
        System.out.print("Enter choice: ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            Server.main(new String[]{}); // Start server
        } else if (choice.equals("2")) {
            Client.main(new String[]{}); // Start client
        } else {
            System.out.println("Invalid choice.");
        }
        sc.close();
    }
}
