package picadoRLuisCarlos.Memory;
import picadoRLuisCarlos.BL.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private final Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    public String addClient(Client client) throws SQLException {
        String sql = "INSERT INTO Clients (ClientID, PhoneNumber, Name, LastName) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, client.getID());
            statement.setString(2, client.getPhoneNumber());
            statement.setString(3, client.getName());
            statement.setString(4, client.getLastName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "El cliente " + client.getName() + " fue registrado exitosamente!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return "No se pudo registrar el cliente.";
    }

    public List<Client> getClientsFromDatabase() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Clients";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getString("PhoneNumber"),
                        resultSet.getInt("ClientID"),
                        resultSet.getString("Name"),
                        resultSet.getString("LastName")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public boolean checkClientExistence(int clientID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Clients WHERE ClientID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        }
    }
    public Client getClientById(int clientId) {
        String sql = "SELECT * FROM Clients WHERE ClientID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Client(
                        resultSet.getString("PhoneNumber"),
                        resultSet.getInt("ClientID"),
                        resultSet.getString("Name"),
                        resultSet.getString("LastName")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public String addPet(Pet pet) throws SQLException {
        String sql = "INSERT INTO Pets (PetID, PetName, ClientID, Description, PetPicture, Ranking) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pet.getID());
            statement.setString(2, pet.getPetName());
            statement.setInt(3, pet.getClientID());
            statement.setString(4, pet.getDescription());
            statement.setString(5, pet.getPetPicture());
            statement.setInt(6, pet.getRanking());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "La mascota " + pet.getPetName() + " fue registrada exitosamente!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return "No se pudo registrar la mascota.";
    }

    public List<Pet> getPetsFromDatabase() {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM Pets";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int clientID = resultSet.getInt("ClientID");
                Client client = getClientById(clientID);

                Pet pet = new Pet(
                        resultSet.getString("PetName"),
                        client,
                        resultSet.getInt("PetID"),
                        resultSet.getString("Description"),
                        resultSet.getString("PetPicture"),
                        resultSet.getInt("Ranking")
                );

                pets.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }


    public boolean checkPetExistence(int petID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Pets WHERE PetID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, petID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        }
    }



    public String addReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO Reservations (PetID, EntryDate, ExitDate, Description) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getNewPet().getID());
            statement.setDate(2, new java.sql.Date(reservation.getEntryDate().getTime()));
            statement.setDate(3, new java.sql.Date(reservation.getExitDate().getTime()));
            statement.setString(4, reservation.getDescription());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "La reservación con fue registrada exitosamente!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return "No se pudo registrar la reservación.";
    }

    public List<Reservation> getReservationsFromDatabase() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservations";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int petID = resultSet.getInt("PetID");
                Pet pet = getPetById(petID);

                Reservation reservation = new Reservation(
                        pet,
                        resultSet.getDate("EntryDate"),
                        resultSet.getDate("ExitDate"),
                        resultSet.getString("Description")
                );

                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
    public Pet getPetById(int petId) {
        String sql = "SELECT * FROM Pets WHERE PetID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, petId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Client client = getClientById(resultSet.getInt("ClientID"));
                return new Pet(
                        resultSet.getString("PetName"),
                        client,
                        resultSet.getInt("PetID"),
                        resultSet.getString("Description"),
                        resultSet.getString("PetPicture"),
                        resultSet.getInt("Ranking")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String createAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO Appointments (PetID, AppointmentDate, AppointmentTime, Description) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointment.getPet().getID());
            statement.setDate(2, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            statement.setTime(3, new java.sql.Time(appointment.getAppointmentTime().getTime()));
            statement.setString(4, appointment.getDescription());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "La cita fue registrada exitosamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return "No se pudo registrar la cita.";
    }

    public List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointments";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int petID = resultSet.getInt("PetID");
                Pet pet = getPetById(petID);

                Appointment appointment = new Appointment(
                        pet,
                        resultSet.getDate("AppointmentDate"),
                        resultSet.getTime("AppointmentTime"),
                        resultSet.getString("Description")
                );

                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
}
