package picadoRLuisCarlos.TL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import picadoRLuisCarlos.BL.entities.*;
import picadoRLuisCarlos.Memory.*;
import picadoRLuisCarlos.UI.UI;
import picadoRLuisCarlos.BL.logic.*;
import java.sql.Time;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import picadoRLuisCarlos.Utils.Utils;

public class Controller {
    private UI interfaz;
    private static PetGestor petGestor;
    private static ClientGestor clientGestor;
    private static UserGestor userGestor;
    private static ReservationGestor reservationGestor;
    private static AppointmentGestor appointmentGestor;
    private static Connection connection;


    public Controller() throws SQLException {
        connection = DAO.getConnection();
        ClientDAO clientDAO = new ClientDAO(connection);
        UserDAO userDAO = new UserDAO(connection);
        interfaz = new UI();
        petGestor = new PetGestor(clientDAO);
        clientGestor = new ClientGestor(clientDAO);
        userGestor = new UserGestor(userDAO);
        reservationGestor = new ReservationGestor(clientDAO);
        appointmentGestor = new AppointmentGestor(clientDAO);
    }

    public void start() throws Exception {
        boolean continuar = true;

        while (continuar) {
            try {
                int option = 0;
                interfaz.menu1();
                option = interfaz.optionReader();

                switch (option) {
                    case 1:
                        boolean loggedIn = false;
                        interfaz.printText("Ingrese su cédula: ");
                        int userID = Integer.parseInt(interfaz.readText());
                        if (userGestor.checkUserExist(userID)) {
                            loggedIn = true;
                            interfaz.printText("Inicio de sesión exitoso.");
                            while (loggedIn) {
                                interfaz.menu();
                                option = interfaz.optionReader();

                                switch (option) {
                                    case 1:
                                        menu3(interfaz);
                                        break;

                                    case 2:
                                        menu2(interfaz);
                                        break;

                                    case 3:
                                        interfaz.printText("Hasta luego.");
                                        loggedIn = false;
                                        break;

                                    default:
                                        interfaz.printText("Opción no válida.");
                                        break;
                                }
                            }
                        } else {
                            interfaz.printText("Usuario no encontrado. Intente nuevamente.");
                        }
                        break;

                    case 2:
                        NewUser(interfaz);
                        break;

                    case 3:
                        interfaz.printText("Hasta luego.");
                        continuar = false;
                        break;

                    default:
                        interfaz.printText("Opción no válida.");
                        break;
                }
            } catch (Exception A) {
                interfaz.printText("Ha ocurrido un error. Intente nuevamente.");
            }
        }
    }



    private static void menu3(UI interfaz) {
        boolean showMenu = true;

        while (showMenu) {
            try {
                int registroOption = 0;
                interfaz.menu2();

                registroOption = interfaz.optionReader();

                switch (registroOption) {
                    case 1:
                        NewClient(interfaz);
                        break;

                    case 2:
                        newPet(interfaz);
                        break;

                    case 3:
                        NewAppointment(interfaz);
                        break;

                    case 4:
                        NewReservation(interfaz);
                        break;

                    case 5:
                        showMenu = false;
                        break;

                    default:
                        interfaz.printText("Opción no válida.");
                        break;
                }
            } catch (Exception B) {
                interfaz.printText("Ha ocurrido un error. Intente nuevamente.");
            }
        }
    }

    private static void NewUser(UI interfaz) {
        try {
            interfaz.printText("Ingrese su nombre:");
            String name = interfaz.readText();

            if (Utils.containsNumbers(name)) {
                interfaz.printText("Error: El nombre no debe contener números. Por favor, inténtelo de nuevo.");
                return;
            }

            interfaz.printText("Ingrese su apellido:");
            String lastName = interfaz.readText();

            if (Utils.containsNumbers(lastName)) {
                interfaz.printText("Error: El apellido no debe contener números. Por favor, inténtelo de nuevo.");
                return;
            }

            interfaz.printText("Ingrese su ID:");
            int ID = Integer.parseInt(interfaz.readText());

            if (userGestor.checkUserExist(ID)) {
                interfaz.printText("El cliente ya existe. Por favor, ingrese un ID único.");
            } else {
                interfaz.printText("Ingrese su número de teléfono:");
                String phoneNumber = interfaz.readText();

                interfaz.printText("Ingrese su dirección:");
                String address = interfaz.readText();

                interfaz.printText("¿Es usted un veterinario (V) o un secretario (S)?");
                String roleInput = interfaz.readText();
                String role = "";

                if (roleInput.equalsIgnoreCase("V")) {
                    role = "Veterinario";
                } else if (roleInput.equalsIgnoreCase("S")) {
                    role = "Secretario";
                } else {
                    interfaz.printText("Rol inválido. Por favor, ingrese 'V' para veterinario o 'S' para secretario.");
                    return;
                }

                userGestor.registeredUser(new User(phoneNumber, ID, name, lastName, address, role, ""));
                interfaz.printText("Usuario registrado con exito!");
            }
        } catch (Exception ex) {
            interfaz.printText("Se produjo un error. Por favor, inténtelo de nuevo.");
        }
    }

