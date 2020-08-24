package servlets;

import model.File;
import model.Note;
import model.User;
import repository.MainRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/AddNoteServlet")
public class AddNoteServlet extends HttpServlet {
    MainRepository mainRepository = new MainRepository();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String filePath = req.getParameter("filePath");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        String  writtenNote = req.getParameter("note");
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("loggedUser");
        try {
            int fileId = mainRepository.getFileId(filePath);
            File file = mainRepository.getFileById(fileId);
            Note note = new Note(file, writtenNote, Integer.parseInt(startTime), Integer.parseInt(endTime));
            mainRepository.addNote(note, currentUser.getIdUser());
            resp.sendRedirect("listen.jsp");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
