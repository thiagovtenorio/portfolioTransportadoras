package gw.portfoliotransportadoras.controller;

import gw.portfoliotransportadoras.filtro.FiltroTransportadora;
import gw.portfoliotransportadoras.manager.TransportadoraManager;
import gw.portfoliotransportadoras.modelo.Transportadora;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
                }else if(action.equals("excluir")){
                    excluirTransportadora(request, response);
                }else if(action.equals("pesquisar")){
                    pesquisarTransportadora(request, response);
                }
		
	}
        public void pesquisarTransportadora(HttpServletRequest request, HttpServletResponse response){
            try{
                
                String nome=request.getParameter("nome");
                
                FiltroTransportadora filtro=new FiltroTransportadora();
                filtro.setNome(nome);
                
                transportadoraManager=new TransportadoraManager();
                
                List<Transportadora> transportadoraList =transportadoraManager.pesquisarPorFiltro(filtro);
                
            }catch(Exception e){
                e.printStackTrace();
            }
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
            
            voltarTelaPrincipal(request, response);
        }
        
        public void alterarTransportadora(HttpServletRequest request, HttpServletResponse response){
            
            this.transportadoraManager=new TransportadoraManager();
            
            Transportadora transportadoraAtual=carregarTransportadora(request, response);
            
            this.transportadoraManager.alterarTransportadora(transportadoraAtual);
            
            voltarTelaPrincipal(request, response);
        }
        public void excluirTransportadora(HttpServletRequest request, HttpServletResponse response){
            this.transportadoraManager=new TransportadoraManager();
            
            Transportadora transportadoraEscolhida=carregarTransportadora(request, response);
            
            this.transportadoraManager.excluirTransportadora(transportadoraEscolhida.getId());
            
            voltarTelaPrincipal(request, response);
        }
        
        public void voltarTelaPrincipal(HttpServletRequest request, HttpServletResponse response){
            try{
                RequestDispatcher rd=request.getRequestDispatcher("viewtransportadoras.jsp");
                rd.forward(request, response);
                
            }catch(Exception e){
              e.printStackTrace();
            }
        }
        
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	
}
