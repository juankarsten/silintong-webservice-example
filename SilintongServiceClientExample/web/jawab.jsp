<%-- 
    Document   : jawab
    Created on : May 18, 2013, 5:49:44 PM
    Author     : juan.karsten
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String id=request.getParameter("idquestion");
        %>
        <form action="Respond" method="post">
            Content:<input type="text" name="content"/>
            <input type="hidden" name="id" value="<%=id%>" />
            <input type="submit" value="jawab"/>
        </form>
    </body>
</html>
