<%-- 
    Document   : Home
    Created on : 01/06/2017, 08:18:22 PM
    Author     : Admin-amb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align="center">Bienvenido: ${sessionScope.usuario.apellidoPaterno} 
        ${sessionScope.usuario.apellidoMaterno}
        ${sessionScope.usuario.nombres}</h1>
    </body>
</html>
