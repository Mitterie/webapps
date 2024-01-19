import java.sql.Statement;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Main", urlPatterns = {"/Main"})

public class Main extends HttpServlet
{

    private static int nbVideo = 0;
    private static int nbPage = 0;
    private static int numeroPage = 0;

    public void service( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("token") != null){

            String s = req.getParameter("rech");
            if(s == null){
                s = "";
                if(session.getAttribute("rech")!= null){
                    session.removeAttribute("rech");
                }
            }else if(s.equals("")){
                if(session.getAttribute("rech")!= null){
                    session.removeAttribute("rech");
                }
            }else{
                session.setAttribute("rech", s);
            }

            if(req.getParameter("numeroPage") == null){
                numeroPage = 0;
            }else{
                try{
                    numeroPage = Integer.parseInt(req.getParameter("numeroPage"));
                }catch(Exception e){
                    numeroPage = 0;
                }
            }
            
            boolean croissant = false;
            String affichageCroissant = "";
            if(session.getAttribute("MainCroissant") != null){
                if((boolean)session.getAttribute("MainCroissant")){
                    croissant = true;
                    affichageCroissant = "checked";
                }else{
                    croissant = false;
                }
            }else{
                croissant = false;
            }

            String rechFinal = "";
            if((String)session.getAttribute("rech") != null ){
                rechFinal = (String)session.getAttribute("rech");
            }

            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<link rel=\"stylesheet\" href=\'./css/main.css\'>");
            out.println("<title>Videos</title>");
            out.println("<div class=\"list\"><h1>On fait quoi ?</h1><ul><li><a href=\"Entrance\">Retour</a></li><li><form action=Main method=post><input name=rech type=text placeholder=\"Rechercher...\"><input type=submit value=\"Valider\"></form></li><li><a href=\"Disconnect\">Se déconnecter</a></li></div>");
            out.println("<body><div class=\"videos\"><h2>Vidéos des mites</h2><form action=\"SaveCroissantMain\" method=\"post\"><input type=\"checkbox\" name=\"jeune\" value=\"vrai\" "+affichageCroissant+"/><label for=\"jeune\">Ordre croissant</label><button type=\"submit\">Recharger</button></form>");
            out.println(getAllVideosHtml(croissant,rechFinal)+"</div>");
            out.println(getListButtons(rechFinal)+"</body>");
        }else{
            res.sendRedirect("/Mitterie");
        }
    }

    public static String getAllVideosHtml(boolean cr, String rech){
        String content = "";
        try{
            Properties pr = new Properties();
            pr.load(new FileInputStream("/opt/tomcat/postgres.prop"));
            // enregistrement du driver
            Class.forName(pr.getProperty("driver"));
            // connexion à la base
            String url = pr.getProperty("url");
            String nom = pr.getProperty("nom");
            String mdp = pr.getProperty("mdp");
            Connection con = DriverManager.getConnection(url, nom, mdp);
            try{
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT (*) FROM videosmitterie;");
                rs.next();
                nbVideo = rs.getInt(1);
                nbPage = nbVideo/9;
                if(nbVideo%9 != 0){
                    nbPage++;
                }
                rs = null;
                if(cr){
                    if(rech.equals("")){
                        rs = stmt.executeQuery("SELECT * FROM videosmitterie ORDER BY datesortie DESC, heuresortie DESC;");
                    }else{
                        rs = stmt.executeQuery("SELECT COUNT (*) FROM videosmitterie WHERE UPPER(titre) LIKE UPPER('%"+rech+"%');");
                        rs.next();
                        nbVideo = rs.getInt(1);
                        nbPage = nbVideo/9;
                        if(nbVideo%9 != 0){
                            nbPage++;
                        }
                        rs = null;
                        rs = stmt.executeQuery("SELECT * FROM videosmitterie WHERE UPPER(titre) LIKE UPPER('%"+rech+"%') ORDER BY datesortie DESC, heuresortie DESC;");
                    }
                }else{
                    if(rech.equals("")){
                        rs = stmt.executeQuery("SELECT * FROM videosmitterie ORDER BY datesortie , heuresortie;");
                    }else{
                        rs = stmt.executeQuery("SELECT COUNT (*) FROM videosmitterie WHERE UPPER(titre) LIKE UPPER('%"+rech+"%');");
                        rs.next();
                        nbVideo = rs.getInt(1);
                        nbPage = nbVideo/9;
                        if(nbVideo%9 != 0){
                            nbPage++;
                        }
                        rs = null;
                        rs = stmt.executeQuery("SELECT * FROM videosmitterie WHERE UPPER(titre) LIKE UPPER('%"+rech+"%') ORDER BY datesortie , heuresortie;");
                    }
                }
                ArrayList<String> links = new ArrayList<>();
                ArrayList<String> noms = new ArrayList<>();
                while(rs.next()){
                    links.add(rs.getString(1));
                    noms.add(rs.getString(2));
                }

                if((numeroPage * 9)+9 >= links.size()){
                    for(int i = (numeroPage * 9);i < links.size();i ++){
                        content = content + "<div class=\"video\"><iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/"+links.get(i)+"\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe><p>"+noms.get(i)+"</p></div>";
                    }
                }else{
                    for(int i = (numeroPage * 9);i < (numeroPage * 9)+9;i ++){
                        content = content + "<div class=\"video\"><iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/"+links.get(i)+"\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe><p>"+noms.get(i)+"</p></div>";
                    }
                }

                
                
                con.close();
            }catch(Exception e2){
                System.out.println(e2.getMessage());
                content = content + e2.getMessage();
                con.close();
            }
        }catch(Exception e1){
            System.out.println(e1.getMessage());
            content = content + e1.getMessage();
        }
        return content;
    }

    public String getListButtons(String rech){
        String res = "<div class=\"listButton\">";
        if(rech.equals("")){
            for(int i = 0;i < nbPage;i ++){
                res = res + "<a href=\"http://51.91.101.98/Mitterie/Main?numeroPage="+i+"\">"+(i+1)+"</a>";
            }
        }else{
            for(int i = 0;i < nbPage;i ++){
                res = res + "<a href=\"http://51.91.101.98/Mitterie/Main?numeroPage="+i+"&rech="+rech+"\">"+(i+1)+"</a>";
            }
        }
        res = res + "</div>";
        return res;
    }

}
