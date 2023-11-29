package picadoRLuisCarlos.BL.logic;
import picadoRLuisCarlos.BL.entities.Client;
import picadoRLuisCarlos.Memory.ClientDAO;

import java.sql.SQLException;
import java.util.List;

public class ClientGestor {
    private final ClientDAO dataAccess;

    public ClientGestor(ClientDAO dataAccess) {
        this.dataAccess = dataAccess;
    }

    public void registeredClient(Client client) throws SQLException {
        dataAccess.addClient(client);
    }

    public boolean checkClientExist(int ID) throws SQLException {
        return dataAccess.checkClientExistence(ID);
    }

    public List<Client> getClients() {
        return dataAccess.getClientsFromDatabase();
    }

    public Client getClientByID(int clientID) throws SQLException {
        return dataAccess.getClientById(clientID);
    }
}