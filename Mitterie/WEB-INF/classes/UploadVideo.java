import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "UploadVideo", urlPatterns = { "/UploadVideo" })

public class UploadVideo extends HttpServlet {
    public void doPost( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if (session.getAttribute("token") != null) {
            if (session.getAttribute("role") != null) {
                if (((String) session.getAttribute("role")).equals("admin")) {
                    String urlVideo = req.getParameter("url");
                    String titreVideo = req.getParameter("titre");
                    if(urlVideo != null && titreVideo != null){
                        if(!urlVideo.equals("") &&  !titreVideo.equals("")){
                            boolean fait = false;
                            try{
                                String[] urlTab = urlVideo.split("/");
                                try{
                                    Properties pr = new Properties();
                                    pr.load(new FileInputStream("/opt/tomcat/postgres.prop"));
                                    // enregistrement du driver
                                    Class.forName(pr.getProperty("driver"));
                                    // connexion à la base
                                    String url = pr.getProperty("url");
                                    String nom = pr.getProperty("nom");
                                    String mdp = pr.getProperty("mdp");
                                    Connection con = DriverManager.getConnection(url, nom, mdp);
                                    try{
                                        PreparedStatement stmt = con.prepareStatement("INSERT INTO videosmitterie VALUES (?,?,'"+LocalDate.now().toString()+"','"+LocalTime.now().toString()+"');");
                                        stmt.setString(1, urlTab[3]);
                                        stmt.setString(2, titreVideo);
                                        int r = stmt.executeUpdate();
                                        if(r != -1){
                                            fait = true; 
                                            PreparedStatement stmt2 = con.prepareStatement("NOTIFY bot, ?");
                                            stmt2.setString(1, titreVideo);
                                            stmt2.execute();
                                        }
                                        con.close();
                                    }catch(Exception e2){
                                        con.close();
                                    }
                                }catch(Exception e1){}
                            }catch(Exception e){} 
                            if(fait){
                                res.sendRedirect("Entrance");
                            }else{
                                res.sendRedirect("UploadPage");
                            }
                        }else{
                            res.sendRedirect("UploadPage");
                        }
                    }else{
                        res.sendRedirect("UploadPage");
                    }

                } else {
                    res.sendRedirect("Entrance");
                }
            } else {
                res.sendRedirect("Entrance");
            }
        } else {
            res.sendRedirect("Entrance");
        }
    }

}
