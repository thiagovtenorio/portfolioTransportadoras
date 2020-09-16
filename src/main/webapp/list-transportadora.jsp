<%-- 
    Document   : list-transportadora
    Created on : 16/09/2020, 10:03:43
    Author     : vicente
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    
    <head>
        <title>A Web Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    
    <body>
        
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                <div>
                    <a href="https://www.javaguides.net" class="navbar-brand"> Transportadoras App</a>
                </div>
                <ul class="navbar-nav">
                    <li>
                        <a href="<%=request.getContextPath()%>/list" class="nav-link">Transportadoras</a>
                    </li>
                </ul>
            </nav>
        </header>
        <br>
        
        <div class="row">
            
            <div class="container">
                <h3 class="text-center">Lista de Transportadoras</h3>
                <hr>
                <div class="container text-left">
                    <div class="container col-md-5">
                        <div class="card">
                            <div class="card-body">
                                <form id="action" action="pesquisar" method="post">
                                    <label>Nome:</label> 
                                    <fieldset class="form-group">
                                        <input type="text" value="<c:out value='${transportadora.nome}' />" class="form-control" name="nome">
                                    </fieldset>
                                     <button type="submit" class="btn btn-primary">Procurar</button>
                                </form>
                            </div>
                        </div>
                </div>
                <div class="container text-right">
                    <a href="<%=request.getContextPath()%>/novo" class="btn btn-success">Cadastre sua transportadora</a>
                </div>

                <br>
                <table class="table table-bordered">
                    <thead>
                    </thead>
                    <tbody>
                        <c:forEach var="transportadora" items="${listTransportadora}">
                            <tr>
                                <td>
                                    <a href="editar?id=<c:out value='${transportadora.id}' />">
                                        <c:out value="${transportadora.nome}" />
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${transportadora.telefone}" />
                                </td>
                                <td>
                                    <c:out value="${transportadora.whatsapp}" />
                                </td>
                                <td>
                                    <c:out value="${transportadora.empresa}" />
                                </td>
                                <td> &nbsp;&nbsp;&nbsp;&nbsp;</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
    
</html>