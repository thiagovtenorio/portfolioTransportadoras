<%-- 
    Document   : viewtransportadoras
    Created on : 14/09/2020, 11:04:53
    Author     : vicente
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Transportadoras</title>
    </head>
    <body>
        
        <%@page import="gw.portfoliotransportadoras.dao.TransportadoraDAO, gw.portfoliotransportadoras.modelo.Transportadora, java.util.*"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        
        <h1>Lista de Transportadoras</h1>
        
        <%
            List<Transportadora> list=TransportadoraDAO.getList();
            request.setAttribute("list",list);
        %>
        
        <table border="1" width="90%">
            
            <tr>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Whatsapp</th>
                <th>Cep</th>
                <th> </th>
                <th> </th>
                <th> </th>
                <th> </th>
            </tr>
            
            <c:forEach items="${list}" var="t">
                <tr>
                    <td>${t.getNome()}</td>
                    <td>${t.getTelefone()}</td>
                    <td>${t.getWhatsapp()}</td>
                    <td>${t.getCep()}</td>
                </tr>
            </c:forEach>
            
        </table>
        <br/><a href="adicionarTransportadoraForm.jsp">Adicionar nova Transportadora</a>
        
    </body>
</html>
