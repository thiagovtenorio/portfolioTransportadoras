/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras.controller;

import gw.portfoliotransportadoras.filtro.FiltroTransportadora;
import gw.portfoliotransportadoras.manager.TransportadoraManager;
import gw.portfoliotransportadoras.modelo.LocalizacaoMunicipio;
import gw.portfoliotransportadoras.modelo.LocalizacaoUF;
import gw.portfoliotransportadoras.modelo.ModalQtd;
import gw.portfoliotransportadoras.modelo.Transportadora;
import gw.portfoliotransportadoras.service.ViaCEP;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vicente
 */
@WebServlet("/")
public class TransportadoraServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private TransportadoraManager transportadoraManager;
    
    public void init() {
        this.transportadoraManager=new TransportadoraManager();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
        
        try {
            switch (action) {
                case "/pesquisar":
                    pesquisarTransportadora(request, response);
                    break;
                case "/novo":
                    mostrarNovoForm(request, response);
                    break;
                case "/procurarCep": 
                    procurarCep(request,response);
                    break;
                case "/inserir":
                    adicionarTransportadora(request, response);
                    break;    
                case "/editar":
                    mostrarFormEdicao(request, response);
                    break;
                case "/alterar":
                    alterarTransportadora(request, response);
                    break;
                case "/deletar": 
                    deletarTransportadora(request, response);
                    break;
                default:
                    pesquisarTransportadora(request, response);
                    break;
            }
        }catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    public void procurarCep(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        try{
            String cep=request.getParameter("cep");

            ViaCEP viacep=new ViaCEP();
            viacep.buscar(cep);
            
            System.err.println("cidade "+viacep.getLocalidade());
            
            response.setContentType("text/plain");
            response.getWriter().write(viacep.getLocalidade());
        
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void pesquisarTransportadora(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        
        String nome=request.getParameter("nome");
        String estado=request.getParameter("estado");
        String cidade=request.getParameter("cidade");
        String modal=request.getParameter("modal");

        FiltroTransportadora filtro=new FiltroTransportadora();
        
        filtro.setNome(nome);
        filtro.setEstado(estado);
        filtro.setCidade(cidade);
        filtro.setModal(modal);
        
        List<Transportadora> listTransportadora=this.transportadoraManager.pesquisarPorFiltro(filtro);
        List<LocalizacaoUF> listLocalizacaoUfs=transportadoraManager.listLocalizacaoUFs();
        List<LocalizacaoMunicipio> listLocalizacaoMunicipio=transportadoraManager.listLocalizacaoMunicipio();
        List<ModalQtd> listModalQtd=transportadoraManager.listModalQtd();
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-transportadora.jsp");
        
        request.setAttribute("listLocalizacaoUFs", listLocalizacaoUfs);
        request.setAttribute("listLocalizacaoMunicipio", listLocalizacaoMunicipio);
        request.setAttribute("listModalQtd", listModalQtd);
        
        request.setAttribute("listTransportadora", listTransportadora);
        
        dispatcher.forward(request, response);
    }
    public Transportadora carregarTransportadora(HttpServletRequest request, HttpServletResponse response) {
        Integer id=0;
        if(request.getParameter("id")!=null){
            id = Integer.parseInt(request.getParameter("id"));
        }
        String email = (String) request.getParameter("email");
        String nome = (String) request.getParameter("nome");
        String empresa = (String) request.getParameter("empresa");
        String telefone = (String) request.getParameter("telefone");
        String celular = (String) request.getParameter("celular");
        String whatsapp = (String) request.getParameter("whatsapp");
        String modal = (String) request.getParameter("modal");
        String cep = (String) request.getParameter("cep");
        String estado = (String) request.getParameter("estado");
        String cidade = (String) request.getParameter("cidade");
        String bairro = (String) request.getParameter("bairro");
        String ruaAvenida = (String) request.getParameter("ruaavenida");
        Integer numero = Integer.parseInt((String) request.getParameter("numero"));

        Transportadora novaTransportadora = new Transportadora(id, nome, email, telefone, celular, whatsapp,
                modal, cep, estado, cidade, bairro, ruaAvenida, numero, empresa);
        return novaTransportadora;
    }
    public void adicionarTransportadora(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException{
        Transportadora novaTransportadora=carregarTransportadora(request, response);
        this.transportadoraManager.adicionarTransportadora(novaTransportadora);
        response.sendRedirect("list");
    }
    public void alterarTransportadora(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException{
        Transportadora transportadoraAtual=carregarTransportadora(request, response);
        this.transportadoraManager.alterarTransportadora(transportadoraAtual);
        response.sendRedirect("list");
    }
    public void deletarTransportadora(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException{
        Transportadora transportadoraAtual=carregarTransportadora(request, response);
        this.transportadoraManager.excluirTransportadora(transportadoraAtual);
        response.sendRedirect("list");
    }
    
    private void mostrarNovoForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("transportadora-form.jsp");
        dispatcher.forward(request, response);
    }
    private void mostrarFormEdicao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
         int id = Integer.parseInt(request.getParameter("id"));
         
         Transportadora transportadoraExistente=transportadoraManager.getTransportadoraPorId(id);
         RequestDispatcher dispatcher = request.getRequestDispatcher("transportadora-form.jsp");
         
         request.setAttribute("transportadora", transportadoraExistente);
         dispatcher.forward(request, response);
    }
    
    private void listTransportadora(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List<Transportadora> listTransportadora = transportadoraManager.getList();
        
        request.setAttribute("listTransportadora", listTransportadora);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-transportadora.jsp");
        
        dispatcher.forward(request, response);
    }
}
