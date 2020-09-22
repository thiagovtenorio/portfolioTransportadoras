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
                <div class="navbar-nav">
                    <a href="<%=request.getContextPath()%>/list" class="nav-link">Transportadoras</a>
                </div>
            </nav>
            <style>
                ul {
                    list-style-type: none;
                    padding: 0;
                    margin: 0;
                  }
                /* Add a light grey background color on hover */
                ul li:hover {
                  background-color: #eee;
                }
                /* Style the list items */
                ul li {
                  border: 1px solid #ddd;
                  margin-top: -1px; /* Prevent double borders */
                  background-color: #f6f6f6;
                  padding: 12px;
                  text-decoration: none;
                  font-size: 18px;
                  color: black;
                  display: block;
                  position: relative;
                  width:150px;
                }
                
                .close:hover {background: #bbb;}
                
            </style>
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
                                        document.getElementById("filtroEstado").hidden=false;
                                        document.getElementById("inputEstado").value=estado;
                                        document.getElementById("filtroEstado").innerHTML=estado+'<span id="closeFiltroEstado" class="close"> x</span>';
                                        
                                        document.getElementById("closeFiltroEstado")
                                                .addEventListener("click", function() 
                                                {
                                                    document.getElementById("inputEstado").value='';
                                                    document.getElementById("filtroEstado").hidden=true;
                                                    document.getElementById("filtroEstado").innerHTML='';
                                                });
                                    }
                                    function escolherCidade(cidade){
                                        document.getElementById("filtroCidade").hidden=false;
                                        document.getElementById("inputCidade").value=cidade;
                                        document.getElementById("filtroCidade").innerHTML=cidade+'<span id="closeFiltroCidade" class="close"> x</span>';
                                        
                                        document.getElementById("closeFiltroCidade")
                                                .addEventListener("click", function() 
                                                {
                                                    document.getElementById("inputCidade").value='';
                                                    document.getElementById("filtroCidade").hidden=true;
                                                    document.getElementById("filtroCidade").innerHTML='';
                                                });
                                        
                                    }
                                    function escolherModal(modal){
                                        document.getElementById("filtroModal").hidden=false;
                                        document.getElementById("inputModal").value=modal;
                                        document.getElementById("filtroModal").innerHTML=modal+'<span id="closeFiltroModal" class="close"> x</span>';
                                        
                                        document.getElementById("closeFiltroModal")
                                                .addEventListener("click", function() 
                                                {
                                                    document.getElementById("inputModal").value='';
                                                    document.getElementById("filtroModal").hidden=true;
                                                    document.getElementById("filtroModal").innerHTML='';
                                                });
                                    }
                                    function close(){
                                       alert('teste');
                                    }
                                    
                                </script>
                                <form id="action" action="pesquisar" method="post">
                                    <fieldset class="form-group">
                                        <c:out value='${qtdResultados}'/> resultados
                                    </fieldset>
                                    <fieldset class="form-group">
                                        <ul id="filtros"> 
                                            <li id="filtroEstado" hidden="true">
                                                
                                            </li>
                                            <li id="filtroCidade" hidden="true">
                                            </li>
                                            <li id="filtroModal" hidden="true">
                                            </li>
                                        </ul>
                                    </fieldset>
                                        
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