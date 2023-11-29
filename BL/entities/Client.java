package picadoRLuisCarlos.BL.entities;

public class Client {
    private String phoneNumber;
    private int ID;
    private String name;
    private String lastName;

    public Client(String phoneNumber, int ID, String name, String lastName) {
        this.phoneNumber = phoneNumber;
        this.ID = ID;
        this.name = name;
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
