package picadoRLuisCarlos.BL.logic;

import picadoRLuisCarlos.BL.entities.Appointment;
import picadoRLuisCarlos.Memory.ClientDAO;

import java.sql.SQLException;
import java.util.List;

public class AppointmentGestor {
    private final ClientDAO dataAccess;

    public AppointmentGestor(ClientDAO userDAO) {
        this.dataAccess = userDAO;
    }

    public void newAppointment(Appointment appointment) throws SQLException {
        dataAccess.createAppointment(appointment);
    }

    public List<Appointment> getAppointments() {
        return dataAccess.getAppointments();
    }
}
