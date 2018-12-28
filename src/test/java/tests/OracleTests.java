package tests;

import data.OracleDBHelper;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: Nhi Dinh
 * Date: 27/12/2018
 */
public class OracleTests {
    String dbUrl = "jdbc:oracle:thin:@localhost:49161:xe";
    String dbUsername = "system";
    String dbPassword = "oracle";
    String query1 = "CREATE table \"COUNTRY\" (\n" +
            "    \"NAME\"       VARCHAR2(2000),\n" +
            "    \"ZIP\"        NUMBER\n" +
            ")";

    String query2 = "insert into country values ('NY', '3000')";
    String query3 = "select * from country";
    String query = "SELECT * FROM USER_TABLES";
    @Test
    public void testQueryOracle(){
        Connection con = OracleDBHelper.connectToDB(dbUrl, dbUsername, dbPassword);
        ResultSet result = OracleDBHelper.sendQueryToDB(query);

        try {
            while (result.next()){
                String countryName =result.getString(1);
                String countryZip = result.getString(2);
                System.out.println("Table Name: " + countryName);
                System.out.println("Country Zip: " + countryZip);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
