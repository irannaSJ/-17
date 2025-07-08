package miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HYBRIDCLOUD {
    private static final String url = "jdbc:mysql://localhost:3306/hybridcloud";
    private static final String username = "root";
    private static final String password = "Sksv@12345";

    public static void main(String[] args){
        try{
            Class.forName("con.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
//            e.printStackTrace();
            System.out.println("connection is in process ");
        }
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            HomePage HomePage = new HomePage(connection,scanner);
            publicCloudRegisteration publicCloudRegisteration = new publicCloudRegisteration(connection,scanner);
            publicCloudLogin publicCloudLogin = new publicCloudLogin(connection,scanner);
            filesOfPublicCloud filesOfPublicCloud = new filesOfPublicCloud(connection,scanner);
            privateCloudRegisteration privateCloudRegisteration = new privateCloudRegisteration(connection,scanner);
            privateCloudLogin privateCloudLogin = new privateCloudLogin(connection,scanner);
            filesOfPrivateCloud filesOfPrivateCloud = new filesOfPrivateCloud(connection,scanner);


            System.out.println("connection successful");
            HomePage.welcomeToHome();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("failed");
        }
    }
}
