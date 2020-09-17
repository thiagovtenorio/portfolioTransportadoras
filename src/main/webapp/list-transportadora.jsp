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
                
                
                <div class="row">
                    <div class="col-sm">
                        <div class="card-body">
                                <h3 class="text-left">Transportadoras</h3>
                                <br>
                                <script>
                                    function escolherEstado(estado){
                                        document.getElementById("inputEstado").value=estado;
                                    }
                                    function escolherCidade(cidade){
                                        document.getElementById("inputCidade").value=cidade;
                                    }
                                    function escolherModal(modal){
                                        document.getElementById("inputModal").value=modal;
                                    }
                                </script>
                                <form id="action" action="pesquisar" method="post">
                                    <label>Nome:</label> 
                                    <fieldset class="form-group">
                                        <input type="text" value="<c:out value='${transportadora.nome}' />" class="form-control" name="nome">
                                    </fieldset>
                                    <fieldset class="form-group">
                                        <label>Localizacao (UFs)
                                        </label>
                                        <table class="table table-bordered">
                                            <tbody>
                                                <c:forEach var="localizacaoUF" items="${listLocalizacaoUFs}">
                                                    <tr>
                                                        <td>
                                                            <a href="#" onclick="escolherEstado('${localizacaoUF.sigla}')">
                                                               <c:out value="${localizacaoUF.nomeCompleto} (${localizacaoUF.qtdCadastros})" />
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div style="width:150px">
                                            <input id="inputEstado" type="hidden" value="<c:out value='${transportadora.estado}' />" class="form-control" name="estado">
                                        </div>
                                            
                                    
                                    </fieldset>
                                    <fieldset class="form-group">
                                        <label>Localizacao (Municípios)
                                        </label>
                                        <table class="table table-bordered">
                                            <tbody>
                                                <c:forEach var="localizacao" items="${listLocalizacaoMunicipio}">
                                                    <tr>
                                                        <td>
                                                            <a href="#" onclick="escolherCidade('${localizacao.nome}')">
                                                               <c:out value="${localizacao.nome} (${localizacao.qtdCadastros})" />
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <input id="inputCidade" type="hidden" value="<c:out value='${transportadora.cidade}' />" class="form-control" name="cidade">
                                    </fieldset>
                                    <fieldset class="form-group">
                                        <label>Modal</label>
                                        <table class="table table-bordered">
                                            <tbody>
                                                <c:forEach var="modalQtd" items="${listModalQtd}">
                                                    <tr>
                                                        <td>
                                                            <a href="#" onclick="escolherModal('${modalQtd.descricao}')">
                                                               <c:out value="${modalQtd.descricao} (${modalQtd.qtdCadastros})" />
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <input id="inputModal" type="hidden" name="modal" value="<c:out value='${transportadora.modal}' />" class="form-control" style="width:155px"/>
                                        
                                    </fieldset>
                                     <button type="submit" class="btn btn-primary">Procurar</button>
                                </form>
                            </div>
                    </div>
                    <div class="col-sm">
                            <br>
                            <div class="container text-right">
                                <a href="<%=request.getContextPath()%>/novo" class="btn btn-success">Cadastre sua transportadora</a>
                            </div>
                            <br><br>
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
                                            <td> 
                                                <c:out value="${transportadora.email}" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
    
</html>