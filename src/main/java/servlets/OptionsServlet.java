package servlets;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/OptionsServlet")
public class OptionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("loggedUser");
        req.setAttribute("isAdmin", currentUser.getAdmin());
        req.setAttribute("username", currentUser.getUsername());
        resp.setContentType("text/html");
        RequestDispatcher rs = req.getRequestDispatcher("userOptions.jsp");
        rs.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("loggedUser");
        req.setAttribute("isAdmin", currentUser.getAdmin());
        req.setAttribute("username", currentUser.getUsername());
        resp.setContentType("text/html");
        RequestDispatcher rs = req.getRequestDispatcher("userOptions.jsp");
        rs.forward(req,resp);
    }
}
