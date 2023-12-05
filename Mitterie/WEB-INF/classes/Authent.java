import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Authent", urlPatterns = {"/Authent"})

public class Authent extends HttpServlet
{
    public void doPost( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        
    }

}
