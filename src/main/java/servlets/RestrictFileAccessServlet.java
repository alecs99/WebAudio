package servlets;

import model.File;
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

@WebServlet("/RestrictFileAccessServlet")
public class RestrictFileAccessServlet extends HttpServlet {
    MainRepository mainRepository = new MainRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<File> userFiles = new ArrayList<>();
        //preiau fisierele audio pe care le are utilizatorul
        HttpSession session = req.getSession();
        String idUser = (String) session.getAttribute("selectedUserId");
        try {
            userFiles = mainRepository.getUserFiles(Integer.parseInt(idUser));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("selectionFiles", userFiles);
        RequestDispatcher rs = req.getRequestDispatcher("restrictFileAccess.jsp");
        rs.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> files = Arrays.asList(req.getParameterValues("fileAccess"));
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("selectedUserId");
        session.removeAttribute("selectedUserId");
        try {
            mainRepository.deleteFromUser(Integer.parseInt(userId), files);
            RequestDispatcher rs = req.getRequestDispatcher("success.jsp");
            rs.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
