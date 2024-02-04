package controler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modele.DAO.VideoDAO;
import modele.POJO.Video;

@WebServlet(name = "UploadVideo", urlPatterns = { "/UploadVideo" })

public class UploadVideo extends HttpServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        boolean valid = false;

        if (session.getAttribute("token") != null) {
            if (session.getAttribute("role") != null) {
                if ((session.getAttribute("role")).equals("admin")) {
                    valid = true;
                }
            }
        }

        if (valid) {
            boolean fait = false;
            String urlVideo = req.getParameter("url");
            String titreVideo = req.getParameter("titre");

            if (urlVideo != null && titreVideo != null) {
                if (!urlVideo.equals("") && !titreVideo.equals("")) {
                    try {
                        String[] urlTab = urlVideo.split("/");
                        fait = VideoDAO.createVideo(new Video(urlTab[3], titreVideo, LocalDate.now(), LocalTime.now()));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            if(fait){
                res.sendRedirect("Control?page=entrance");
            }else{
                res.sendRedirect("Control?page=upload");
            }

        } else {
            res.sendRedirect("/Mitterie/");
        }
    }

}
