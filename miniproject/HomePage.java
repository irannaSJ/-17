package miniproject;

import java.sql.Connection;
import java.util.Scanner;

public class HomePage {
    private static Connection connection;
    private static Scanner scanner;

    public HomePage(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public static void welcomeToHome(){
        System.out.println("**************HYBRID__CLOUD**************");
        System.out.println("press 1 to REGISTER \npress 2 LOGIN\npress 3 to EXIT");
//        Scanner scanner = new Scanner(System.in);
        while (true){
            int choice = -1;
            if(scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }else {
                System.out.println("Please enter number choice ");
                scanner.next();
                continue;
            }
            switch (choice){
                case 1: Register();
                    break;
                case 2: Login();
                    break;
                case 3:System.exit(0);
            }
        }
    }

    public static void Register(){
        System.out.println("**************HYBRID__CLOUD**************");
//        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("press 1 to register to PUBLIC CLOUD \npress 2 to register to PRIVATE CLOUD \npress 3 to GO BACK");
            int opt = -1;
            if(scanner.hasNextInt()) {
                opt = scanner.nextInt();
            }else {
                System.out.println("Please enter number choice ");
                scanner.next();
                continue;
            }
            switch (opt){
                case 1:publicCloudRegisteration.register();
                    break;
                case 2:privateCloudRegisteration.register();
                    break;
                case 3:welcomeToHome();break;
//            case "3":RSAencrypt.token("12321");
                default:System.out.println("Enter correct option");
            }
        }
    }

    public static void Login(){
        System.out.println("**************HYBRID__CLOUD**************");
//        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("press 1 to login to PUBLIC CLOUD \npress 2 to login to PRIVATE CLOUD");
            int opt = -1;
            if (scanner.hasNextInt()) {
                opt = scanner.nextInt();
            }else {
                System.out.println("Please enter number choice ");
                scanner.next();
                continue;
            }
            switch (opt){
                case 1:publicCloudLogin.loginform();
                    break;
                case 2:privateCloudLogin.loginform();
                    break;
                default:System.out.println("press number from above options");
            }
        }
    }
}
