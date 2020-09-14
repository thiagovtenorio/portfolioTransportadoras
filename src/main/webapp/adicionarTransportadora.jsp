<%@page import="gw.portfoliotransportadoras.dao.TransportadoraDAO"%>

<jsp:useBean id="transportadora" class="gw.portfoliotransportadoras.modelo.Transportadora"></jsp:useBean>
<jsp:setProperty property="*" name="transportadora"/>

<% 
    int i=TransportadoraDAO.inserir(transportadora);
    
    if(i>0){ 
        response.sendRedirect("adicionartransportadora-sucesso.jsp");
    }else{
        
    }
%>