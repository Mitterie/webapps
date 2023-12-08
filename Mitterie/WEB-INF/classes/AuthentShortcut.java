import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "AuthentShortcut", urlPatterns = {"/AuthentShortcut"})

public class AuthentShortcut extends HttpServlet
{
    public void doPost( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("token") != null){
            res.sendRedirect("Main");
        }else{
            res.sendRedirect("http://51.91.101.98:8080/Mitterie/");
        }
    }

}
