import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "NotifyBot", urlPatterns = { "/NotifyBot" })

public class NotifyBot extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
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

                Statement stmt2 = con.createStatement();
                String caca = "sa";
                if(stmt2.executeUpdate("NOTIFY bot, "+caca+";") == -1){
                    out.println("ya r pelo");
                }

                con.close();
            } catch (Exception e2) {
                con.close();
                out.println(e2.getMessage());
            }
        } catch (Exception e1) {
            out.println(e1.getMessage());
        }
    }

}
