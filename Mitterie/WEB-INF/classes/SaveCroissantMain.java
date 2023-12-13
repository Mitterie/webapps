import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "SaveCroissantMain", urlPatterns = {"/SaveCroissantMain"})

public class SaveCroissantMain extends HttpServlet
{
    public void doPost( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("token") != null){
            if(req.getParameter("jeune") != null){
                session.setAttribute("MainCroissant", true);
            }else{
                session.setAttribute("MainCroissant", false);
            }
        }
        res.sendRedirect("Main");
    }
}