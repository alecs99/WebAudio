package servlets;

import model.File;
import model.User;
import repository.MainRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/FileAccessServlet")
public class FileAccessServlet extends HttpServlet {
    MainRepository mainRepository = new MainRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<File> files = new ArrayList<>();
        List<File> userFiles = new ArrayList<>();
        //preiau fisierele audio pe care le are utilizatorul pentru a nu le afisa fiind irelevant
        HttpSession session = req.getSession();
        String idUser = (String) session.getAttribute("selectedUserId");
        try {
            files = mainRepository.getAllFiles();
            userFiles = mainRepository.getUserFiles(Integer.parseInt(idUser));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //selectam doar fisierele audio care nu apartin utilizatorului
        List<File> finalUserFiles = userFiles;
        List<File> newFiles = files
                            .stream()
                            .filter(file -> !finalUserFiles.contains(file))
                            .collect(Collectors.toList());
        req.setAttribute("selectionFiles", newFiles);
        RequestDispatcher rs = req.getRequestDispatcher("fileAccess.jsp");
        rs.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> files = Arrays.asList(req.getParameterValues("fileAccess"));
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("selectedUserId");
        session.removeAttribute("selectedUserId");
        try {
            mainRepository.addToUser(Integer.parseInt(userId), files);
            RequestDispatcher rs = req.getRequestDispatcher("success.jsp");
            rs.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
