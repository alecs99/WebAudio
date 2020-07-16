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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<File> getAudioFiles() {
        return audioFiles;
    }

    public void setAudioFiles(List<File> audioFiles) {
        this.audioFiles = audioFiles;
    }

    @Override
    public String toString() {
        return "User have the id " + idUser + ", username " + username + ", firstName " + firstName + ", lastName " +
                lastName + ", email " + email + ", is/ is not" + admin;
    }
}
