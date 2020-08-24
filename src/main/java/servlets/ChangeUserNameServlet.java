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

@WebServlet("/ChangeUserNameServlet")
public class ChangeUserNameServlet extends HttpServlet {
    MainRepository mainRepository = new MainRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs = req.getRequestDispatcher("usernameChange.jsp");
        rs.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newUsername = req.getParameter("newUsername");
        List<User> users = new ArrayList<>();
        try {
            users = mainRepository.getUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(User user:users){
            if(newUsername.equals(user.getUsername())){
                PrintWriter out = resp.getWriter();
                out.println("<h1>Ops! A user with this name already exists  <a href='ChangeUserNameServlet'>Try again</a></h1>");
            }
        }
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("loggedUser");
        try {
            mainRepository.updateUserName(currentUser.getIdUser(), newUsername);
            RequestDispatcher rs = req.getRequestDispatcher("success.jsp");
            rs.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
