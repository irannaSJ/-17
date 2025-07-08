package miniproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class publicCloudLogin {
    private static Connection connection;
    private static Scanner scanner;

    public publicCloudLogin(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public static void loginform(){
        System.out.println("**************PUBLIC__CLOUD**************");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter userName: ");
        String uname = scanner.next();
        System.out.println("Enter password: ");
        String pass = scanner.next();
        checkuser(uname,pass);
    }

    public static void checkuser(String name , String pass){
        String query = "SELECT * FROM publiccloudregisteration";
        try(PreparedStatement check = connection.prepareStatement(query)){
            ResultSet resultSet = check.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getString("UserName");
                String password = resultSet.getString("Password");
                int id = resultSet.getInt("UserId");
                if(username.equals(name) && password.equals(pass)){
                    System.out.println("logined successfully");
                    loginHomepage(id);
                    break;
                } else if (username.equals(name) || password.equals(pass)){
                    System.out.println("UserName or password is incorrect ");
                    loginform();
                    break;
                }
                else {
//                    publicCloudRegisteration.register();
                    continue;
                }
            }
            if (resultSet.next() == check.isClosed()){
                System.out.println("NEW USER ");
                while (true){
                    System.out.println("press 1 > REGISTER \npress 2 > GO BACK");
                    Scanner input = new Scanner(System.in);
                    int number = -1;
                    if(input.hasNextInt()) {
                        number = input.nextInt();
                    }else {
                        System.out.println("Please enter number choice");
                        input.next();
                        continue;
                    }
                    switch (number){
                        case 1:publicCloudRegisteration.register();break;
                        case 2:HomePage.welcomeToHome();break;
                        default:System.out.println("please choose correct option ");
                    }
                }
            }
        }catch (SQLException e){
            System.out.println("SOMETHING WENT WRONG  \"PUBLIC CLOUD\"");
            HomePage.welcomeToHome();
        }
    }

    public static void loginHomepage(int id){
        System.out.println("--------------WELCOME TO PUBLIC CLOUD--------------");
        int identity = id;
        int ch =-1;
        System.out.println("press 1 to UPLOAD files\npress 2 to VIEW YOUR UPLOADED FILES \npress 3 to VIEW ALL FILES\npress 4 to LOGOUT");
        while (true) {
            if(scanner.hasNextInt()){
                ch = scanner.nextInt();
            }
            else {
                System.out.println("please enter number choice ");
                scanner.next();
                continue;
            }

            switch (ch) {
                case 1:
                    filesOfPublicCloud.insertFile(id);
                    break;
                case 2:
                    filesOfPublicCloud.userfiles(id);
                    break;
                case 3:
                    filesOfPublicCloud.allfiles(id);
                    break;
                case 4:
                    HomePage.welcomeToHome();
                default:
                    System.out.println("Please enter valid choice ");
                    loginHomepage(id);
            }
        }
    }

}
