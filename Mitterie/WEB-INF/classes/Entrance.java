import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Entrance", urlPatterns = {"/Entrance"})

public class Entrance extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("token") != null){
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<link rel=\"stylesheet\" href=\'./css/entrance.css\'>");
            out.println("<title>Acceuil</title>");
            out.println("<a href=\"Disconnect\">Se déconnecter</a><br><h1>On fait quoi ?</h1>");
            out.println("<div class=\"list\">\r\n" + //
                    "    <ul>\r\n" + //
                    "        <li><a href=\"Main\">Toutes les vidéos</a></li>\r\n" + //
                    "        <li><a href=\"RandomVideo\">Générateur de vidéo aléatoire</a></li>\r\n" + //
                    "    </ul>\r\n" + //
                    "</div>");
            out.println("<h2>Les 3 dernières vidéos mises en ligne :</h2><br>");
            out.println(get3LastVideos());
        }else{
            res.sendRedirect("http://51.91.101.98:8080/Mitterie/");
        }
    }

    public static String get3LastVideos(){
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
                ResultSet rs = stmt.executeQuery("SELECT * FROM videosmitterie LIMIT 3 OFFSET (SELECT COUNT(*) - 3 FROM videosmitterie);");
                ArrayList<String> urls = new ArrayList<>();
                ArrayList<String> noms = new ArrayList<>();
                while (rs.next()) {
                    urls.add(rs.getString(1));
                    noms.add(rs.getString(2));
                }
                //recup des 3 dernieres videos
                int nb = 1;
                for(int i = urls.size()-1;i >= 0 ; i--){
                    res = res + "<div class=\"video"+nb+"\"><iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/"+urls.get(i)+"\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe><p>"+noms.get(i)+"</p></div><br>";
                    nb++;
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

