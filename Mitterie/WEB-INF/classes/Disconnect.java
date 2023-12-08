import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Disconnect", urlPatterns = {"/Disconnect"})

public class Disconnect extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        session.setAttribute("token", null);
        res.sendRedirect("http://51.91.101.98:8080/Mitterie/");
    }

}

