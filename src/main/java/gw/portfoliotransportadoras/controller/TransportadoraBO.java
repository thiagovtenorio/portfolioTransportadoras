package gw.portfoliotransportadoras.controller;

import gw.portfoliotransportadoras.manager.TransportadoraManager;
import gw.portfoliotransportadoras.modelo.Transportadora;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransportadoraBO extends HttpServlet {
	private TransportadoraManager transportadoraManager;
        
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String action=request.getParameter("action");
		
                if(action.equals("adicionar")){
                    adicionarTransportadora(request, response);
                }else if(action.equals("alterar")){
                    alterarTransportadora(request, response);
                }
                
//		LoginBean bean=new LoginBean();
//		bean.setName(name);
//		bean.setPassword(password);
//		request.setAttribute("bean",bean);
//		
//		boolean status=bean.validate();
//		
//		if(status){
//			RequestDispatcher rd=request.getRequestDispatcher("login-success.jsp");
//			rd.forward(request, response);
//		}
//		else{
//			RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
//			rd.forward(request, response);
//		}
		
		
	}
        public Transportadora carregarTransportadora(HttpServletRequest request, HttpServletResponse response){
            Integer id=Integer.parseInt(request.getParameter("transportadoraId"));
            
            String email=(String)request.getParameter("email");
            String nome=(String)request.getParameter("nome");
            String empresa=(String)request.getParameter("empresa");
            String telefone=(String)request.getParameter("telefone");
            String celular=(String)request.getParameter("celular");
            String whatsapp=(String)request.getParameter("whatsapp");
            String modal=(String)request.getParameter("modal");
            String cep=(String)request.getParameter("cep");
            String estado=(String)request.getParameter("estado");
            String cidade=(String)request.getParameter("cidade");
            String bairro=(String)request.getParameter("bairro");
            String ruaAvenida=(String)request.getParameter("ruaAvenida");
            Integer numero=Integer.parseInt((String)request.getParameter("numero"));
            
            Transportadora novaTransportadora=new Transportadora(id, nome,email,telefone,celular,whatsapp,
                    modal,cep,estado,cidade,bairro,ruaAvenida,numero,empresa);
            return novaTransportadora;
        }
        
        public void adicionarTransportadora(HttpServletRequest request, HttpServletResponse response){
            
            this.transportadoraManager=new TransportadoraManager();
            
            Transportadora novaTransportadora=carregarTransportadora(request, response);
            
            this.transportadoraManager.adicionarTransportadora(novaTransportadora);
            
        }
        
        public void alterarTransportadora(HttpServletRequest request, HttpServletResponse response){
            
            this.transportadoraManager=new TransportadoraManager();
            
            Transportadora transportadoraAtual=carregarTransportadora(request, response);
            
            this.transportadoraManager.alterarTransportadora(transportadoraAtual);
        }
        
        
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	
}
