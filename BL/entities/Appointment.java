package picadoRLuisCarlos.BL.entities;

import java.sql.Time;
import java.util.Date;

public class Appointment {
    private Pet pet;
    private Date appointmentDate;
    private Time appointmentTime;
    private String description;

    public Appointment(Pet pet, Date appointmentDate, Time appointmentTime, String description) {
        this.pet = pet;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.description = description;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
