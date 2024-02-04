package controler;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modele.DAO.UserDAO;
import modele.POJO.User;

@WebServlet(name = "CreateAccount", urlPatterns = {"/CreateAccount"})

public class CreateAccount extends HttpServlet
{
    public void doPost( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        String l = req.getParameter("ulogin");
        String m = req.getParameter("upswd");
        String e = req.getParameter("uemail");

        boolean bon = false;

        if(l != null && m != null && e != null){
            if((!l.equals(""))&&(!m.equals(""))&&(!e.equals(""))){
                bon = UserDAO.createUser(new User(-1, l, m, "", e, null, null));
            }
        }
        
        if(bon){
            res.sendRedirect("/Mitterie/");
        }else{
            res.sendRedirect("/Mitterie/createAccount.jsp");
        }
    }

}