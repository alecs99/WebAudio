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
import java.util.List;

@WebServlet("/UserFileAccessServlet")
public class UserFileAccessServlet extends HttpServlet {
    MainRepository mainRepository = new MainRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        try {
            users = mainRepository.getUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("users", users);
        RequestDispatcher rs = req.getRequestDispatcher("fileAccessSelectUser.jsp");
        rs.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idUser = req.getParameter("selectedUser");
        HttpSession session = req.getSession();
        session.setAttribute("selectedUserId", idUser);
        resp.sendRedirect("FileAccessServlet");
    }
}
