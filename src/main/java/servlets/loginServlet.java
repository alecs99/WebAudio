package servlets;

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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        MainRepository mainRepository = new MainRepository();
        List<User> usersList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            usersList = mainRepository.getUsers();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        for(User user:usersList){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                HttpSession session = req.getSession();
                session.setAttribute("loggedUser", user);
                RequestDispatcher rs = req.getRequestDispatcher("OptionsServlet");
                rs.forward(req,resp);
            }
        }
        RequestDispatcher rs = req.getRequestDispatcher("loginFailed.jsp");
        rs.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs;

        if(req.getParameter("param") != null) {
            rs = req.getRequestDispatcher("OptionsServlet");
        }else {
            rs = req.getRequestDispatcher("index.jsp");
        }

        rs.forward(req,resp);
    }
}