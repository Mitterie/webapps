import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "AdminPage", urlPatterns = { "/AdminPage" })

public class AdminPage extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("token") != null) {
            if (session.getAttribute("role") != null) {
                if (((String) session.getAttribute("role")).equals("admin")) {
                    res.setContentType("text/html");
                    PrintWriter out = res.getWriter();
                    out.println("<link rel=\"stylesheet\" href=\'./css/admin.css\'>");
                    out.println("<title>Page Admin</title>");
                    out.println("<body><h1>Tu es admin, c'est bein !</h1><br><a href=\"Entrance\">Retour</a><table>"
                            + getUsersContent() + "</table></body>");
                } else {
                    res.sendRedirect("Entrance");
                }
            } else {
                res.sendRedirect("Entrance");
            }
        } else {
            res.sendRedirect("Entrance");
        }
    }

    public static String getUsersContent() {
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
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT id,login,role,email,daylastco,hourlastco FROM loginmitterie;");
                while(rs.next()){
                    res = res +"<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td></tr>";
                }
                con.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
                con.close();
            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
        return res;
    }

}
