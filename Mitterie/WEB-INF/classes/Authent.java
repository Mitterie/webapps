import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Authent", urlPatterns = { "/Authent" })

public class Authent extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        boolean valid = false;
        String l = req.getParameter("uname");
        String m = req.getParameter("upswd");

        if (l != null && m != null) {
            if (!l.equals("") && !m.equals("")) {
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
                        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM loginmitterie WHERE login = ? AND mdp = MD5(?);");
                        pstmt.setString(1, l);
                        pstmt.setString(2, m);
                        ResultSet rs = pstmt.executeQuery();
                        if (rs.next()) {
                            valid = true;
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate("UPDATE loginmitterie SET daylastco = '"+LocalDate.now().toString()+"', hourlastco = '"+LocalTime.now().toString()+"' WHERE login = '"+l+"';");
                        }
                        
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

        if (!valid) {
            res.sendRedirect("/Mitterie");
        } else {
            HttpSession session = req.getSession(true);
            String token = l;
            session.setAttribute("token", token);
            session.setAttribute("role", getRole(l));
            session.setMaxInactiveInterval(3600);
            res.sendRedirect("/Mitterie/Entrance");
        }
    }

    public static String getRole(String login) {
        String res = "";
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
                PreparedStatement pstmt = con.prepareStatement("SELECT role FROM loginmitterie WHERE login = ?");
                pstmt.setString(1, login);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    res = rs.getString(1);
                }
                con.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
                res = "utils";
                con.close();
            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
            res = "utils";
        }
        return res;
    }

}
