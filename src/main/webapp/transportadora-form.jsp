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
        <title>A Web Page</title>
        <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
        <script src="js/app-ajax.js" type="text/javascript"></script>
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
                     <form id="action" action="alterar" method="post" onsubmit="return validarCampos()"> 
                 </c:if>
                 <c:if test="${transportadora == null}">
                    
                     <form id="fileUploadForm"
                          action="TransportadoraServlet" 
                          method="post" 
                          onsubmit="return validarCampos()"
                          enctype = "multipart/form-data">
                         
                          <input type="hidden" name="action" value="inserir">
                 </c:if>
                 
                 <caption>
                     <h2>
                         <c:if test="${transportadora != null}">
                             Atualizar / Deletar Transportadora
                         </c:if>
                         <c:if test="${transportadora == null}">
                             Cadastrar Transportadora
                         </c:if>
                     </h2>
                 </caption>
                 <c:if test="${transportadora != null}">
                    <input id="inputId" type="hidden" name="id" value="<c:out value='${transportadora.id}' />" />
                 </c:if>
                 
                 <fieldset class="form-group">
                    <label>E-mail:</label> 
                    <input id="inputEmail" type="text" value="<c:out value='${transportadora.email}' />" class="form-control" name="email" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Nome:</label> 
                    <input type="text" value="<c:out value='${transportadora.nome}' />" class="form-control" name="nome" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Empresa:</label> 
                    <input id="inputEmpresa" type="text" value="<c:out value='${transportadora.empresa}' />" class="form-control" name="empresa" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Telefone:</label> 
                    <input type="text" value="<c:out value='${transportadora.telefone}' />" class="form-control" name="telefone" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Whatsapp:</label> 
                    <input type="text" value="<c:out value='${transportadora.whatsapp}' />" class="form-control" name="whatsapp" >
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Modal:</label> 
                    <select name="modal" value="<c:out value='${transportadora.modal}' />" class="form-control" style="width:155px" required="required">
                        <option value="<c:out value='${transportadora.modal}' />" selected><c:out value='${transportadora.modal}' /></option>
                        <option>Rodoviario</option>
                        <option>Aquaviario</option>
                        <option>Aereo</option>
                    </select>
                 </fieldset>
                 <fieldset class="form-group"> 
                     <label>Cep</label>
                     <input id="inputCep" type="text" onblur="procurarPorCep()" value="<c:out value='${transportadora.cep}' />" class="form-control" style="width:155px" name="cep">
                 </fieldset>
                <fieldset class="form-group">
                    <label>Estado</label>
                     <select id="selectEstado" name="estado" value="<c:out value='${transportadora.estado}' />" class="form-control" style="width:155px" required="required">
                        <option value="<c:out value='${transportadora.estado}' />" selected><c:out value='${transportadora.estado}' /></option> 
                        <option>AC</option>
                        <option>AL</option>
                        <option>AP</option>
                        <option>AM</option>
                        <option>BA</option>
                        <option>BA</option>
                        <option>CE</option>
                        <option>DF</option>
                        <option>ES</option>
                        <option>GO</option>
                        <option>MA</option>
                        <option>MT</option>
                        <option>MS</option>
                        <option>MG</option>
                        <option>PA</option>
                        <option>PB</option>
                        <option>PR</option>
                        <option>PE</option>
                        <option>PI</option>
                        <option>RJ</option>
                        <option>RN</option>
                        <option>RS</option>
                        <option>RO</option>
                        <option>RR</option>
                        <option>SC</option>
                        <option>SP</option>
                        <option>SE</option>
                        <option>TO</option>
                    </select>
                </fieldset>
                <fieldset class="form-group">
                    <label>Cidade:</label> 
                    <input id="inputCidade" type="text" value="<c:out value='${transportadora.cidade}' />" class="form-control" name="cidade" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Bairro:</label> 
                    <input id="inputBairro" type="text" value="<c:out value='${transportadora.bairro}' />" class="form-control" name="bairro" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Rua / Avenida:</label> 
                    <input id="inputRua" type="text" value="<c:out value='${transportadora.ruaAvenida}' />" class="form-control" name="ruaavenida" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Numero:</label> 
                    <input type="text" value="<c:out value='${transportadora.numero}' />" class="form-control" name="numero" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    
                 </fieldset>
                 <fieldset class="form-group">
                      <img id="output" width="200">
                 </fieldset>
                 <fieldset class="form-group">
                    <input type="checkbox" id="aceitacaoTermosServico" name="aceitacaoTermosServico" value="true">
                    <label for="aceitacaoTermosServico"> Eu aceito os termos de serviço</label><br>
                 </fieldset>
                 <c:if test="${transportadora != null}">
                    <button type="submit" class="btn btn-success">Atualizar</button>
                    <button type="submit" class="btn btn-danger" onclick="changeActionDeletar()">Deletar</button> 
                 </c:if>
                <c:if test="${transportadora == null}">
                    <button id="btnSubmit" type="submit" class="btn btn-success" >Cadastrar-se agora!</button>
                 </c:if> 
                    <input id="inputCaminhoLogo" type="hidden" name="caminhoLogo">
                    <input accept="image/*" type="file" id="logo" name="image" value="Escolher logo" onchange="loadFile(event)" />
                 </form>
                 
                     <form  action="TransportadoraServlet" method = "post"  >
                        <label>Logo:</label>
                        <input  type= "submit" value = "Upload File" />
                     </form>
                     
                 <script>
                    
                    
                    $(document).ready(function () {
                        $("#btnSubmit").click(function (event) {
                             //stop submit the form, we will post it manually.
                            event.preventDefault();
                            // Get form
                            var form = $('#fileUploadForm')[0];
                            // Create an FormData object
                            var data = new FormData(form);
                            // If you want to add an extra field for the FormData
                            
                            // disabled the submit button
                            $("#btnSubmit").prop("disabled", true);
                            
                            $.ajax({
                                type: "POST",
                                enctype: 'multipart/form-data',
                                url: "TransportadoraServlet",
                                data: data,
                                processData: false,
                                contentType: false,
                                cache: false,
                                timeout: 600000,
                                success: function (responseText) {
                                   var jsonObj = jQuery.parseJSON(responseText); 
                                   console.log(responseText);
                                    $("#btnSubmit").prop("disabled", false);
                                },
                                error: function (e) {
                                    console.log("ERROR : ", e);
                                    $("#btnSubmit").prop("disabled", false);
                                }
                            });
                            
                        });
                    });
                    
                    
                    
                    var loadFile = function(event) {
                        var image = document.getElementById('output');
                        
                        image.src = URL.createObjectURL(event.target.files[0]);
                        
                        document.getElementById('inputCaminhoLogo').value=image.src;
                        
                    };
                    
                    function validarCampos(){
                        if($('#inputEmpresa').val().length < 4){
                            alert("O nome da empresa deve conter pelo menos 4 caracteres");
                            return false;
                        }
                    }
                    
                    function procurarPorCep(){
                        $.ajax({
                            url : 'CepServlet',
                            data : {
                                    inputCep : $('#inputCep').val()
                            },
                            success : function(responseText) {
                                    var jsonObj = jQuery.parseJSON(responseText); 
                                    document.getElementById("selectEstado").value=jsonObj.uf;
                                    document.getElementById("inputCidade").value=jsonObj.localidade;
                                    document.getElementById("inputBairro").value=jsonObj.bairro;
                                    document.getElementById("inputRua").value=jsonObj.logradouro;
                            }
                        });
                    }
                     
                    function changeActionDeletar(){
                        document.getElementById("action").action="deletar";
                    }
                </script>
             </div>
         </div>
     </div>            
</html>
