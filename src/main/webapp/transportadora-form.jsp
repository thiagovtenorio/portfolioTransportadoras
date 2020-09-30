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
         </nav>
     </header>
     <br>
     <div class="container col-md-5">
         <div class="card">
             <div class="card-body">
                 <c:if test="${transportadora != null}">
                     <form id="fileUploadForm" 
                           action="TransportadoraServlet" 
                           onsubmit="return validarCampos()"
                           method="post" 
                           enctype = "multipart/form-data">
                         <input id="action" type="hidden" name="action" value="alterar">
                 </c:if>
                 <c:if test="${transportadora == null}">
                     <form id="fileUploadForm"
                          action="TransportadoraServlet" 
                          method="post" 
                          onsubmit="return validarCampos()"
                          enctype = "multipart/form-data">
                          <input id="action" type="hidden" name="action" value="inserir">
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
                 <c:if test="${transportadora == null}">
                    <input id="inputId" type="hidden" name="id" value="0" />
                 </c:if>
                 <fieldset class="form-group">
                    <label>E-mail:</label> 
                    <input id="inputEmail" type="text" value="<c:out value='${transportadora.email}' />" class="form-control" name="email" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Nome:</label> 
                    <input id="nome" type="text" value="<c:out value='${transportadora.nome}' />" class="form-control" name="nome" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Empresa:</label> 
                    <input id="empresa" type="text" value="<c:out value='${transportadora.empresa}' />" class="form-control" name="empresa" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Telefone:</label> 
                    <input id="telefone" type="text" value="<c:out value='${transportadora.telefone}' />" class="form-control" name="telefone" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Celular:</label> 
                    <input id="celular" type="text" value="<c:out value='${transportadora.celular}' />" class="form-control" name="celular" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Whatsapp:</label> 
                    <input id="whatsapp" type="text" value="<c:out value='${transportadora.whatsapp}' />" class="form-control" name="whatsapp" >
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Modal:</label> 
                    <select id="modal" name="modal" value="<c:out value='${transportadora.modal}' />" class="form-control" style="width:155px" required="required">
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
                    <input id="numero" type="text" value="<c:out value='${transportadora.numero}' />" class="form-control" name="numero" required="required">
                 </fieldset>
                 <fieldset class="form-group">
                    <label>Logo:</label> 
                 </fieldset>
                 <fieldset class="form-group">
                     <img id="output" width="200" src="<c:out value='${transportadora.caminhoLogo}'/>">
                 </fieldset>
                 <fieldset class="form-group">
                    <input accept="image/*" type="file" id="logo" name="image" value="Escolher logo" onchange="loadFile(event)" 
                           src="<c:out value='${transportadora.caminhoLogo}'/>"/>
                 </fieldset>
                 
                  <c:if test="${transportadora == null}">
                     <fieldset class="form-group">
                       <input type="checkbox" id="aceitacaoTermosServico" name="aceitacaoTermosServico" value="true">
                       <label for="aceitacaoTermosServico"> Eu aceito os termos de serviço</label><br>
                    </fieldset>
                  </c:if>
                    
                 <c:if test="${transportadora != null}">
                    <button id="btnSubmit" type="submit" class="btn btn-success">Atualizar</button>
                    <button type="submit" class="btn btn-danger" onclick="changeActionDeletar()">Deletar</button> 
                 </c:if>
                <c:if test="${transportadora == null}">
                    <button id="btnSubmit" type="submit" class="btn btn-success" >Cadastrar-se agora!</button>
                 </c:if>
                 </form>
                 <fieldset class="form-group">
                    <form action="TransportadoraServlet" method="post">
                      <input type="hidden" name="action" value="pesquisar"/>
                      <button type="submit" class="btn btn-primary"/>Voltar</button>
                    </form>
                 </fieldset>
                 <script>
                    
                            
                    $(document).ready(function () {
                        $("#btnSubmit").click(function (event) {
                             //stop submit the form, we will post it manually.
                           event.preventDefault();
                           
                            if($('#empresa').val().length < 4){
                                alert("O nome da empresa deve conter pelo menos 4 caracteres");
                                return false;
                            }
                           
                           var formData = new FormData();
                           formData.append('action', $('#action').val());
                           
                           formData.append('id', $('#inputId').val());
                           formData.append('email', $('#inputEmail').val());
                           formData.append('nome', $('#nome').val());
                           formData.append('empresa', $('#empresa').val());
                           formData.append('telefone', $('#telefone').val());
                           formData.append('celular', $('#celular').val());
                           formData.append('whatsapp', $('#whatsapp').val());
                           formData.append('modal', $('#modal').val());
                           formData.append('cep', $('#inputCep').val());
                           formData.append('estado', $('#selectEstado').val());
                           formData.append('cidade', $('#inputCidade').val());                           
                           formData.append('bairro', $('#inputBairro').val());
                           formData.append('ruaavenida', $('#inputRua').val());
                           formData.append('numero', $('#numero').val());
                           formData.append('empresa', $('#empresa').val());
                           
                           const file=document.querySelector('#logo').files;
                           
                           formData.append('image', file[0]);
                           
                           
                           let options = {
                                method: 'POST',
                                body: formData
                            };
                           
                           const URL_TO_FETCH = 'TransportadoraServlet';
                           fetch(URL_TO_FETCH, options)
                                   .then(function(res)
                                { 
                                    var contentType = res.headers.get("content-type");
                                    if(contentType && contentType.indexOf("application/json") !== -1)
                                    {
                                        return res.json();
                                    }
                                    
                                }).then(function (json){
                                    
                                   if(json.codigo==1 ){ 
                                        window.location.href='TransportadoraServlet?action=pesquisar';
                                   }
                                    alert(json.mensagem);
                                })
                                  
//                          .catch(function(err) { 
//                              console.error(err); 
//                           });
                           
                        });
                    });
                    
                    
                    var loadFile = function(event) {
                        var image = document.getElementById('output');
                        
                        image.src = URL.createObjectURL(event.target.files[0]);
                        
                        //document.getElementById('inputCaminhoLogo').value=image.src;
                        
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
                        document.getElementById("action").value="deletar";
                    }
                </script>
             </div>
         </div>
     </div>            
</html>
