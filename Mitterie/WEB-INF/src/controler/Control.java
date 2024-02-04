package controler;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Control", urlPatterns = { "/Control" })

public class Control extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String page = req.getParameter("page");

        HttpSession session = req.getSession();
        if (session.getAttribute("token") != null) {
            if (page != null) {
                switch (page) {
                    case "entrance":
                        res.sendRedirect("./entrance.jsp");
                        break;

                    case "authentShortcut":
                        if(session.getAttribute("token") != null){
                            res.sendRedirect("./entrance.jsp");
                        }else{
                            res.sendRedirect("/Mitterie/");
                        }
                        break;

                    case "disconnect":
                        session.invalidate();
                        res.sendRedirect("/Mitterie/");
                        break;

                    case "random":
                        res.sendRedirect("./randomVideo.jsp");
                        break;

                    case "playlist":
                        res.sendRedirect("./playlist.jsp");
                        break;

                    case "adminPage":
                        res.sendRedirect("./adminPage.jsp");
                        break;

                    case "upload":
                        res.sendRedirect("./uploadPage.jsp");
                        break;

                    case "main":
                        res.sendRedirect("./main.jsp");
                        break;
                        
                    default:
                        res.sendRedirect("/Mitterie/");
                        break;
                }
            }
        } else {
            res.sendRedirect("/Mitterie/");
        }
    }

}
