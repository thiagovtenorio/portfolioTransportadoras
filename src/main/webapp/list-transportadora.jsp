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
            </nav>
            <style>
/*                ul#filtros li {
                    display:inline;
                  }*/
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
                
                .btn-link {
                    border: none;
                    outline: none;
                    background: none;
                    cursor: pointer;
                    color: #0000EE;
                    padding: 0;
                    text-decoration: underline;
                    font-family: inherit;
                    font-size: inherit;
                }
                
            </style>
        </header>
        <br>
        <div class="row" >
            <div class="col-sm-3">
                <div class="card"> 
                    
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
                                    function escreverNome(nome){
                                        if(document.getElementById("inputNome").value.length>0){
                                            document.getElementById("filtroNome").hidden=false;
                                            document.getElementById("inputNome").value=nome;
                                            document.getElementById("filtroNome").innerHTML=nome+'<span id="closeFiltroNome" class="close"> x</span>';
                                            document.getElementById("closeFiltroNome").addEventListener("click", function() 
                                                    {
                                                        document.getElementById("inputNome").value='';
                                                        document.getElementById("filtroNome").hidden=true;
                                                        document.getElementById("filtroNome").innerHTML='';
                                                    });
                                       }
                                        
                                    }
                                    
                                    function close(){
                                       alert('teste');
                                    }
                                    
                                </script>
                                <form id="action" action="TransportadoraServlet" method="post">
                                    <input type="hidden" name="action" value="pesquisar">
                                    
                                    <fieldset class="form-group">
                                        <c:out value='${qtdResultados}'/> resultados
                                    </fieldset>
                                    <fieldset class="form-group">
                                        <ul id="filtros"> 
                                             <li id="filtroNome" hidden="true">
                                            </li>
                                            <li id="filtroEstado" hidden="true">
                                            </li>
                                            <li id="filtroCidade" hidden="true">
                                            </li>
                                            <li id="filtroModal" hidden="true">
                                            </li>
                                        </ul>
                                    </fieldset>
                                        
                                    <label><b>Nome</b></label> 
                                    <fieldset class="form-group">
                                        <input id="inputNome" type="text" value="<c:out value='${transportadora.nome}' />" onblur="escreverNome(this.value)" class="form-control" style="width:300px;" name="nome">
                                    </fieldset>
                                    <fieldset class="form-group">
                                        <label>
                                            <b>
                                                Localizacao (UFs)
                                            </b>
                                            
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
                                        <label>
                                            <b>
                                                Localizacao (Municípios)
                                            </b>
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
                                        <label>
                                            <b>
                                                Modal
                                            </b>
                                        </label>
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
                </div>
                    <div class="col-sm-9">
                        <div class="card">
                            <div class="card-body">
                                
                                <br>
                                <div class="container text-right">

                                    <form action="TransportadoraServlet">
                                        <input type="hidden" name="action" value="novo">
                                        <button type="submit" class="btn btn-success" value="">Cadastre sua transportadora</button>
                                    </form>

                                </div>
                                <table class="table table-bordered">
                                <thead>
                                </thead>
                                <tbody>
                                    <tr>
                                      <th>Logo</th>
                                      <th>Nome</th>
                                      <th>Telefone</th>
                                      <th>Whatsapp</th>
                                      <th>Empresa</th>
                                      <th>Email</th> 
                                    
                                    </tr>
                                    <c:forEach var="transportadora" items="${listTransportadora}">
                                        
                                        <tr>
                                            <td>
                                                <img src="<c:out value="${transportadora.caminhoLogo}" />">
                                                 
                                            </td>
                                                <td>
                                                    <form method="post" action="TransportadoraServlet" >
                                                        <input type="hidden" name="id" value=<c:out value='${transportadora.id}' />> 
                                                        <input type="hidden" name="action" value="editar"/>

                                                        <button type="submit" class="btn-link">
                                                            <c:out value="${transportadora.nome}" />
                                                        </button>
                                                    </form>
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