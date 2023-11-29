package picadoRLuisCarlos.Memory;

import picadoRLuisCarlos.BL.entities.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        String url = "jdbc:mysql://localhost:3306/veterinaria?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "1q2w3e4r5t.";

        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}