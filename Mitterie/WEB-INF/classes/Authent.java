import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Authent", urlPatterns = {"/Authent"})

public class Authent extends HttpServlet
{
    public void doPost( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {

        boolean valid = false;
        String l = req.getParameter("uname");
        String m = req.getParameter("upswd");

        if(l != null && m != null){
            if(l.equals("") && m.equals("")){
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
                        // exécution de la requete
                        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM loginmitterie WHERE login ='"+l+"' AND mdp = '"+m+"';");
                        ResultSet rs = pstmt.executeQuery();
                        if(rs.next()){
                            valid = true;
                        }
                    }catch(Exception e2){
                        System.out.println(e2.getMessage());
                        con.close();
                    }
                }catch(Exception e1){
                    System.out.println(e1.getMessage());
                }
            }
        }

        if(!valid){
            res.sendRedirect("http://51.91.101.98:8080/Mitterie/");
        }else{
            HttpSession session = req.getSession(true);
            String token = l;
            session.setAttribute("token", token);
            res.sendRedirect("Main");
        }
    }

}