    private static void NewClient(UI interfaz) {
        try {
            interfaz.printText("Ingrese el nombre del cliente:");
            String name = interfaz.readText();

            if (Utils.containsNumbers(name)) {
                interfaz.printText("Error: El nombre no debe contener números. Por favor, inténtelo de nuevo.");
                return;
            }

            interfaz.printText("Ingrese el apellido del cliente:");
            String lastName = interfaz.readText();

            if (Utils.containsNumbers(lastName)) {
                interfaz.printText("Error: El apellido no debe contener números. Por favor, inténtelo de nuevo.");
                return;
            }

            interfaz.printText("Ingrese la cédula del cliente:");
            int ID = Integer.parseInt(interfaz.readText());

            boolean clientExists = clientGestor.checkClientExist(ID);

            if (clientExists) {
                interfaz.printText("El cliente ya existe. Ingrese una cédula única.");
            } else {
                interfaz.printText("Ingrese su número de teléfono:");
                String phoneNumber = interfaz.readText();

                clientGestor.registeredClient( new Client(phoneNumber, ID, name, lastName));
                interfaz.printText("Cliente registrado con éxito.");
            }
        } catch (Exception ex) {
            interfaz.printText("Ha ocurrido un error. Intente nuevamente.");
        }
    }



    private static void NewAppointment(UI interfaz) {
        try {
            interfaz.printText("Ingrese el ID de la mascota que asistirá a la cita:");
            int petID = Integer.parseInt(interfaz.readText());

            if (petGestor.checkPetExistence(petID)) {
                interfaz.printText("Ingrese la fecha de la cita (aaaa-MM-dd):");
                String appointmentDateString = interfaz.readText();
                Date appointmentDate = new SimpleDateFormat("yyyy-MM-dd").parse(appointmentDateString);

                interfaz.printText("Ingrese la hora de la cita en formato de 24 horas (por ejemplo, 14:30):");
                String appointmentTimeInput = interfaz.readText();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                java.util.Date date = sdf.parse(appointmentTimeInput);
                Time appointmentTime = new Time(date.getTime());

                interfaz.printText("Ingrese una descripción de la mascota:");
                String description = interfaz.readText();
                if (Utils.containsNumbers(description)) {
                    interfaz.printText("Error: La descripción no debe contener números. Por favor, inténtelo de nuevo.");
                    return;
                }

                appointmentGestor.newAppointment(new Appointment(petGestor.getPetByID(petID), appointmentDate, appointmentTime, description));
                interfaz.printText("Cita registrada exitosamente!");
            } else {
                interfaz.printText("El nombre de la mascota no existe. No se puede registrar la cita.");
            }
        } catch (Exception ex) {
            interfaz.printText("Se produjo un error. Por favor, inténtelo de nuevo.");
        }
    }

    private static void NewReservation(UI interfaz) {
        try {
            interfaz.printText("Ingrese el id de la mascota para la reserva:");
            int petID = Integer.parseInt(interfaz.readText());
            if (petGestor.checkPetExistence(petID)) {
                interfaz.printText("Ingrese la fecha de entrada (yyyy-MM-dd):");
                String entryDateString = interfaz.readText();
                Date entryDate = new SimpleDateFormat("yyyy-MM-dd").parse(entryDateString);

                interfaz.printText("Ingrese la fecha de salida (yyyy-MM-dd):");
                String exitDateString = interfaz.readText();
                Date exitDate = new SimpleDateFormat("yyyy-MM-dd").parse(exitDateString);

                interfaz.printText("Ingrese una descripción de la reserva:");
                String description = interfaz.readText();

                if (Utils.containsNumbers(description)) {
                    interfaz.printText("Error: La descripción no debe contener números. Por favor, inténtelo de nuevo.");
                    return;
                }
                Pet selectedPet = petGestor.getPetByID(petID);
                reservationGestor.makeReservation(new Reservation(selectedPet, entryDate, exitDate, description));
                interfaz.printText("Reserva registrada con éxito.");
            } else {
                interfaz.printText("El nombre de la mascota no existe. No se puede registrar la reserva.");
            }
        } catch (Exception ex) {
            interfaz.printText("Ha ocurrido un error. Intente nuevamente.");
        }
    }

