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

@WebServlet(name = "NotifyBot", urlPatterns = { "/NotifyBot" })

public class NotifyBot extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

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

                PreparedStatement stmt2 = con.prepareStatement("NOTIFY bot, ?");
                stmt2.setString(1, "truc");
                stmt2.execute();

                con.close();
            } catch (Exception e2) {
                con.close();
                System.out.println(e2.getMessage());
            }
        } catch (Exception e1) {
            System.err.println(e1.getMessage());
        }
    }

}
