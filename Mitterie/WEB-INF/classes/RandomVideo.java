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
            out.println("<link rel=\"stylesheet\" href=\'./css/entrance.css\'>");
            out.println("<title>Video Random</title>");
            out.println("<a href=\"Disconnect\">Se déconnecter</a><br><a href=\"Entrance\">Retour</a><br><h1>"+txt[rd.nextInt(txt.length)]+"</h1>");
            out.println(getRandomVideo());
            out.println("<h2>Recharge la page pour avoir une nouvelle vidéo au hasard !</h2>");
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
                    res = res + "<iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/"+rs.getString(1)+"\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe><p>"+rs.getString(2)+"</p>";
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

