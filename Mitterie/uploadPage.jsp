<%@ page contentType="text/html; charset=UTF-8" %>

<%
    if(session.getAttribute("token") == null){
        response.sendRedirect("/Mitterie/");
    }else{
        if(session.getAttribute("role") == null){
            response.sendRedirect("/Mitterie/");
        }else{
            if(!session.getAttribute("role").equals("admin")){
                response.sendRedirect("/Mitterie/");
            }
        }
    }
%>

<html>

<head>
    <title>Page upload</title>
    <link type="text/css"  rel="stylesheet"  href="${pageContext.request.contextPath}/css/upload.css">
</head>

<body>
    <a href="Control?page=entrance">Retour</a>
    <div class="video">
        <h1>Upload ta vidéo !</h1>
        <form action="UploadVideo" method="post">

            <div class="url">
                <input type="text" placeholder="Entrez l'url de la vidéo (sans espace ou autre caractères)" name="url" required>
                    <p>Exemple d'url valide :</p>
                    <img src="${pageContext.request.contextPath}/img/exempleurl.png" alt="url">
            </div>

            <div class="titre">
                <input type="text" placeholder="Entrez le nom de la Vidéo (sans accent ou charactères spécial)" name="titre"required>
                    <p>Exemple de titre valide :</p>
                    <img src="${pageContext.request.contextPath}/img/exempleTitre.png" alt="titre">
            </div>

            <button type="submit">Upload</button>

            <p>Si l'upload réussi vous serez reidirigé vers l'acceuil sinon vous reviendrez sur cette page</p>
        </form>
    </div>
</body>

</html>