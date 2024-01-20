import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "UploadPage", urlPatterns = { "/UploadPage" })

public class UploadPage extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("token") != null) {
            if (session.getAttribute("role") != null) {
                if (((String) session.getAttribute("role")).equals("admin")) {
                    res.setContentType("text/html");
                    PrintWriter out = res.getWriter();
                    out.println("<title>Page upload</title>");
                    out.println("<link rel=\"stylesheet\" href=\'./css/upload.css\'><a href=\"Entrance\">Retour</a><body>");

                    String txt = "<div class=\"video\"><h1>Upload ta vidéo !</h1>"+
                    "<form action=\"UploadVideo\" method=\"post\">"+
                    "<div class=\"url\">"+
                        "<input type=\"text\" placeholder=\"Entrez l'url de la vidéo (sans espace ou autre caractères)\" name=\"url\" required>"+
                        "<p>Exemple d'url valide :</p><img>../img/exempleurl.png</img>"+
                    "</div>"+
                    "<div class=\"titre\">"+
                        "<input type=\"text\" placeholder=\"Entrez le nom de la Vidéo (sans accent ou charactères spécial)\" name=\"titre\" required>"+
                        "<p>Exemple de titre valide :</p><img>../img/exempleTitre.png</img>"+
                    "</div>"+
                    "<button type=\"submit\">Upload</button>"+
                    "<p>Si l'upload réussi vous serez reidirigé vers l'acceuil sinon vous reviendrez sur cette page</p>"+
                "</form></div></body>";

                out.println(txt);
                    
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

}
