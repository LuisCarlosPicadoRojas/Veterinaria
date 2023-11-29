package picadoRLuisCarlos.BL.logic;

import picadoRLuisCarlos.BL.entities.User;
import picadoRLuisCarlos.Memory.ClientDAO;
import picadoRLuisCarlos.Memory.UserDAO;

import java.util.List;
import java.sql.SQLException;

public class UserGestor {
    private final UserDAO dataAccess;

    public UserGestor(UserDAO clientDAO) {
        this.dataAccess = clientDAO;
    }

    public String registeredUser(User user) {
        try {
            return dataAccess.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al registrar el usuario: " + e.getMessage();
        }
    }

    public boolean checkUserExist(int ID) {
        try {
            return dataAccess.checkUserExistence(ID);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> showUsers() {
        return dataAccess.getUsersFromDatabase();
    }
}
