package picadoRLuisCarlos.BL.entities;

public class Pet {
    private String petName;
    private Client client;
    private int ID;
    private String description;
    private String petPicture;
    private int ranking;

    public Pet(String petName, Client client, int ID, String description, String petPicture, int ranking) {
        this.petName = petName;
        this.client = client;
        this.ID = ID;
        this.description = description;
        this.petPicture = petPicture;
        this.ranking = ranking;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Client getClient() {
        return client;
    }
    public int getClientID() {
        return client.getID();
    }


    public void setClient(Client client) {
        this.client = client;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPetPicture() {
        return petPicture;
    }

    public void setPetPicture(String petPicture) {
        this.petPicture = petPicture;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
