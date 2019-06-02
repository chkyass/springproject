package chkyass.testDB;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class TestDBConnection {

    @Test
    public void testDBConnection() {
        String username = "springuser";
        String password = System.getenv("MYSQL_PASS");
        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?characterEncoding=latin1&useSSL=false&serverTimezone=UTC";

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.close();
        }
        catch (SQLException e) {
            System.out.printf(e.toString());
            assertTrue(false);
        }
    }
}