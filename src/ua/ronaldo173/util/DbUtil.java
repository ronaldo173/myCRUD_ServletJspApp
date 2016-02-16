package ua.ronaldo173.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by Santer on 16.02.2016.
 */
public class DbUtil {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                Properties properties = new Properties();
                InputStream inputStream = new FileInputStream("db.properties");
                properties.load(inputStream);

                String driver = properties.getProperty("driver");
                String url = properties.getProperty("url");
                String login = properties.getProperty("user");
                String password = properties.getProperty("password");

                System.out.println(driver + "\n" + url + "\n" +
                        login + "\n" + password);

                Class.forName(driver);
                connection = DriverManager.getConnection(url, login, password);
            } catch (Exception e) {
                System.out.println("no file prop");
                e.printStackTrace();
            }

            return connection;
        }
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3};
        System.out.println(arr.length);
        System.out.println(arr[arr.length-1]);
    }
}
