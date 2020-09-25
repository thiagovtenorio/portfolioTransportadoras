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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author vicente
 */
@WebServlet("/TransportadoraServlet")
@MultipartConfig
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
        
        String action="";
        if(request.getParameter("action")!=null){
            action= (String)request.getParameter("action");
        }
         
        System.err.println(action);
        try {
            switch (action) {
                case "pesquisar":
                    pesquisarTransportadora(request, response);
                    break;
                case "novo":
                    mostrarNovoForm(request, response);
                    break;
                case "inserir":
                    adicionarTransportadora(request, response);
                    break;    
                case "editar":
                    mostrarFormEdicao(request, response);
                    break;
                case "alterar":
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
        
        Integer qtdResultados=this.transportadoraManager.getQtdResultados();
        List<Transportadora> listTransportadora=this.transportadoraManager.pesquisarPorFiltro(filtro);
        List<LocalizacaoUF> listLocalizacaoUfs=transportadoraManager.listLocalizacaoUFs();
        List<LocalizacaoMunicipio> listLocalizacaoMunicipio=transportadoraManager.listLocalizacaoMunicipio();
        List<ModalQtd> listModalQtd=transportadoraManager.listModalQtd();
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-transportadora.jsp");
        
        request.setAttribute("qtdResultados", qtdResultados);
        request.setAttribute("listLocalizacaoUFs", listLocalizacaoUfs);
        request.setAttribute("listLocalizacaoMunicipio", listLocalizacaoMunicipio);
        request.setAttribute("listModalQtd", listModalQtd);
        request.setAttribute("listTransportadora", listTransportadora);
        request.setAttribute("homePath", getServletContext().getRealPath("/WEB-INF"));       
        
        
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
        
        Integer numero=0;
        if(request.getParameter("numero")!=null){
            numero = Integer.parseInt((String) request.getParameter("numero"));
        }
        
        Transportadora novaTransportadora = new Transportadora(id, nome, email, telefone, celular, whatsapp,
                modal, cep, estado, cidade, bairro, ruaAvenida, numero, empresa);
        
        System.err.println(email);
        
        carregarLogo(request, response, novaTransportadora);
        
        return novaTransportadora;
    }
    public void carregarLogo(HttpServletRequest request, HttpServletResponse response,Transportadora novaTransportadora){
        try {
            Part filePart = request.getPart("image");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = filePart.getInputStream();
            byte[] buffer = new byte[fileContent.available()];
            fileContent.read(buffer);
            
            File arquivoLogo = new File("/home/vicente/Documentos/desenvolvimento/apache-tomcat-8.0.27/webapps/data/"+fileName);
            
            if(arquivoLogo!=null)
            {
                OutputStream outStream = new FileOutputStream(arquivoLogo);
                outStream.write(buffer);
                novaTransportadora.setArquivoLogo(arquivoLogo);
            }
            System.err.println("fileName "+fileName);
            
        } catch (IOException ex) {
            Logger.getLogger(TransportadoraServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(TransportadoraServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isCampoEmailValido(String email) throws ServletException, IOException, AddressException{
        
        boolean result = true;
        
        try{
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        }catch(AddressException ex){
            
            result = false;
        }
        return result;
    }
    
    public void adicionarTransportadora(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
        System.err.println("adicionarTransportadora");
        Transportadora novaTransportadora=carregarTransportadora(request, response);
        
        try{
            if(isCampoEmailValido(novaTransportadora.getEmail()))
            {
                this.transportadoraManager.adicionarTransportadora(novaTransportadora);
                response.sendRedirect("list");
            }else{
                PrintWriter pw = response.getWriter();
                pw.println("E-mail inválido!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    public void alterarTransportadora(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException{
        Transportadora transportadoraAtual=carregarTransportadora(request, response);
        
        try{
            if(isCampoEmailValido(transportadoraAtual.getEmail()))
            {
                this.transportadoraManager.alterarTransportadora(transportadoraAtual);
                response.sendRedirect("list");
            }else{
                PrintWriter pw = response.getWriter();
                pw.println("E-mail inválido!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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
}
