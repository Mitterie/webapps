package controler;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modele.DAO.SecurityDAO;
import modele.DAO.UserDAO;

@WebServlet(name = "Authent", urlPatterns = { "/Authent" })

public class Authent extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String l = req.getParameter("uname");
        String m = req.getParameter("upswd");

        HttpSession session = req.getSession();

        if(SecurityDAO.isUserValid(l, m)){
            session.setAttribute("token", l);  
            session.setAttribute("role", UserDAO.find(l).getRole());    
        }
        res.sendRedirect("Control?page=entrance");
    }

}
