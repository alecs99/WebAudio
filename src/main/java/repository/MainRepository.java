package repository;

import helpers.AES;
import model.File;
import model.User;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MainRepository {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sys?serverTimezone=UTC";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "12345678";
    private static final String GET_USER_FILES = "SELECT * FROM AUDIO WHERE id_file IN " +
                                                 "(SELECT id_file FROM LISTEN WHERE id_user = ?)";
    private static final String GET_USERS = "SELECT * FROM USERS";
    private static final String ADD_USER = "INSERT INTO USERS(user_name, password, first_name, last_name, email, admin)" +
                                            "VALUES(?, ?, ?, ?, ?, ?)";

    public static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
    }

    private static String getKey() throws IOException {
        String filePath = "/Users/alc/IdeaProjects/WebAudio/src/main/java/repository/key.txt";
        String key = null;
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null) {
            key = line;
        }
        return key;
    }

    public List<File> getUserFiles(int userId) throws SQLException{
        List<File> files = new ArrayList<>();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_USER_FILES);
        preparedStatement.setInt(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            File file = new File(rs.getInt(1), rs.getString(2), rs.getString(3),
                                 rs.getString(4), rs.getString(5));
            files.add(file);
        }
        return files;
    }

    public List<User> getUsers() throws SQLException{
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_USERS);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            int id = rs.getInt(1);
            String pass = rs.getString(3);
            String key = null;
            try {
                key = getKey();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String decryptedPass = AES.decrypt(pass, key);
            List<File> userFiles = getUserFiles(id);
//            List<File> userFiles = new ArrayList<>();
            User user = new User(id, rs.getString(2), decryptedPass,
                                 rs.getString(4), rs.getString(5), rs.getString(6),
                                 rs.getBoolean(7), userFiles);
            users.add(user);
        }
        return users;
    }

    public boolean addUser(User user) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(ADD_USER);
        preparedStatement.setString(1,user.getUsername());
        String key = null;
        try {
            key = getKey();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String cryptedPass = AES.encrypt(user.getPassword(), key);
        preparedStatement.setString(2, cryptedPass);
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setBoolean(6, user.getAdmin());

        return preparedStatement.executeUpdate() > 0;
    }

}
