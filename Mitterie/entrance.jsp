<%@ page import="java.util.List" %>
<%@ page import="modele.DAO.VideoDAO" %>
<%@ page import="modele.POJO.Video" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<%
    if(session.getAttribute("token") == null){
        response.sendRedirect("/Mitterie/");
    }
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css"  rel="stylesheet"  href="${pageContext.request.contextPath}/css/entrance.css">
    <title>Accueil</title>
</head>

<body>
    <div class="list">
        <h1>On fait quoi ?</h1>
        <ul>
            <li><a href="Control?page=main">Toutes les vidéos</a></li>
            <li><a href="Control?page=random">Générateur de vidéo aléatoire</a></li>
            <li><a href="Control?page=playlist">Voir la "Playlist Officiel"</a></li>
            <%
                if(session.getAttribute("role") != null) {
                    if(session.getAttribute("role").equals("admin")) {
                        out.println("<li><a href=\"Control?page=adminPage\">Voir la \"Page Admin\"</a></li>");
                        out.println("<li><a href=\"Control?page=upload\">Voir la \"Page Upload\"</a></li>");
                    }
                }
            %>
            <li><a href="Control?page=disconnect">Se déconnecter</a></li>
        </ul>
    </div>

    <div class="videos">
        <h2>Les 3 dernières vidéos mises en ligne :</h2>
        <%
            List<Video> v = VideoDAO.find3Last();
            for(int i = 0;i < v.size();i ++){
                out.println("<div class=\"video\"><iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/"+v.get(i).getUrl()+"\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe><p>"+v.get(i).getTitle()+"</p></div>");
            }
        %>
    </div>
</body>

</html>