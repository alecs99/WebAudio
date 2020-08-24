package servlets;

import model.Note;
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

@WebServlet("/DeleteNoteServlet")
public class DeleteNoteServlet extends HttpServlet {
    MainRepository mainRepository = new MainRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Note> notes = new ArrayList<>();
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("loggedUser");
        try {
            notes = mainRepository.getUserNotes(currentUser.getIdUser());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("notes", notes);
        RequestDispatcher rs = req.getRequestDispatcher("deleteNote.jsp");
        rs.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idNote = req.getParameter("selectedNote");
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("loggedUser");
        try {
            mainRepository.deleteFromNotes(currentUser.getIdUser(), Integer.parseInt(idNote));
            RequestDispatcher rs = req.getRequestDispatcher("success.jsp");
            rs.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
