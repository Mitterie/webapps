import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Playlist", urlPatterns = {"/Playlist"})

public class Playlist extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("token") != null){
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<link rel=\"stylesheet\" href=\'./css/playlist.css\'>");
            out.println("<title>Playlist Officiel</title>");
            out.println("<body><h1>Régale toi chef !</h1><div class=\"carBT\">");
            out.println("<iframe style=\"border-radius:12px\" src=\"https://open.spotify.com/embed/playlist/3EcbQmX1J0E4Ii8uty41VS?utm_source=generator&theme=0\" width=\"500\" height=\"352\" frameBorder=\"0\" allowfullscreen=\"\" allow=\"autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture\" loading=\"lazy\"></iframe>");
            out.println("</body>");
        }else{
            res.sendRedirect("http://51.91.101.98:8080/Mitterie/");
        }
    }

}

