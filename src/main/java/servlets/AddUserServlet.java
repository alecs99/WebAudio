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
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    MainRepository mainRepository = new MainRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<File> audioFiles = new ArrayList<>();
        //Preiau fisierele audio din BD*/
        try {
            audioFiles = mainRepository.getAllFiles();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("audioFiles", audioFiles);
        RequestDispatcher rs = req.getRequestDispatcher("newUser.jsp");
        rs.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("userPassword");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        boolean admin = false;
        if(req.getParameter("isAdmin") != null){
            admin = true;
        }
        User user = new User(userName, password, firstName, lastName, email, admin);
        List<String> audioFiles = Arrays.asList(req.getParameterValues("audios"));
        int userId = 0;
        try {
            mainRepository.addUser(user);
            userId = mainRepository.getUserId(user.getUsername());
            mainRepository.addToUser(userId, audioFiles);
            System.out.println(audioFiles);
            RequestDispatcher rs = req.getRequestDispatcher("success.jsp");
            rs.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
