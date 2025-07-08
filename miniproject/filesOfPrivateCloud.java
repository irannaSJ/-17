package miniproject;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class filesOfPrivateCloud {
    private static Connection connection;
    private static Scanner scanner;

    public filesOfPrivateCloud(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public static void insertFile(int id){
        System.out.println("**************UPLOAD FILES TO PRIVATE_CLOUD**************");
        System.out.println("Enter the file name : ");
        String fname = scanner.next();
        System.out.println("Enter the file path : ");
        String fpath = scanner.next();
        File filepath = new File(fpath);

        if(filepath.exists()){
            String fhash = filehashcalculator(fpath);
            boolean check = checkfile(fhash);
            if(check == true){
                System.out.println("file already present in database");
                insertFile(id);
            }
            else {
                filetodb(fname,fpath,fhash,id);
            }
        }
        else {
            System.out.println("file not exists");
            privateCloudLogin.privatecloudhomepage(id);
        }
    }

    public static String filehashcalculator(String fpath){
        String FHash = " ";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[8192];
            int bytesRead;
            FileInputStream fis = new FileInputStream(fpath);
            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
            StringBuilder hashform = new StringBuilder();
            for (byte b : digest.digest()) {
                hashform.append(String.format("%02x", b));
            }
            FHash = hashform.toString();
//                return FHash;
        } catch (Exception e) {
            System.out.println("file not found");
        }
        return FHash;
    }

    public static boolean checkfile(String fhash){
        String qry = "SELECT * FROM filesofprivatecloud";
        try (PreparedStatement preparedStatement = connection.prepareStatement(qry)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String FHASH = resultSet.getString("FileHash");
                if (FHASH.equals(fhash)){
                    return true;
                }
                else {
                    continue;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void filetodb(String fname,String fpath,String fhash,int id){
        String qry = "INSERT INTO filesofprivatecloud(FileName,FilePath,FileHash,Uid) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(qry)){
            preparedStatement.setString(1,fname);
            preparedStatement.setString(2,fpath);
            preparedStatement.setString(3,fhash);
            preparedStatement.setInt(4,id);

            int affectedrows = preparedStatement.executeUpdate();
            if(affectedrows > 0){
                System.out.println("file inserted successfully");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void userfiles(int id){
        String qry = "SELECT * FROM filesofprivatecloud where Uid = ?";
        System.out.println("  FIlE ID  "+ "  FILE NAME  ");
        try (PreparedStatement preparedStatement = connection.prepareStatement(qry)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String fname = resultSet.getString("FileName");
                int fid = resultSet.getInt("Fid");

                System.out.println("   " + fid + "   " + "    "+ fname + "     ");
            }
            privateCloudLogin.privatecloudhomepage(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
