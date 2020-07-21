package servlets;

import model.User;
import repository.MainRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ListUsersServlet")
public class ListUsersServlet extends HttpServlet {
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
        RequestDispatcher rs = req.getRequestDispatcher("userList.jsp");
        rs.forward(req,resp);
    }
}
