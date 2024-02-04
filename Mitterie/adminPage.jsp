<%@ page import="java.util.List" %>
<%@ page import="modele.DAO.UserDAO" %>
<%@ page import="modele.POJO.User" %>
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
    <link type="text/css"  rel="stylesheet"  href="${pageContext.request.contextPath}/css/admin.css">
    <title>Page Admin</title>
</head>

<body>
    <h1>T'es admin toi ?...</h1><br><a href="Control?page=entrance">Retour</a>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Role</th>
                <th>Email</th>
                <th>Day of the last connection</td>
                <th>Time of the last connection</td>
            </tr>
        </thead>
        <tbody>
            <%
                List<User> l = UserDAO.findAllById();
                for(int i = 0;i < l.size();i ++){
                    out.println("<tr><td>"+l.get(i).getId()+"</td><td>"+l.get(i).getName()+"</td><td>"+l.get(i).getRole()+"</td><td>"+l.get(i).getEmail()+"</td><td>"+l.get(i).getLastDayCo().toString()+"</td><td>"+l.get(i).getLastTimeCo().toString()+"</td></tr>");
                }
            %>
        </tbody>
    </table>
</body>

</html>