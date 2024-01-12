import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "AuthentShortcut", urlPatterns = {"/AuthentShortcut"})

public class AuthentShortcut extends HttpServlet
{
    public void doPost( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("token") != null){
            try {
                    Properties pr = new Properties();
                    pr.load(new FileInputStream("/opt/tomcat/postgres.prop"));
                    // enregistrement du driver
                    Class.forName(pr.getProperty("driver"));
                    // connexion à la base
                    String url = pr.getProperty("url");
                    String nom = pr.getProperty("nom");
                    String mdp = pr.getProperty("mdp");
                    Connection con = DriverManager.getConnection(url, nom, mdp);
                    try {
                        // exécution de la requete
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("UPDATE loginmitterie SET daylastco = '"+LocalDate.now().toString()+"', hourlastco = '"+LocalTime.now().toString()+"' WHERE login = "+session.getAttribute("token")+";");
                        con.close();
                    } catch (Exception e2) {
                        System.out.println(e2.getMessage());
                        con.close();
                    }
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            res.sendRedirect("Entrance");
        }else{
            res.sendRedirect("http://51.91.101.98/Mitterie/");
        }
    }

}
