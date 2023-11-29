package picadoRLuisCarlos.BL.logic;
import picadoRLuisCarlos.BL.entities.Reservation;
import picadoRLuisCarlos.Memory.ClientDAO;

import java.sql.SQLException;
import java.util.List;

public class ReservationGestor {
    private final ClientDAO dataAccess;

    public ReservationGestor(ClientDAO dataAccess) {
        this.dataAccess = dataAccess;
    }

    public void makeReservation(Reservation reservation) throws SQLException {
        dataAccess.addReservation(reservation);
    }

    public List<Reservation> showReservations() {
        return dataAccess.getReservationsFromDatabase();
    }
}