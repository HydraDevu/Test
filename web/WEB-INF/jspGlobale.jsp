<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%--au lieu de mettre directement l'adresse de la page, on declare un 
        objet menu/corps et on lui affectera l'adresse dans le controller--%>
        <jsp:include page="${menu}" flush="false"/>
        <hr>
        <jsp:include page="${corps}" flush="false"/>
        
        
    </body>
</html>
