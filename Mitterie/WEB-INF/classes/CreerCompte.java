import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "CreerCompte", urlPatterns = {"/CreerCompte"})

public class CreerCompte extends HttpServlet
{
    public void doPost( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        String l = req.getParameter("ulogin");
        String m = req.getParameter("upswd");
        String e = req.getParameter("uemail");

        if(l != null && m != null && e != null){
            if(!l.equals("")&&!m.equals("")&&!e.equals("")){
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
                        PreparedStatement pstmt = con.prepareStatement("INSERT INTO loginmitterie(login,mdp,role,email) VALUES(?,MD5(?),'utils',?);");
                        pstmt.executeUpdate();
                        con.close();
                    } catch (Exception e2) {
                        System.out.println(e2.getMessage());
                        con.close();
                    }
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            }
        }
        res.sendRedirect("http://51.91.101.98:8080/Mitterie/");
    }

}


