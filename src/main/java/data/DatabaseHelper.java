package data;

import utilities.logger.Log;

import java.sql.*;

/**
 * User: Nhi Dinh
 * Date: 10/12/2018
 */
public class DatabaseHelper {
    private static Connection con;

    public static Connection connectToDB(String dbUrl, String dbUsername, String dbPassword) {

        try {
            Log.info("Checking Driver loader...");
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            System.out.println("Driver Loaded");

            Log.info("Getting DB Connection");
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("UNABLE TO CONNECT TO DB");
            System.exit(1);
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ORACLE JDBC CLASS NOT FOUND");
            System.exit(4);
            return null;

        }
        catch (IllegalAccessException ex) {
            System.out.println("Error: access problem while loading!");
            System.exit(2);
            return null;

        } catch (InstantiationException ex) {
            System.out.println("Error: unable to instantiate driver!");
            System.exit(3);
            return null;

        }
    }

    public static ResultSet sendQueryToDB(String query) {
        try {
            Statement stmt = con.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
