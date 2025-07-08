package miniproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class privateCloudLogin {
    private static Connection connection;
    private static Scanner scanner;

    public privateCloudLogin(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public static void loginform(){
        System.out.println("**************PRIVATE__CLOUD**************");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter userName: ");
        String uname = scanner.next();
        System.out.println("Enter password: ");
        String pass = scanner.next();
        checkuser(uname,pass);
    }

    public static void checkuser(String name , String pass){
        String query = "SELECT * FROM privatecloudregisteration";
        try(PreparedStatement check = connection.prepareStatement(query)){
            ResultSet resultSet = check.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getString("UserName");
                String password = resultSet.getString("password");
                String encpass = resultSet.getString("encryptedPass");
                int id = resultSet.getInt("UserId");
                if(username.equals(name) && password.equals(pass)){
                    System.out.println("logined successfully");
                    loginverificationpage(id,encpass);
                    break;
                } else if (username.equals(name) || password.equals(pass)){
                    System.out.println("UserName or password is incorrect ");
                    loginform();
                    break;
                }
                else {
//                    privateCloudRegisteration.register();
                    continue;
                }
            }
            if(resultSet.next() == check.isClosed()){
                System.out.println("New user ");
                while (true){
                    System.out.println("press 1 to REGISTER \npress 2 to GO BACK");
                    Scanner input = new Scanner(System.in);
                    int number =-1;
                    if (input.hasNextInt()) {
                        number = input.nextInt();
                    }else {
                        System.out.println("please enter number choice ");
                        input.next();
                        continue;
                    }
                    switch (number){
                        case 1:privateCloudRegisteration.register();break;
                        case 2:HomePage.Login();break;
                        default:System.out.println("please choose correct option ");
                    }
                }
            }
//            System.out.println("New user ");
//            privateCloudRegisteration.register();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void loginverificationpage(int id,String encpass){
        System.out.println("--------------WELCOME TO PRIVATE CLOUD--------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Token: ");
        String tok = scanner.next();
        if (encpass.equals(tok)){
           privatecloudhomepage(id);
        }
        else {
            System.out.println("wrong token plz enter correct TOKEN ");

            int select = -1;
            Scanner input = new Scanner(System.in);
            while (true){
                System.out.println("press 1 > ENTER TOKEN AGAIN TO VERIFY \npress 2 > GO BACK");
                if (input.hasNextInt()){
                    select = input.nextInt();
                }else {
                    System.out.println("Please enter number choice ");
                    input.next();
                    continue;
                }
                switch (select){
                    case 1:loginverificationpage(id,encpass);break;
                    case 2:HomePage.Login();break;
                    default:System.out.println("Please enter correct option ");
                }
            }
        }

    }



    public static void privatecloudhomepage(int id){
        System.out.println(" VERIFIED USER ");
       while (true){
           System.out.println("press 1 > UPLOAD FILES \n press 2> VIEW YOUR FILES \npress 3> LOGOUT ");
           int select =  -1;
           if (scanner.hasNextInt()) {
               select = scanner.nextInt();
           }else {
               System.out.println("Please press number choice");
               scanner.next();
               continue;
           }

           switch (select){
               case 1:filesOfPrivateCloud.insertFile(id);
                   break;

               case 2:filesOfPrivateCloud.userfiles(id);
                   break;

               case 3:HomePage.welcomeToHome();break;
               default:System.out.println("Please enter correct option");
                   privatecloudhomepage(id);
           }
       }
    }
}
