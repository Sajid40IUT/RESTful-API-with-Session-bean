package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection = null;
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/PCget";
            String username = "root";
            String password = "Kronos7988";

            connection = DriverManager.getConnection(url, username, password);
            System.out.print("connected");
        }
        return connection;
    }
}
