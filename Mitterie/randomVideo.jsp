<%@ page import="modele.DAO.VideoDAO" %>
<%@ page import="modele.POJO.Video" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    if(session.getAttribute("token") == null){
        response.sendRedirect("/Mitterie/");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link type="text/css"  rel="stylesheet"  href="${pageContext.request.contextPath}/css/random.css">
    <title>Video Random</title>
</head>

<body>
    <div class="list">
        <h1>On fait quoi ?</h1>
        <ul>
            <li><a href="Control?page=entrance">Retour</a></li>
            <li><a href="Control?page=disconnect">Se déconnecter</a></li>
        </ul>
    </div>
    <div class="videos">
        <h2>Regarde ca !</h2>
        <%
            Video v = VideoDAO.findRandom();
            out.println("<div class=\"video\"><iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/"+v.getUrl()+"\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe><p>"+v.getTitle()+"</p></div>");
        %>
        <form action="Control?page=random" method="post"><button type="submit">Une autre vidéo !</button></form>
    </div>
</body>

</html>