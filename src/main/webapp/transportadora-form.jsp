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
     <div class="row">
         <div class="col-sm-4"></div>
         <div class="col-sm-4">
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
                  <br>
                  <div class="form-group row">
                      <label for="inputEmail" class="col-sm-2 col-form-label" >E-mail:</label> 
                      <div class="col-sm-10">
                          <input id="inputEmail" type="text" value="<c:out value='${transportadora.email}' />" class="form-control" style="width:300px;" name="email">
                      </div>
                  </div>

                          <div class="form-group row">
                              <label for="nome" class="col-sm-2 col-form-label">Nome:</label> 
                              <div class="col-sm-10">
                                  <input id="nome" type="text" value="<c:out value='${transportadora.nome}' />" class="form-control" style="width:300px;" name="nome">
                              </div>
                          </div>     
                          <div class="form-group row">
                              <label for="empresa" class="col-sm-2 col-form-label">Empresa:</label> 
                              <div class="col-sm-10">
                                  <input id="empresa" type="text" value="<c:out value='${transportadora.empresa}' />" class="form-control" style="width:300px;" name="empresa">

                              </div>
                          </div>
                          <div class="form-group row">
                            <label for="telefone" class="col-sm-2 col-form-label">Telefone:</label>
                                <div class="col-sm-10">
                                          <input id="telefone" type="text" value="<c:out value='${transportadora.telefone}' />" class="form-control"  style="width:300px;" name="telefone">
                                </div>
                               </div>     
                          <div class="form-group row">
                              <label for="celular" class="col-sm-2 col-form-label">Celular:</label>
                              <div class="col-sm-10">
                                   <input id="celular" type="text" value="<c:out value='${transportadora.celular}' />" class="form-control" style="width:300px;" name="celular">
                 
                              </div>
                         </div>    
                         <div class="form-group row">
                             <label for="whatsapp" class="col-sm-2 col-form-label">Whatsapp</label> 
                             <div class="col-sm-10">
                                 <input id="whatsapp" type="text" value="<c:out value='${transportadora.whatsapp}' />" class="form-control" style="width:300px;" name="whatsapp" >
                             </div>
                         </div>
                         <div class="form-group row">
                             <label for="modal" class="col-sm-2 col-form-label">Modal:</label> 
                             <div class="col-sm-10">
                                 <select id="modal" name="modal" value="<c:out value='${transportadora.modal}' />" class="form-control" style="width:300px;">
                                    <option value="<c:out value='${transportadora.modal}' />" selected><c:out value='${transportadora.modal}' /></option>
                                    <option>Rodoviario</option>
                                    <option>Aquaviario</option>
                                    <option>Aereo</option>
                                </select>
                             </div>
                         </div>
                         <div class="form-group row">
                             <label for="inputCep" class="col-sm-2 col-form-label">Cep </label>
                             <div class="col-sm-4">
                                 <input id="inputCep" type="text" onblur="procurarPorCep()" value="<c:out value='${transportadora.cep}' />" class="form-control" style="width:150px;" name="cep">
                             
                             </div>
                             <label class="col-sm-2 col-form-label">Estado</label>
                             <div class="col-sm-4">
                                 <select id="selectEstado" name="estado" value="<c:out value='${transportadora.estado}' />" class="form-control" style="width:155px">
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
                             </div>
                         </div>
                                   <div class="form-group row">
                                         <label for="inputCidade" class="col-sm-2 col-form-label">Cidade:</label>
                                         <div class="col-sm-4">
                                             <input id="inputCidade" type="text" value="<c:out value='${transportadora.cidade}' />" class="form-control" style="width:300px;" name="cidade">
                                         </div>  
                                    </div>
                                         <div class="form-group row">
                                             <label for="inputBairro" class="col-sm-2 col-form-label">Bairro:</label> 
                                             <div class="col-sm-4">
                                                 <input id="inputBairro" type="text" value="<c:out value='${transportadora.bairro}' />" class="form-control" style="width:300px;" name="bairro" >
                                             </div>
                                             
                                         </div>
                                         <div class="form-group row">
                                             <label for="inputRua" class="col-sm-2 col-form-label">Rua / Avenida:</label>
                                             <div class="col-sm-4">
                                                 <input id="inputRua" type="text" value="<c:out value='${transportadora.ruaAvenida}' />" class="form-control" style="width:300px;" name="ruaavenida">
                                             </div>
                                         </div>
                                         <div class="form-group row">
                                             <label for="numero" class="col-sm-2 col-form-label">Numero:</label>
                                             <div class="col-sm-4">
                                                 <input id="numero" type="text" value="<c:out value='${transportadora.numero}' />" class="form-control" style="width:300px;" name="numero">
                                             </div>
                                         </div>
                                         <div class="form-group row">
                                             <label for="output" class="col-sm-2 col-form-label">Logo:</label> 
                                             <div class="col-sm-4">
                                                 <img id="output" width="200" src="<c:out value='${transportadora.caminhoLogo}'/>">
                                             </div>
                                         </div>
                                          <div class="form-group row">
                                             <div class="col-sm-2">
                                             </div>
                                             <div class="col-sm-4">
                                                 <input accept="image/*" type="file" id="logo" name="image" value="Escolher logo" onchange="loadFile(event)" 
                                                    src="<c:out value='${transportadora.caminhoLogo}'/>"/>
                                             </div>
                                         </div>
                                         <div class="form-group row">
                                             <div class="col-sm-2">
                                             </div>
                                             <div class="col-sm-6">
                                                 <c:if test="${transportadora == null}">
                                                     <input type="checkbox" id="aceitacaoTermosServico" name="aceitacaoTermosServico" value="true">
                                                    <label for="aceitacaoTermosServico"> Eu aceito os termos de serviço</label><br>
                                                 </c:if>
                                             </div>
                                         </div>
                                         <div class="form-group row">
                                            <div class="col-sm-2">
                                               <!-- <form action="TransportadoraServlet" method="post">
                                                    <input type="hidden" name="action" value="pesquisar"/>
                                                    <button type="submit" class="btn btn-primary"/>Voltar</button>
                                                </form> -->
                                            </div>
                                            <div class="col-sm-4">
                                                 <c:if test="${transportadora == null}">
                                                    <button id="btnSubmit" type="submit" class="btn btn-success" >Cadastrar-se agora!</button>
                                                 </c:if>
                                            </div>
                                         </div>
                                         <c:if test="${transportadora != null}">
                                            <div class="form-group row">
                                                <div class="col-sm-2"></div>
                                                <div class="col-sm-3">
                                                    <button id="btnSubmit" type="submit" class="btn btn-success" style="width:150px;">Atualizar</button>
                                                </div>
                                                <div class="col-sm-3">
                                                    <button id="btnDeletar" type="submit" class="btn btn-danger" style="width:150px;">Deletar</button> 
                                                </div>
                                            </div>
                                         </c:if>
                    
                                    </form>
                    
                 <script>
                    
                            
                    $(document).ready(function () {
                         $("#btnDeletar").click(function (event) {
                             event.preventDefault();
                             
                             var confirmacao=confirm("Você realmente deseja excluir essa transportadora?");
                             if(confirmacao==false){
                                 return false;
                             }
                             
                             var formData = new FormData();
                             formData.append('action', 'deletar');
                             formData.append('id', $("#inputId").val());
                             
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
                                });
                         });
                        
                        $("#btnSubmit").click(function (event) {
                             //stop submit the form, we will post it manually.
                           event.preventDefault();
                           
                           if($('#inputEmail').val()==0)
                           {
                               alert("O campo e-mail é obrigatório");
                               return false;
                           }
                           if($('#nome').val()==0)
                           {
                               alert("O campo nome é obrigatório");
                               return false;
                           }
                           
                           if($('#empresa').val().length < 4){
                               alert("O nome da empresa deve conter pelo menos 4 caracteres");
                               return false;
                           }
                           if($('#telefone').val()==0)
                           {
                               alert("O campo telefone é obrigatório");
                               return false;
                           }
                           if($('#modal').val()==0)
                           {
                               alert("O campo modal é obrigatório");
                               return false;
                           }
                           if($('#inputRua').val()==0)
                           {
                               alert("O campo rua é obrigatório");
                               return false;
                           }
                           if($('#numero').val()==0)
                           {
                               alert("O campo numero é obrigatório");
                               return false;
                           }
                           if($('#bairro').val()==0)
                           {
                               alert("O campo numero é obrigatório");
                               return false;
                           }
                           if($('#inputBairro').val()==0)
                           {
                               alert("O campo bairro é obrigatório");
                               return false;
                           }
                           if($('#inputCidade').val()==0)
                           {
                               alert("O campo cidade é obrigatório");
                               return false;
                           }
                            if($('#selectEstado').val()==0)
                           {
                               alert("O campo UF é obrigatório");
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
         <div class="col-sm-4"></div>
     </div>    
</html>
