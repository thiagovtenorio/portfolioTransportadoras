<%-- 
    Document   : alterartransportadoraform
    Created on : 15/09/2020, 10:43:46
    Author     : vicente
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Transportadora</title>
    </head>
    <body>
        <%@page import="gw.portfoliotransportadoras.dao.TransportadoraDAO, gw.portfoliotransportadoras.modelo.Transportadora"%>

        <%
            String id=request.getParameter("id");
        
            Transportadora transportadoraBanco=TransportadoraDAO.getTransportadoraPorId(Integer.parseInt(id));
            
        %>
        
        <h1>Atualizar / Deletar Nova Transportadora</h1>
        
        <form action="TransportadoraBO" method="post">
    
            <input type="hidden" name="action" value="alterar" />

            <table>
                <tr>
                    <td>E-mail:</td>
                    <td>
                        <input type="text" name="email" value="<%= transportadoraBanco.getEmail()%>"/>
                    </td>
                </tr>

                <tr>
                    <td>Nome:</td>
                    <td>
                        <input type="text" name="nome" value="<%= transportadoraBanco.getNome()%>"/>
                    </td>
                </tr>

                <tr>
                    <td>Empresa:</td>
                    <td>
                        <input type="text" name="empresa" value="<%= transportadoraBanco.getEmpresa()%>"/>
                    </td>
                </tr>

                <tr>
                    <td>Telefone:</td>
                    <td>
                        <input type="text" name="telefone" value="<%= transportadoraBanco.getTelefone()%>"/>
                    </td>
                </tr>

                <tr>
                    <td>Celular:</td>
                    <td>
                        <input type="text" name="celular" value="<%= transportadoraBanco.getCelular()%>"/>
                    </td>
                </tr>

                <tr>
                    <td>Whatsapp:</td>
                    <td>
                        <input type="text" name="whatsapp" value="<%= transportadoraBanco.getWhatsapp()%>"/>
                    </td>
                </tr>

                <tr>
                    <td>Modal:</td>
                    <td>
                        <select name="modal" style="width:155px">
                            <option value="<%= transportadoraBanco.getModal()%>" selected ><%= transportadoraBanco.getModal()%></option>
                            <option>Rodoviario</option>
                            <option>Aquaviario</option>
                            <option>Aereo</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>Cep:</td>
                    <td>
                        <input type="text" name="cep" value="<%= transportadoraBanco.getCep()%>"/>
                    </td>
                    <td>Estado:</td>
                    <td>
                        <select name="estado" style="width:40px">
                            <option value="<%= transportadoraBanco.getEstado()%>" selected ><%= transportadoraBanco.getEstado()%></option>
                            <option>AC</option>
                        </select>
                    </td>
                </tr>

                <tr> 
                    <td>Cidade:</td>
                    <td>
                        <input type="text" name="cidade" value="<%= transportadoraBanco.getCidade()%>"/>
                    </td>
                </tr>

                <tr> 
                    <td>Bairro:</td>
                    <td>
                        <input type="text" name="bairro" value="<%= transportadoraBanco.getBairro()%>"/>
                    </td>
                </tr>

                <tr> 
                    <td>Rua / Avenida:</td>
                    <td>
                        <input type="text" name="ruaAvenida" value="<%= transportadoraBanco.getRuaAvenida()%>"/>
                    </td>
                </tr>

                <tr> 
                    <td>Número:</td>
                    <td>
                        <input type="text" name="numero" value="<%= transportadoraBanco.getNumero()%>"/>
                    </td>
                </tr>

                <tr> 
                    <td>Logo:</td>
                    <td>
                        <input type="file" id="logo" value="Escolher logo" />
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" value="Atualizar"/>
                    </td>
                    <td colspan="2">
                        <input type="submit" value="Deletar"/>
                    </td>
                </tr>
            </table>
        </form>
        
    </body>
</html>
