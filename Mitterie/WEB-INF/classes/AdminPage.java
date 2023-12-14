import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "AdminPage", urlPatterns = {"/AdminPage"})

public class AdminPage extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("token") != null){
            if(session.getAttribute("role") != null){
                if(session.getAttribute("role").equals("admin")){
                    res.setContentType("text/html");
                    PrintWriter out = res.getWriter();
                    out.println("<link rel=\"stylesheet\" href=\'./css/entrance.css\'>");
                    out.println("<title>Page Admin</title>");
                    out.println("<body><h1>Tu es admin, c'est bein !</h1><br><a href=\"Entrance\">Retour</a></body>");
                }else{
                    res.sendRedirect("http://51.91.101.98:8080/Mitterie/");
                }
            }else{
                res.sendRedirect("http://51.91.101.98:8080/Mitterie/");
            }
        }else{
            res.sendRedirect("http://51.91.101.98:8080/Mitterie/");
        }
    }

   

}

