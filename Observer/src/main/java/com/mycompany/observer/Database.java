package com.mycompany.observer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Mees Buschman
 * Creates a connection to the Database.
 */
public class Database {
    //Holds the connection object.
    private static Connection connection;

    //Holds the username.
    private static final String DEFAULT_USER = "root";

    //Holds the password.
    private static final String DEFAULT_PASSWORD = "root";


    //Get a connection to the database.
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String url = String.format(
                    "jdbc:mysql://%s:%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "127.0.0.01", //Change to your database url
                    "3308",//Change to your port
                    "test" //Change to your database name
            );

            connection = DriverManager.getConnection(
                    url,
                    DEFAULT_USER,
                    DEFAULT_PASSWORD
            );
            
            System.out.println("Connected.");
        }
        return connection;

    }

    //Stop the connection.
    public static void Stop() throws SQLException {
        if (connection == null) {
            System.out.println("error no connection to shutdown");
            return;
        }
        connection.close();
        connection = null;
    }
    
}
