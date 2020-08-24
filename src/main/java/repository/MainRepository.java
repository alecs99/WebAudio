package repository;

import helpers.AES;
import model.File;
import model.Note;
import model.User;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MainRepository {
    /*Credentialele pentru stabilirea conexiunii cu baza de date */
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sys?serverTimezone=UTC";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "12345678";
    /*Interogarile trimise catre baza de date */
    private static final String GET_USER_FILES = "SELECT * FROM AUDIO WHERE id_file IN " +
                                                 "(SELECT id_file FROM LISTEN WHERE id_user = ?)";
    private static final String GET_ALL_FILES = "SELECT * FROM AUDIO";
    private static final String GET_USERS = "SELECT * FROM USERS";
    private static final String ADD_USER = "INSERT INTO USERS(user_name, password, first_name, last_name, email, admin)" +
                                            "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM USERS WHERE id_user = ?";
    private static final String ADD_FILE = "INSERT INTO AUDIO(name, author, length, file_path) VALUES(?, ?, ?, ?)";
    private static final String REMOVE_FILE = "DELETE FROM AUDIO WHERE id_file = ?";
    private static final String GET_FILE_BY_ID = "SELECT * FROM AUDIO WHERE id_file = ?";
    private static final String GET_FILE_ID = "SELECT id_file FROM AUDIO WHERE file_path = ?";
    private static final String GET_FILE_PATH = "SELECT file_path FROM AUDIO WHERE id_file = ?";
    private static final String GET_USER_ID = "SELECT id_user FROM USERS WHERE user_name = ?";
    private static final String GET_NEXT_FILE_ID = "SELECT AUTO_INCREMENT FROM information_schema.TABLES " +
                                                    "WHERE TABLE_SCHEMA = 'sys' AND TABLE_NAME = 'audio'";
    private static final String GET_NEXT_USER_ID = "SELECT AUTO_INCREMENT FROM information_schema.TABLES " +
                                                    "WHERE TABLE_SCHEMA = 'sys' AND TABLE_NAME = 'users'";
    private static final String GET_USER_NOTES = "SELECT id_file_notes, note, start_time, end_time FROM NOTES " +
                                                    "WHERE id_user_notes = ?";
    private static final String ADD_TO_RELATIONAL = "INSERT INTO LISTEN(id_user, id_file) VALUES(?, ?)";
    private static final String ADD_NOTE = "INSERT INTO NOTES(id_user_notes, id_file_notes, note, start_time, end_time)" +
                                            "VALUES(?, ?, ?, ?, ?)";
    private static final String DELETE_FROM_RELATIONAL = "DELETE FROM LISTEN WHERE id_user = ? AND id_file = ?";
    private static final String DELETE_FROM_NOTES = "DELETE FROM NOTES WHERE id_user_notes = ? AND id_file_notes = ?";
    private static final String GET_ADMINS = "SELECT id_user FROM USERS WHERE admin = 1";
    private static final String UPDATE_ADMIN_STATE = "UPDATE USERS SET admin = ? WHERE id_user = ?";
    private static final String UPDATE_USERNAME = "UPDATE USERS SET user_name = ? WHERE id_user = ?";
    private static final String UPDATE_PASSWORD = "UPDATE USERS SET password = ? WHERE id_user = ?";

    /*Stabilirea conexiunii cu baza de date */
    public static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
    }
    /*Preluarea cheii necesare pentru parole*/
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
    /*Preluarea tuturor fisierelor unui utilizator din BD*/
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
    /*Preluarea tuturor fisierelor din BD*/
    public List<File> getAllFiles() throws SQLException{
        List<File> files = new ArrayList<>();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_ALL_FILES);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            File file = new File(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5));
            files.add(file);
        }
        return files;
    }
    /*Preluarea tuturor utilizatorilor din BD*/
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
            User user = new User(id, rs.getString(2), decryptedPass,
                                 rs.getString(4), rs.getString(5), rs.getString(6),
                                 rs.getBoolean(7), userFiles);
            users.add(user);
        }
        return users;
    }
    /*Adaugarea unui nou utilizator in  BD*/
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
    /*Stergerea unui utilizator din  BD*/
    public boolean deleteUser(int idUser) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_USER);
        preparedStatement.setInt(1, idUser);
        return preparedStatement.executeUpdate() > 0;
    }

    /*Adaugarea unui fisier auduio in tabelul relational din BD. Fara aceasta adaugare nu este posibila atribuirea
    * unui fisier utilizatorului */

    public boolean addToRelational(int idUser, int idFile) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(ADD_TO_RELATIONAL);
        preparedStatement.setInt(1, idUser);
        preparedStatement.setInt(2, idFile);
        return preparedStatement.executeUpdate() > 0;
    }
    /* Stergerea unui fisier din tabelul relational */
    public boolean deleteFromRelational(int idUser, int idFile) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_FROM_RELATIONAL);
        preparedStatement.setInt(1, idUser);
        preparedStatement.setInt(2, idFile);
        return preparedStatement.executeUpdate() > 0;
    }
    /*Stergerea unei note din  BD*/
    public boolean deleteFromNotes(int idUser, int idFile) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_FROM_NOTES);
        preparedStatement.setInt(1, idUser);
        preparedStatement.setInt(2, idFile);
        return preparedStatement.executeUpdate() > 0;
    }
    /*Preluarea tuturor adminilor din BD*/
    public List<Integer> getAdmins() throws  SQLException{
        List<Integer> adminsId = new ArrayList<>();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_ADMINS);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Integer adminId = rs.getInt(1);
            adminsId.add(adminId);
        }
        return adminsId;
    }
    /*Preluarea urmatorului fileID din BD. Aceasta metoda este necesara in momentul in care vrem sa adaugam
    * un nou fisier audio in BD*/
    public int getNextFileId() throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_NEXT_FILE_ID);
        ResultSet rs = preparedStatement.executeQuery();
        int nextId = 0;
        while(rs.next()){
            nextId = rs.getInt(1);
        }
        return nextId;
    }

    /*Preluarea id-ului unui fisier folosindu-ne de calea acestuia. */
    public int getFileId(String filePath) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_FILE_ID);
        preparedStatement.setString(1, filePath);
        ResultSet rs = preparedStatement.executeQuery();
        int fileId = 0;
        while(rs.next()){
            fileId = rs.getInt(1);
        }
        return fileId;

    }
    /*Preluarea calea unui fisier folosindu-ne de id-ul acestuia. */
    public String getFilePath(int fileId) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_FILE_PATH);
        preparedStatement.setInt(1, fileId);
        ResultSet rs = preparedStatement.executeQuery();
        String filePath = null;
        while(rs.next()){
            filePath = rs.getString(1);
        }
        return filePath;

    }
    /*Adaugarea unui nou fisier audio in tabelul AUDIO din BD */
    public boolean addToAudio(File file) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(ADD_FILE);
        preparedStatement.setString(1, file.getFileName());
        preparedStatement.setString(2, file.getAuthor());
        preparedStatement.setString(3, file.getLength());
        preparedStatement.setString(4, file.getFilePath());
        return preparedStatement.executeUpdate() > 0;
    }
    public void addFile(User user, File file) throws SQLException{
        //Adaug fisierul in tabelul AUDIO
        addToAudio(file);
        int fileId = getFileId(file.getFilePath());
        /*
        Adaug id-ul userului care l-a adaugat si idul fisierului audio pentru a putea avea acces la el pe viitor.Daca
        Utilizatorul nu este admin adaugam separat, daca este admin vom adauga fisierul in momentul in care il adaugam
        pentru toti adminii.
        */
        if(!user.getAdmin()){
            addToRelational(user.getIdUser(), fileId);
        }
        List<Integer> admins = getAdmins();
        for(Integer admin:admins){
            addToRelational(admin, fileId);
        }
    }
    /*Stergerea unui fisier audio */
    public boolean deleteFile(String fileId) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(REMOVE_FILE);
        preparedStatement.setInt(1, Integer.parseInt(fileId));
        return preparedStatement.executeUpdate() > 0;
    }

    /*Preluarea id-ul user-ului folosindu-ne de username*/
    public Integer getUserId(String userName) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_USER_ID);
        preparedStatement.setString(1, userName);
        ResultSet rs = preparedStatement.executeQuery();
        Integer userId = null;
        while(rs.next()){
            userId = rs.getInt(1);
        }
        return userId;
    }
    /*Adaugarea unui fisier audio unui utilizator */
    public void addToUser(int idUser, List<String> audioFiles) throws SQLException{
        for(String audioFile:audioFiles){
            addToRelational(idUser, Integer.parseInt(audioFile));
        }
    }
    /*Stergerea unui fisier audio al unui utilizator*/
    public void deleteFromUser(int idUser, List<String> audioFiles) throws SQLException{
        for(String audioFile:audioFiles){
            deleteFromRelational(idUser, Integer.parseInt(audioFile));
        }
    }
    /*Ridica rangul unui utilizator la rangul de admin*/
    public boolean setAdmin(int idUser, boolean isAdmin) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(UPDATE_ADMIN_STATE);
        preparedStatement.setBoolean(1, isAdmin);
        preparedStatement.setInt(2, idUser);
        return preparedStatement.executeUpdate() > 0;
    }
    /*Actualizeaza username-ul unui utilizator*/
    public boolean updateUserName(int idUser, String newUserName) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(UPDATE_USERNAME);
        preparedStatement.setString(1, newUserName);
        preparedStatement.setInt(2, idUser);
        return preparedStatement.executeUpdate() > 0;
    }
    /*Actualizeaza parola unui utilizator */
    public boolean updatePassword(int idUser, String newPassword) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(UPDATE_PASSWORD);
        String key = null;
        /*Vom cripta parola veche folosind metoda encrypt*/
        try {
            key = getKey();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String cryptedPass = AES.encrypt(newPassword, key);
        preparedStatement.setString(1, cryptedPass);
        preparedStatement.setInt(2, idUser);
        return preparedStatement.executeUpdate() > 0;
    }
    /*Preluarea unui fisier pe baza id-ului */
    public File getFileById(int idFile) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_FILE_BY_ID);
        preparedStatement.setInt(1, idFile);
        ResultSet rs = preparedStatement.executeQuery();
        File file = null;
        while(rs.next()){
            file = new File(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5));
        }
        return file;
    }
    /* Returneaza o lista de note ale unui utilizator*/
    public List<Note> getUserNotes(int idUser) throws SQLException{
        List<Note> notes = new ArrayList<>();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_USER_NOTES);
        preparedStatement.setInt(1, idUser);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            File file = getFileById(rs.getInt(1));
            Note note = new Note(file, rs.getString(2), rs.getInt(3), rs.getInt(4));
            notes.add(note);
        }
        return notes;
    }
    /*Adaugarea unei note noi */
    public boolean addNote(Note note, int idUser) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(ADD_NOTE);
        preparedStatement.setInt(1, idUser);
        File file = note.getFile();
        preparedStatement.setInt(2, file.getIdFile());
        preparedStatement.setString(3, note.getNote());
        preparedStatement.setInt(4, note.getStartTime());
        preparedStatement.setInt(5, note.getEndTime());
        return preparedStatement.executeUpdate() > 0;
    }
}
