package picadoRLuisCarlos.BL.logic;

import picadoRLuisCarlos.BL.entities.Pet;
import picadoRLuisCarlos.Memory.ClientDAO;
import picadoRLuisCarlos.Memory.UserDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class PetGestor {

    public int generateUniqueID() throws SQLException {
        Random random = new Random();
        int newID;
        do {
            newID = 1000000 + random.nextInt(9000000);
        } while (checkPetExistence(newID));
        return newID;
    }
    public Pet getPetByID(int petID) {
        return dataAccess.getPetById(petID);
    }
    public boolean checkPetExistence(int petID) throws SQLException {
        return dataAccess.checkPetExistence(petID);
    }
    private final ClientDAO dataAccess;

    public PetGestor(ClientDAO dataAccess) {
        this.dataAccess = dataAccess;
    }

    public void registerPet(Pet pet) throws SQLException {
        int uniqueID = generateUniqueID();
        pet.setID(uniqueID);
        try {
            dataAccess.addPet(pet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pet> getPets() {
        return dataAccess.getPetsFromDatabase();
    }
}