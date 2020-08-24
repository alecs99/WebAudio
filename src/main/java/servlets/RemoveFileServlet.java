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

@WebServlet("/RemoveFileServlet")
public class RemoveFileServlet extends HttpServlet {
    MainRepository mainRepository = new MainRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<File> userFiles = new ArrayList<>();
        //preiau fisierele audio pe care le are utilizatorul
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("loggedUser");
        try {
            userFiles = mainRepository.getUserFiles(currentUser.getIdUser());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("selectionFiles", userFiles);
        RequestDispatcher rs = req.getRequestDispatcher("removeAudio.jsp");
        rs.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idFile = req.getParameter("selectedFile");
        try {
            String filePath = mainRepository.getFilePath(Integer.parseInt(idFile));
            java.io.File file = new java.io.File("/Users/alc/IdeaProjects/WebAudio/src/main/webapp/" + filePath);
            if(file.delete()){
                mainRepository.deleteFile(idFile);
                RequestDispatcher rs = req.getRequestDispatcher("success.jsp");
                rs.forward(req,resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
