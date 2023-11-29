package picadoRLuisCarlos.BL.entities;

public class User extends Client {

    private String address;
    private String role;
    private String status;

    public User(String phoneNumber, int ID, String name, String lastName, String address, String role, String status) {
        super(phoneNumber, ID, name, lastName);
        this.address = address;
        this.role = role;
        this.status = "Activo";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
