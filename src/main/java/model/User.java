package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int idUser;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    private Boolean admin;
    private List<File> audioFiles = new ArrayList<>();

    public User(int idUser, String username, String password, String firstName,
                String lastName, String email, Boolean admin, List<File> audioFiles) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.admin = admin;
        this.audioFiles = audioFiles;
    }

    //Constructor pentru adaugarea unui nou utilizator
    public User(String username, String password, String firstName, String lastName, String email, Boolean admin) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.admin = admin;
    }

    /*Metode de tip getter si setter necesare in cadrul aplicatiei*/
    public int getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public List<File> getAudioFiles() {
        return audioFiles;
    }

    @Override
    public String toString() {
        return "User has the id " + idUser + ", username " + username + ", first name " + firstName + ", last name " +
                lastName + ", email " + email + ", admin:" + admin;
    }
}