    public static void showPets() {
        SwingUtilities.invokeLater(() -> {
            JFrame guiFrame = new JFrame("Lista de Mascotas");
            guiFrame.setSize(600, 500);
            guiFrame.setLocationRelativeTo(null);

            List<Pet> pets = petGestor.getPets();

            if (pets.isEmpty()) {
                System.out.println("No hay mascotas registradas");
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "No hay mascotas registradas.", "Lista de Mascotas", JOptionPane.INFORMATION_MESSAGE);
                });
            } else {
                JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

                for (Pet pet : pets) {
                    JPanel petPanel = new JPanel(new GridLayout(7, 2, 5, 5));
                    addAttributeToPanel(petPanel, "Nombre de la mascota:", pet.getPetName());
                    addAttributeToPanel(petPanel, "Nombre del dueño:", pet.getClient().getName() + " " + pet.getClient().getLastName());
                    addAttributeToPanel(petPanel, "ID de la mascota:", Integer.toString(pet.getID()));
                    addAttributeToPanel(petPanel, "Número de teléfono:", pet.getClient().getPhoneNumber());
                    addAttributeToPanel(petPanel, "Descripción de la mascota:", pet.getDescription());
                    addAttributeToPanel(petPanel, "Foto de la mascota:", pet.getPetPicture());
                    addAttributeToPanel(petPanel, "Ranking de la mascota:", Integer.toString(pet.getRanking()));

                    mainPanel.add(petPanel);
                }

                JScrollPane scrollPane = new JScrollPane(mainPanel);
                guiFrame.add(scrollPane);
            }
            guiFrame.setVisible(true);
        });
    }
    private static void addAttributeToPanel(JPanel panel, String label, String value) {
        JLabel labelComponent = new JLabel(label);
        JTextArea valueComponent = new JTextArea(value);
        valueComponent.setEditable(false);
        valueComponent.setLineWrap(true);
        valueComponent.setWrapStyleWord(true);

        panel.add(labelComponent);
        panel.add(valueComponent);
    }
    public static void showReservations(UI interfaz) {
        List<Reservation> reservations = reservationGestor.showReservations();
        int count = 0;

        for (Reservation reservation : reservations) {
            count++;
            interfaz.printText("Reserva " + count + ":");
            interfaz.printText("Detalles de la mascota");
            interfaz.printText("Nombre de la mascota: " + reservation.getNewPet().getPetName());
            interfaz.printText("Nombre del dueño: " + reservation.getNewPet().getClient().getName() + " " + reservation.getNewPet().getClient().getLastName());
            interfaz.printText("ID del dueño: " + reservation.getNewPet().getClient().getID());
            interfaz.printText("Número de teléfono: " + reservation.getNewPet().getClient().getPhoneNumber());
            interfaz.printText("Descripción de la mascota: " + reservation.getNewPet().getDescription());
            interfaz.printText("Foto de la mascota: " + reservation.getNewPet().getPetPicture());
            interfaz.printText("Ranking de la mascota: " + reservation.getNewPet().getRanking());
            interfaz.printText("Fecha de entrada: " + reservation.getEntryDate());
            interfaz.printText("Fecha de salida: " + reservation.getExitDate());
            interfaz.printText("Descripción de la reserva: " + reservation.getDescription());
            interfaz.printText("");
        }

        if (count == 0) {
            interfaz.printText("No hay reservas registradas.");
        }
    }
    public static void showUsers(UI interfaz) {
        List<User> users = userGestor.showUsers();
        int count = 0;

        if (users.isEmpty()) {
            interfaz.printText("No hay usuarios registrados.");
        } else {
            for (User user : users) {
                count++;
                interfaz.printText("Usuario " + count + ":");
                interfaz.printText("Nombre: " + user.getName());
                interfaz.printText("Apellido: " + user.getLastName());
                interfaz.printText("Cédula: " + user.getID());
                interfaz.printText("Número de teléfono: " + user.getPhoneNumber());
                interfaz.printText("Dirección: " + user.getAddress());
                interfaz.printText("Rol: " + user.getRole());
                interfaz.printText("Estado: " + user.getStatus());
                interfaz.printText("");
            }
        }
    }
    public static void showClients(UI interfaz) {
        List<Client> clients = clientGestor.getClients();
        int count = 0;

        for (Client client : clients) {
            count++;
            interfaz.printText("Cliente " + count + ":");
            interfaz.printText("Nombre: " + client.getName());
            interfaz.printText("Apellido: " + client.getLastName());
            interfaz.printText("Cédula: " + client.getID());
            interfaz.printText("Número de teléfono: " + client.getPhoneNumber());
            interfaz.printText("");
        }

        if (count == 0) {
            interfaz.printText("No hay clientes registrados.");
        }
    }
    public static void showAppointments(UI interfaz) {
        List<Appointment> appointments = appointmentGestor.getAppointments();
        int count = 0;

        for (Appointment appointment : appointments) {
            count++;
            interfaz.printText("Cita " + count + ":");
            interfaz.printText("Nombre de la mascota: " + appointment.getPet().getPetName());
            interfaz.printText("Fecha de la cita: " + appointment.getAppointmentDate());
            interfaz.printText("Hora de la cita: " + appointment.getAppointmentTime());
            interfaz.printText("Descripción de la mascota: " + appointment.getDescription());
            interfaz.printText("");
        }

        if (count == 0) {
            interfaz.printText("No hay citas registradas.");
        }
    }

    private static void newPet(UI interfaz) {
        SwingUtilities.invokeLater(() -> {
            JFrame guiFrame = new JFrame("Registro de Mascota");
            guiFrame.setSize(500, 400);
            guiFrame.setLocationRelativeTo(null);

            JLabel nameLabel = new JLabel("Nombre de la mascota:");
            JTextField nameField = new JTextField();

            JLabel ownerIDLabel = new JLabel("Cédula del dueño:");
            JTextField ownerIDField = new JTextField();

            JLabel descriptionLabel = new JLabel("Descripción:");
            JTextField descriptionField = new JTextField();

            JLabel pictureLabel = new JLabel("Foto:");
            JTextField pictureField = new JTextField();

            JLabel rankingLabel = new JLabel("Ranking:");
            JTextField rankingField = new JTextField();

            JButton registerButton = new JButton("Registrar Mascota");

            guiFrame.setLayout(new java.awt.GridLayout(7, 2));

            guiFrame.add(nameLabel);
            guiFrame.add(nameField);
            guiFrame.add(ownerIDLabel);
            guiFrame.add(ownerIDField);
            guiFrame.add(descriptionLabel);
            guiFrame.add(descriptionField);
            guiFrame.add(pictureLabel);
            guiFrame.add(pictureField);
            guiFrame.add(rankingLabel);
            guiFrame.add(rankingField);
            guiFrame.add(new JLabel());
            guiFrame.add(registerButton);

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String petName = nameField.getText();
                         {
                            if (Utils.containsNumbers(petName)) {
                                JOptionPane.showMessageDialog(guiFrame, "Error: El nombre de la mascota no debe contener números. Por favor, inténtelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            int ownerID = Integer.parseInt(ownerIDField.getText());
                            boolean clientExists = clientGestor.checkClientExist(ownerID);

                            if (!clientExists) {
                                JOptionPane.showMessageDialog(guiFrame, "El cliente no existe. Registre al cliente primero antes de registrar la mascota.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                Client selectedClient = clientGestor.getClientByID(ownerID);
                                int petID = petGestor.generateUniqueID();

                                String description = descriptionField.getText();
                                String petPicture = pictureField.getText();
                                int ranking = Integer.parseInt(rankingField.getText());

                                if (ranking < 6) {
                                    petGestor.registerPet(new Pet(petName, selectedClient, petID, description, petPicture, ranking));
                                    JOptionPane.showMessageDialog(guiFrame, "Mascota registrada. El ID de " + petName + " es: " + petID, "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                                    guiFrame.dispose();
                                    interfaz.menu2();
                                } else {
                                    JOptionPane.showMessageDialog(guiFrame, "Error: el ranking no puede ser mayor que 5", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(guiFrame, "Error: Ingrese un número válido para la cédula del dueño y el ranking.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(guiFrame, "Ha ocurrido un error. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            guiFrame.setVisible(true);
        });
    }

    private static void menu2(UI interfaz) {
        boolean showMenu = true;
        while (showMenu) {
            try {
                interfaz.menu3();

                int registrosOption = Integer.parseInt(interfaz.readText());

                switch (registrosOption) {
                    case 1:
                        showAppointments(interfaz);
                        break;

                    case 2:
                        showReservations(interfaz);
                        break;

                    case 3:
                        showClients(interfaz);
                        break;

                    case 4:
                        showPets();
                        break;

                    case 5:
                        showUsers(interfaz);
                        break;

                    case 6:
                        showMenu = false;
                        break;

                    default:
                        interfaz.printText("Opción no válida.");
                        break;
                }
            } catch (Exception F) {
                interfaz.printText("Ha ocurrido un error. Intente nuevamente.");
            }
        }
    }
}