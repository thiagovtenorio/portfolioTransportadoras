<%-- 
    Document   : transportadora-form
    Created on : 16/09/2020, 10:45:53
    Author     : vicente
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
     <head>
        <title>User Management Application</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
     </head>
     
     <header>
         <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
             <div>
                 <a href="https://www.javaguides.net" class="navbar-brand"> Transportadoras App </a>
             </div>

             <ul class="navbar-nav">
                 <li>
                     <a href="<%=request.getContextPath()%>/list" class="nav-link">Transportadoras</a>
                 </li>
             </ul>
         </nav>
     </header>
     <br>
     <div class="container col-md-5">
         <div class="card">
             <div class="card-body">
                 <c:if test="${transportadora != null}">
                     <form action="alterar" method="post">
                 </c:if>
                 <c:if test="${transportadora == null}">
                    <form action="inserir" method="post">
                 </c:if>
                 
                 <caption>
                     <h2>
                         <c:if test="${transportadora != null}">
                             Alterar Transportadora
                         </c:if>
                         <c:if test="${transportadora == null}">
                             Cadastrar Transportadora
                         </c:if>
                     </h2>
                 </caption>
                 <c:if test="${transportadora != null}">
                    <input type="hidden" name="id" value="<c:out value='${transportadora.id}' />" />
                 </c:if>
                 <fieldset class="form-group">
                    <label>E-mail:</label> 
                    <input type="text" value="<c:out value='${transportadora.email}' />" class="form-control" name="name" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Nome:</label> 
                    <input type="text" value="<c:out value='${transportadora.nome}' />" class="form-control" name="name" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Empresa:</label> 
                    <input type="text" value="<c:out value='${transportadora.empresa}' />" class="form-control" name="name" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Telefone:</label> 
                    <input type="text" value="<c:out value='${transportadora.telefone}' />" class="form-control" name="name" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Whatsapp:</label> 
                    <input type="text" value="<c:out value='${transportadora.whatsapp}' />" class="form-control" name="name" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Modal:</label> 
                    <select name="modal" value="<c:out value='${transportadora.modal}' />" class="form-control" style="width:155px">
                        <option>Rodoviario</option>
                        <option>Aquaviario</option>
                        <option>Aereo</option>
                    </select>
                 </fieldset>
                 <fieldset class="form-group"> 
                     <label>Cep</label>
                     <input type="text" value="<c:out value='${transportadora.cep}' />" class="form-control" style="width:155px" name="name" required="required">
                 </fieldset>
                <fieldset class="form-group">
                    <label>Estado</label>
                     <select name="estado" value="<c:out value='${transportadora.estado}' />" class="form-control" style="width:155px">
                        <option>AC</option>
                    </select>
                </fieldset>
                <fieldset class="form-group">
                    <label>Cidade:</label> 
                    <input type="text" value="<c:out value='${transportadora.cidade}' />" class="form-control" name="name" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Bairro:</label> 
                    <input type="text" value="<c:out value='${transportadora.bairro}' />" class="form-control" name="name" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Rua / Avenida:</label> 
                    <input type="text" value="<c:out value='${transportadora.ruaAvenida}' />" class="form-control" name="name" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Numero:</label> 
                    <input type="text" value="<c:out value='${transportadora.numero}' />" class="form-control" name="name" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                     <label>Logo:</label>
                     <input type="file" id="logo" value="Escolher logo" />
                 </fieldset> 
                 <fieldset class="form-group">
                    <input type="checkbox" id="aceitacaoTermosServico" name="aceitacaoTermosServico" value="true">
                    <label for="aceitacaoTermosServico"> Eu aceito os termos de serviço</label><br>
                 </fieldset>
                <button type="submit" class="btn btn-success">Cadastrar-se agora!</button>
                </form>
             </div>
         </div>
     </div>            
</html>
