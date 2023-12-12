import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "RandomVideo", urlPatterns = {"/RandomVideo"})

public class RandomVideo extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("token") != null){
            Random rd = new Random();
            String[] txt = new String[]{"Regarde ca !","Check ca !","Ouuhh... pas mal ca !","Regarde cette masterclass !","J'ai trop rigoler sur celle la !"};
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<link rel=\"stylesheet\" href=\'./css/random.css\'>");
            out.println("<title>Video Random</title>");
            out.println("<div class=\"list\"><h1>On fait quoi ?</h1>\r\n" + //
                    "    <ul>\r\n" + //
                    "        <li><a href=\"Entrance\">Retour</a></li>\r\n" + //
                    "        <li><a href=\"Disconnect\">Se déconnecter</a></li>\r\n" + //
                    "    </ul>\r\n" + //
                    "</div>");
            out.println("<div class=\"videos\"><h2>"+txt[rd.nextInt(txt.length)]+"</h2>");
            out.println(getRandomVideo());
            out.println("<form action=RandomVideo method=post><button type=\"submit\">Une autre vidéo !</button></form></div>");
        }else{
            res.sendRedirect("http://51.91.101.98:8080/Mitterie/");
        }
    }

    public static String getRandomVideo(){
        String res = "";
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
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM videosmitterie ORDER BY RANDOM() LIMIT 1;");
                while (rs.next()) {
                    res = res + "<div class=\"video\"><iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/"+rs.getString(1)+"\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe><p>"+rs.getString(2)+"</p></div>";
                }
                con.close();
            }catch(Exception e2){
                System.out.println(e2.getMessage());
                con.close();
            }
        }catch(Exception e1){
            System.out.println(e1.getMessage());
        }
        return res;
    }

}

