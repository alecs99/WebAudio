package servlets;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import repository.MainRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.AudioFileFormat;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/newServlet")
public class newServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    MainRepository mainRepository = new MainRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs = req.getRequestDispatcher("addAudio.jsp");
        rs.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        if(ServletFileUpload.isMultipartContent(req)){
            try{
                String filePath = null;
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
                for(FileItem item:multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        filePath = "/Users/alc/IdeaProjects/WebAudio/src/main/webapp/audioFiles"+ File.separator + name;
                        File uploadedFile = new File(filePath);
                        item.write(uploadedFile);
                    }
                }
                //preiau informatii pentru a adauga fisierul in baza de date
                int nextId = mainRepository.getNextFileId();
                System.out.println(filePath);
                File uploadedFile = new File(filePath);
                AudioFileFormat fileFormat = new MpegAudioFileReader().getAudioFileFormat(uploadedFile);
                Map properties = fileFormat.properties();
                String fileName = (String) properties.get("title");
                String author = (String) properties.get("author");
                Long length = (Long) properties.get("duration");
                model.File fileToDB = new model.File(nextId, fileName, author, Long.toString(length), filePath);
                HttpSession session = req.getSession();
                User currentUser = (User) session.getAttribute("loggedUser");
                mainRepository.addToAudio(fileToDB);
                RequestDispatcher rs = req.getRequestDispatcher("success.jsp");
                rs.forward(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
