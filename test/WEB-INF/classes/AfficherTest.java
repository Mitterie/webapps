import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "servlet-AfficherTest", urlPatterns = {"/servlet-AfficherTest"})

public class AfficherTest extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
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
                // exécution de la requete
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM test;");
                while (rs.next()) {
                    content = content + "<h1>"+rs.getString(1)+"</h1><br>";
                }
                con.close();
            }catch(Exception e2){
                con.close();
                content = "erreur lors de la requette sql";
            }
        }catch(Exception e1){
            content = "La connection a la base de donnée a échoué";
        }
        out.println(content);
    }

}

