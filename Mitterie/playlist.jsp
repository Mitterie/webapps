<%@ page contentType="text/html; charset=UTF-8" %>

<%
    if(session.getAttribute("token") == null){
        response.sendRedirect("/Mitterie/");
    }
%>

<html>

<head>
    <link type="text/css"  rel="stylesheet"  href="${pageContext.request.contextPath}/css/playlist.css">
    <title>Playlist Officiel</title>
</head>

<body>
    <div class="list">
        <h1>Régale toi chef !</h1>
        <ul>
            <li><a href="Control?page=entrance">Retour</a></li>
            <li><a href="Control?page=disconnect">Se déconnecter</a></li>
        </ul>
    </div>
    <div class="CarBT"><iframe style="border-radius:12px" src="https://open.spotify.com/embed/playlist/3EcbQmX1J0E4Ii8uty41VS?utm_source=generator&theme=0\"
            width="500" height="500" frameborder="0" allowfullscreen=""
            allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture" loading="lazy"></iframe>
        <div class="text">
            <p>Il
                était une fois dans une petite ville où un groupe de passionnés de
                musique et de tech, surnommé "Mitterie", avait une idée audacieuse.
                Inspiré par des moments hilarants du quotidien, ils décidèrent de créer
                un album décalé et goofy appelé "Car BT".
            </p>
            <p>Dubsy &amp; Vyque, ingénieurs talentueux, dirigeaient le projet.
                Ils transformèrent des bruits de klaxons, de glissade et de rires de
                scooby en une symphonie hilarante. "Car BT" devint rapidement une
                expérience sonore unique, célébrant l'absurde et la goofiness de la vie
                quotidienne.Avec des échantillons de voix déformées, des rires
                contagieux et des bruits d'animaux farfelus, l'album fut dévoilé au
                monde. Les auditeurs plongèrent dans l'univers goofy de "Mitterie",
                créant une communauté dédiée à la goofiness.</p>
            <p>"Car BT" devint un
                phénomène musical, propulsant "Mitterie" au rang de pionniers de la
                musique goofy. Le groupe continua à créer des expériences uniques, mais
                "Car BT" demeura une légende, apportant un sourire goofy à tous ceux qui
                l'avaient écouté. Ainsi, l'histoire de "Car BT" resta gravée dans les
                mémoires comme une aventure musicale inoubliable. S/O Coton Eye Joe</p>
        </div>
    </div>

</body>

</html>