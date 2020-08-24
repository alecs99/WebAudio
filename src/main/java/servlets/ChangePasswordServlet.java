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


@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    MainRepository mainRepository = new MainRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs = req.getRequestDispatcher("passwordChange.jsp");
        rs.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("loggedUser");
        String oldPass = req.getParameter("oldPass");
        String newPass = req.getParameter("newPass");
        if(oldPass.equals(currentUser.getPassword())){
            PrintWriter out = resp.getWriter();
            out.println("<h1>Ops! Your old password did not match with our database's  <a href='ChangePasswordServlet'>Try again</a></h1>");
        }
        try {
            mainRepository.updatePassword(currentUser.getIdUser(), newPass);
            RequestDispatcher rs = req.getRequestDispatcher("success.jsp");
            rs.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
