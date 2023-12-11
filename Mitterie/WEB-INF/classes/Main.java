import java.sql.Statement;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Main", urlPatterns = {"/Main"})

public class Main extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("token") != null){

            String s = "";
            s = (String)session.getAttribute("rech");
            String f = "";
            if(s != null){
                f = s;
            }
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<link rel=\"stylesheet\" href=\'./css/main.css\'>");
            out.println("<title>Videos</title>");
            out.println("<body><center><h1>Vidéos des mites :</h1></center><a href=\"Disconnect\">Se déconnecter</a><br><a href=\"Entrance\">Retour</a><form action=Main method=post>Rechercher : <input name=rech type=text> <br/><input type=submit></form><table>");
            out.println(getAllVideosHtml(f)+"</table></body>");
        }else{
            res.sendRedirect("http://51.91.101.98:8080/Mitterie/");
        }
    }

    public static String getAllVideosHtml(String rech){
        String content = "";
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
                ResultSet rs = null;
                if(rech.equals("")){
                    rs = stmt.executeQuery("SELECT * FROM videosmitterie;");
                }else{
                    rs = stmt.executeQuery("SELECT * FROM videosmitterie WHERE UPPER(titre) LIKE UPPER('%"+rech+"%');");
                }
                while(rs.next()){
                    content = content + "<tr><td><iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/"+rs.getString(1)+"\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe></td><td><p>"+rs.getString(2)+"</p></td></tr>";
                }
                con.close();
            }catch(Exception e2){
                System.out.println(e2.getMessage());
                con.close();
            }
        }catch(Exception e1){
            System.out.println(e1.getMessage());
        }
        return content;
    }

}
