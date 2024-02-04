package controler;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SaveCroissantMain", urlPatterns = { "/SaveCroissantMain" })

public class SaveCroissantMain extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("token") != null) {
            if (req.getParameter("jeune") != null) {
                session.setAttribute("MainCroissant", true);
            } else {
                session.setAttribute("MainCroissant", false);
            }
        }
        res.sendRedirect("./Control?page=main");
    }

}
