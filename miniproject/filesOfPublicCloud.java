package miniproject;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class filesOfPublicCloud {
    private static Connection connection;
    private static Scanner scanner;

    public filesOfPublicCloud(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }
    public static String FileHashCalculator(String filepath) {
        String FHash = " ";
        try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] buffer = new byte[8192];
                int bytesRead;
                FileInputStream fis = new FileInputStream(filepath);
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
            System.out.println("file hash not found");
        }
        return FHash;
    }

    public static void insertFile(int id){
        System.out.println("**************UPLOAD FILES TO PUBLIC_CLOUD**************");
        System.out.println("Enter the file name : ");
        String Fname = scanner.next();
        System.out.println("enter the file path: ");
        String Fpath = scanner.next();
        File filepath = new File(Fpath);
        if(filepath.exists()){
            String FHash = FileHashCalculator(Fpath);
            boolean check = CheckFile(FHash);
           if (check == true){
               System.out.println("FIle already present in database ");
               publicCloudLogin.loginHomepage(id);
           }
           else {
               fileToDb(Fname,Fpath,FHash,id);
           }
        }
        else {
            System.out.println("File not Exist");
//            scanner.next();
            publicCloudLogin.loginHomepage(id);
        }
    }

    public static boolean CheckFile(String FHash){
        String query = "Select * from filesOfPublicCloud";
        try(PreparedStatement fileexitsts = connection.prepareStatement(query)){
            ResultSet gethash = fileexitsts.executeQuery();
            while (gethash.next()){
                String hash = gethash.getString("FileHash");
                if (FHash.equals(hash)){
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

    public static void fileToDb(String FileName,String FilePath,String FileHash,int Uid){
        String query = "INSERT INTO filesofpubliccloud(FileName,FilePath,FileHash,Uid) values(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1,FileName);
            preparedStatement.setString(2,FilePath);
            preparedStatement.setString(3,FileHash);
            preparedStatement.setInt(4, Uid);

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows >0){
                System.out.println("Inserted Successfully ");
                publicCloudLogin.loginHomepage(Uid);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void userfiles(int id){
        String query = "SELECT * FROM filesofpubliccloud where Uid = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("+-FILE id-+--FILE name----+");
            while (resultSet.next()){
                int fid = resultSet.getInt("Fid");
                String fname = resultSet.getString("FileName");
                System.out.println("  "+ fid +"   "+ fname + "     ");
            }
       }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void allfiles(int id){
        String qry = "Select * from filesofpubliccloud";
        try(PreparedStatement preparedStatement = connection.prepareStatement(qry)){
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("+-FILE id-+--FILE name----+");
            while (resultSet.next()){
                int fid = resultSet.getInt("Fid");
                String fname = resultSet.getString("FileName");
                System.out.println("  "+ fid +"   "+ fname + "     ");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
