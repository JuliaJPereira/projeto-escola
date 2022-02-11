package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConection {
    private static String url = "jdbc:mysql://localhost:3306/escola";
    private static String user = "root";
    private static String password = "root";

    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}
