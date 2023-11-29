package picadoRLuisCarlos.BL.entities;

import java.util.Date;
public class Reservation {
    private Pet newPet;
    private Date entryDate;
    private Date exitDate;
    private String description;

    public Reservation(Pet newPet, Date entryDate, Date exitDate, String description) {
        this.newPet = newPet;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.description = description;
    }

    public Pet getNewPet() {
        return newPet;
    }

    public void setNewPet(Pet newPet) {
        this.newPet = newPet;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}