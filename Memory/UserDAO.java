package picadoRLuisCarlos.Memory;

import picadoRLuisCarlos.BL.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }


    public String addUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (UserID, PhoneNumber, ID, Name, LastName, Address, Role, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getID());
            statement.setString(2, user.getPhoneNumber());
            statement.setInt(3, user.getID());
            statement.setString(4, user.getName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getRole());
            statement.setString(8, user.getStatus());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "El usuario " + user.getName() + " fue registrado exitosamente!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return "No se pudo registrar el usuario.";
    }
    public List<User> getUsersFromDatabase() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("PhoneNumber"),
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Address"),
                        resultSet.getString("Role"),
                        resultSet.getString("Status")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public boolean checkUserExistence(int userID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Users WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        }
    }

}
