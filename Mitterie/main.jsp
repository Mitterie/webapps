<%@ page import="java.util.List" %>
<%@ page import="modele.DAO.VideoDAO" %>
<%@ page import="modele.POJO.Video" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    if(session.getAttribute("token") == null){
        response.sendRedirect("/Mitterie/");
    }
%>

<html>

<head>
    <link type="text/css"  rel="stylesheet"  href="${pageContext.request.contextPath}/css/main.css">
    <title>Videos</title>
</head>

<%
            //recup des parametres 
            int nbVideo = 0;
            int nbPage = 0;
            int numeroPage = 0;

            String s = request.getParameter("rech");
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

            if(request.getParameter("numeroPage") == null){
                numeroPage = 0;
            }else{
                try{
                    numeroPage = Integer.parseInt(request.getParameter("numeroPage"));
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
                }
            }    
            
            String rechFinal = "";
            if((String)session.getAttribute("rech") != null ){
                rechFinal = (String)session.getAttribute("rech");
            }
%>

<body>
    <div class="list">
        <h1>On fait quoi ?</h1>
        <ul>
            <li><a href="Control?page=entrance">Retour</a></li>
            <li><form action="main.jsp" method="post"><input name="rech" type="text" placeholder="Rechercher..."><input type="submit" value="Valider"></form></li>
            <li><a href="Control?page=disconnect">Se déconnecter</a></li>
        </ul>
    </div>
    <div class="videos">
        <h2>Vidéos des mites</h2>
        <form action="SaveCroissantMain" method="post"><input type="checkbox" name="jeune" value="vrai" <%= affichageCroissant %>>
            <label for="jeune">Ordre croissant</label>
            <button type="submit">Recharger</button>
        </form>
        <%
            //videos
            nbVideo = VideoDAO.findNbVideo(rechFinal);
            nbPage = nbVideo/9;
            if(nbVideo%9 != 0){
                nbPage++;
            }
            List<Video> l = VideoDAO.findAllWith(rechFinal,croissant);

            if((numeroPage * 9)+9 >= l.size()){
                for(int i = (numeroPage * 9);i < l.size();i ++){
                    out.println("<div class=\"video\"><iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/"+l.get(i).getUrl()+"\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe><p>"+l.get(i).getTitle()+"</p></div>");
                }
            }else{
                for(int i = (numeroPage * 9);i < (numeroPage * 9)+9;i ++){
                    out.println("<div class=\"video\"><iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/"+l.get(i).getUrl()+"\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe><p>"+l.get(i).getTitle()+"</p></div>");
                }
            }
        %>
    </div>
    <div class="listButton">
        <%
            if(rechFinal.equals("")){
                for(int i = 0;i < nbPage;i ++){
                    out.println("<a href=\"/Mitterie/main.jsp?numeroPage="+i+"\">"+(i+1)+"</a>");
                }
            }else{
                for(int i = 0;i < nbPage;i ++){
                    out.println("<a href=\"/Mitterie/main.jsp?numeroPage="+i+"&rech="+rechFinal+"\">"+(i+1)+"</a>");
                }
            }
            out.println("</div>");
        %>
    </div>
</body>

</html>