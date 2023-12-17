CREATE DATABASE veterinaria;
USE veterinaria;

CREATE TABLE Users (
    UserID INT PRIMARY KEY,
    PhoneNumber VARCHAR(20),
    ID INT,
    Name NVARCHAR(50),
    LastName NVARCHAR(50),
    Address NVARCHAR(100),
    Role NVARCHAR(50),
    Status NVARCHAR(50)
);

CREATE TABLE Clients (
    ClientID INT PRIMARY KEY,
    PhoneNumber VARCHAR(20),
    Name NVARCHAR(50),
    LastName NVARCHAR(50)
);

CREATE TABLE Pets (
    PetID INT PRIMARY KEY,
    PetName NVARCHAR(50),
    ClientID INT,
    Description NVARCHAR(255),
    PetPicture NVARCHAR(100),
    Ranking INT,
    FOREIGN KEY (ClientID) REFERENCES Clients(ClientID)
);

CREATE TABLE Reservations (
    ReservationID INT PRIMARY KEY AUTO_INCREMENT,
    PetID INT,
    EntryDate DATE,
    ExitDate DATE,
    Description NVARCHAR(255),
    FOREIGN KEY (PetID) REFERENCES Pets(PetID)
);

CREATE TABLE Appointments (
    AppointmentID INT PRIMARY KEY AUTO_INCREMENT,
    PetID INT,
    AppointmentDate DATE,
    AppointmentTime TIME,
    Description NVARCHAR(255),
    FOREIGN KEY (PetID) REFERENCES Pets(PetID)
);
