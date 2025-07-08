package miniproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class privateCloudRegisteration {
    private static Connection connection;
    private static Scanner scanner;

    public privateCloudRegisteration(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public static void register(){
        System.out.println("REGISTERATION --------------");
        Scanner scanner = new Scanner(System.in);
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
        String encpas =  RSAencrypt.token(password);
        checkuser(name , email, age, gender ,password,encpas);
    }

    public static void checkuser(String uname,String email,int age,String gender ,String password,String encpass){
        String qry = "SELECT * FROM privatecloudregisteration";
        try(PreparedStatement prepstate = connection.prepareStatement(qry)){
            ResultSet rsltset = prepstate.executeQuery();
//            if (rsltset.next()){
            while (rsltset.next()){
                String name = rsltset.getString("UserName");
                String mail = rsltset.getString("Email");
                if (name.equals(uname) || mail.equals(email)){
                    System.out.println("User is present ");
                    HomePage.welcomeToHome();
                }
                else {
                    continue;
                }
//            }
            }
            insertUser(uname,email,age,gender,password,encpass);
//            else {
//                System.out.println("DB is empty");
//                insertUser(uname,email,age,gender,password,encpass);
//            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void insertUser(String uname,String email,int age,String gender ,String password,String encpass){
        String qry = "INSERT INTO privatecloudregisteration(UserName,Age,Gender,Email,password,encryptedPass) values (?,?,?,?,?,?)";
        try (PreparedStatement insert = connection.prepareStatement(qry)) {
            insert.setString(1,uname);
            insert.setInt(2,age);
            insert.setString(3,gender);
            insert.setString(4,email);
            insert.setString(5,password);
            insert.setString(6,encpass);

            int affectedrows = insert.executeUpdate();
            if(affectedrows >0){
                System.out.println("Registered successfully");
                System.out.println("PLEASE NOTE DOWN THE TOKEN: "+ encpass);
                HomePage.Login();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
