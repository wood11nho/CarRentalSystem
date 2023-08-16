package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBObject {
    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;

    private Connection connection;

    public DBObject(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream("config.properties");
            properties.load(inputStream);
            DB_URL = properties.getProperty("db.url");
            DB_USERNAME = properties.getProperty("db.username");
            DB_PASSWORD = properties.getProperty("db.password");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connected to database successfully!");
        } catch(ClassNotFoundException | SQLException | IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection(){
        try{
            connection.close();
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
