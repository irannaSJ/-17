package miniproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class publicCloudRegisteration {
    private static Connection connection;
    private static Scanner scanner;

    public publicCloudRegisteration(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public static void checkuser(String name , String mail, int age, String gender,String password) {
        String query = "Select * from publicCloudRegisteration";
//        Scanner scanner = new Scanner(System.in);
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("Email");

                    if (username.equals(name) || email.equals(mail)) {
                        System.out.println("user is present ");
                        System.out.println("press 1 to create a new account \npress 2 to login \npress 3 to Exit");
                        int choice = scanner.nextInt();
                        switch (choice){
                            case 1: register();
                                    break;
                            case 2: publicCloudLogin.loginform();
                                    break;
                            case 3:HomePage.welcomeToHome();
                                    break;
                            default:System.out.println("Please enter correct option: ");
                        }
                    }
                    else {
                        continue;
                    }
            }
            insertUser(name,age,gender,mail,password);
        }catch (SQLException e){
            System.out.println("something went wrong");
            HomePage.welcomeToHome();
        }
    }

    public static void register(){
        System.out.println("REGISTERATION --------------");
//        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Username : ");
        String name = scanner.next();
        System.out.println("Enter the age: ");
        int age= scanner.nextInt();
        System.out.println("enter the Gender: ");
        String gender = scanner.next();
        System.out.println("Enter the Email : ");
        String email = scanner.next();
        System.out.println("Enter the password : ");
        String password = scanner.next();

        checkuser(name , email, age, gender ,password);
    }

    public static void insertUser(String name , int age,String gender,String email,String password){
        String query = "INSERT INTO publiccloudregisteration(UserName,Age,Gender,Email,Password) values (?,?,?,?,?)";
//        Scanner scanner = new Scanner(System.in);
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,password);

            int affectedrows = preparedStatement.executeUpdate();
            if(affectedrows > 0 ){
                System.out.println("Inserted successfully");
                publicCloudLogin.loginform();
            }

        }catch (SQLException e){
            System.out.println("UserName with "+ name + " already exist ");
            System.out.println("press 1 to create a new account \npress 2 to login \npress 3 to Exit");
            int choice = scanner.nextInt();
            switch (choice){
                case 1: register();
                case 2: publicCloudLogin.loginform();
                case 3:return;
            }
        }
    }
}
